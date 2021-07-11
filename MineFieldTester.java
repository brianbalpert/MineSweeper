import java.util.Scanner;

public class MineFieldTester {
    public static void main(String[] args) {
/*         MineField mineField = new MineField(5,5,5);
        mineField.populateMineField(1, 2);
        System.out.println(mineField.toString());
        System.out.println();
        mineField.resetEmpty();
        System.out.println(mineField.toString());
        mineField.populateMineField(5, 6);
        System.out.println();
        System.out.println(mineField.toString());
 */

         boolean[][] smallMineField = 
         {{false, false, false, false}, 
         {true, false, false, false}, 
         {false, true, false, false},
         {false, true, false, true}};

         

         MineField mineField2 = new MineField(smallMineField);
         System.out.println(mineField2.toString());
         smallMineField[0][0] = true;
         System.out.println("");
         System.out.println(mineField2.toString());


/*          System.out.println(mineField2.numAdjacentMines(3,2) + " : should be 4");
         System.out.println(mineField2.numAdjacentMines(0,0) + " : should be 1"); */

    }
}
