import java.util.Random;

/**
 * MineField class with locations of mines for a game. This class is mutable,
 * because we sometimes need to change it once it's created. mutators:
 * populateMineField, resetEmpty includes convenience method to tell the number
 * of mines adjacent to a location.
 */
public class MineField {

   // <put instance variables here>
   private boolean[][] mineData;
   private int numMines;

   /**
    * Create a minefield with same dimensions as the given array, and populate it
    * with the mines in the array such that if mineData[row][col] is true, then
    * hasMine(row,col) will be true and vice versa. numMines() for this minefield
    * will corresponds to the number of 'true' values in mineData.
    * 
    * @param mineData the data for the mines; must have at least one row and one
    *                 col, and must be rectangular (i.e., every row is the same
    *                 length)
    */
   public MineField(boolean[][] mineData) {
      int numRows = mineData.length;
      int numCols = mineData[0].length;
      this.mineData = new boolean[numRows][numCols];
      numMines = 0;
      for(int row = 0; row < numRows; row++){
         for(int col = 0; col < numCols; col++){
            if(mineData[row][col] == true){
               this.mineData[row][col] = true;
               numMines++;
            }
            else{
               this.mineData[row][col] = false;
            }   
         }
      }
   }

   /**
    * Create an empty minefield (i.e. no mines anywhere), that may later have
    * numMines mines (once populateMineField is called on this object). Until
    * populateMineField is called on such a MineField, numMines() will not
    * correspond to the number of mines currently in the MineField.
    * 
    * @param numRows  number of rows this minefield will have, must be positive
    * @param numCols  number of columns this minefield will have, must be positive
    * @param numMines number of mines this minefield will have, once we populate
    *                 it. PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3
    *                 of total number of field locations).
    */
   public MineField(int numRows, int numCols, int numMines) {
      this.mineData = new boolean[numRows][numCols];
      this.numMines = numMines;
   }

   /**
    * Removes any current mines on the minefield, and puts numMines() mines in
    * random locations on the minefield, ensuring that no mine is placed at (row,
    * col).
    * 
    * @param row the row of the location to avoid placing a mine
    * @param col the column of the location to avoid placing a mine PRE:
    *            inRange(row, col)
    */
   public void populateMineField(int row, int col) {
      assert inRange(row, col);
      resetEmpty();
      int index;
      int irow;
      int icol;
      Random rand = new Random();
      for (int i = 0; i < numMines; i++) {
         index = rand.nextInt(numRows() * numCols());
         irow = index / numCols();
         icol = index % numCols();
         if (hasMine(irow, icol) || (irow == row && icol == col)) {
            i--;
            continue;
         }
         mineData[irow][icol] = true;
      }
   }

   /**
    * Reset the minefield to all empty squares. This does not affect numMines(),
    * numRows() or numCols() Thus, after this call, the actual number of mines in
    * the minefield does not match numMines(). Note: This is the state a minefield
    * created with the three-arg constructor is in at the beginning of a game.
    */
   public void resetEmpty() {
      for(int row = 0; row < numRows(); row++){
         for(int col = 0; col < numCols(); col++){
            mineData[row][col] = false;
         }
      }
   }

   /**
    * Returns the number of mines adjacent to the specified mine location (not
    * counting a possible mine at (row, col) itself). Diagonals are also considered
    * adjacent, so the return value will be in the range [0,8]
    * 
    * @param row row of the location to check
    * @param col column of the location to check
    * @return the number of mines adjacent to the square at (row, col) PRE:
    *         inRange(row, col)
    */
   public int numAdjacentMines(int row, int col) {
      assert inRange(row, col);
      int adjMines = 0;

      for (int x = row - 1; x <= row + 1; x++) {
         for (int y = col - 1; y <= col + 1; y++) {
            if (inRange(x, y) && !(x == row && y == col)) {
               if (hasMine(x, y)) {
                  adjMines++;
               }
            }
         }
      }

      return adjMines;
   }

   /**
    * Returns true iff (row,col) is a valid field location. Row numbers and column
    * numbers start from 0.
    * 
    * @param row row of the location to consider
    * @param col column of the location to consider
    * @return whether (row, col) is a valid field location
    */
   public boolean inRange(int row, int col) {
      if(row >= 0 && row < numRows() && col >= 0 && col < numCols()){
         return true;
      }
      else{
         return false;
      } 
   }

   /**
    * Returns the number of rows in the field.
    * 
    * @return number of rows in the field
    */
   public int numRows() {
      return mineData.length;
   }

   /**
    * Returns the number of columns in the field.
    * 
    * @return number of columns in the field
    */
   public int numCols() {
      return mineData[0].length;
   }

   /**
    * Returns whether there is a mine in this square
    * 
    * @param row row of the location to check
    * @param col column of the location to check
    * @return whether there is a mine in this square PRE: inRange(row, col)
    */
   public boolean hasMine(int row, int col) {
      assert inRange(row, col);
      return mineData[row][col] == true;
   }

   /**
    * Returns the number of mines you can have in this minefield. For mines created
    * with the 3-arg constructor, some of the time this value does not match the
    * actual number of mines currently on the field. See doc for that constructor,
    * resetEmpty, and populateMineField for more details.
    * 
    * @return
    */
   public int numMines() {
      return numMines;
   }

   // <put private methods here>
   public String toString(){
      char[] out = new char[numRows()*numCols()*2];
      int i = 0;
      for (int row = 0; row < numRows(); row++){
         for (int col = 0; col < numCols(); col++){
            if(hasMine(row, col)){
               out[i] = '1';
            }
            else{
               out[i] = '0';
            }
            i++;
            if(col == numCols()-1){
               out[i] = '\n';
            }
            else{
               out[i] = ' ';
            }
            i++;
            
         }
      }

      return String.copyValueOf(out).strip();
   }
}
