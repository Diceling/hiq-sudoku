/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hiqsudoku;

/**
 *
 * @author gunnarsodergren
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author gunnarsodergren
 */
public class SudokuGenerator {
    
    
    Random rand;
    
    /**
     * Integer Array to store the Sudoku grid. 
     */
    private Integer[] grid;
    
    /**
     * Constructor that initializes the Random generator and the grid array
     */
    public SudokuGenerator()
    {
        rand = new Random();
        grid = new Integer[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    }

    
    /**
     * Calculates and returns all indices on the row of a provided index
     * @param index the index of which to calculate row indices
     * @return an array containing the four indices on the row
     */
    private int[] getRowIndices(int index)
    {
        int row = index /4; 
        int[] indices = new int[4];
        for(int i = 0; i < 4; i++)
        {
            indices[i] = row*4+i;
        }
        return indices;
    }
    
    /**
     * Calculates and returns all indices in the column of a provided index
     * @param index the index of which to calculate column indices
     * @return an array containing the four indices in the column
     */
    private int[] getColIndices(int index)
    {
        int column = index % 4;
        int[] indices = new int[4];
        for(int i = 0; i < 4; i++)
        {
            indices[i] = column+i*4;
        }
        return indices;
    }
    
    /**
     * Calculates and returns all indices in the quad of a provided index
     * @param index the index of which to calculate quad indices
     * @return an array containing the four indices in the quad
     */
    private int[] getQuadIndices(int index)
    {
        int[] indices = new int[4];
        if( index == 0 || index == 1 || index ==4 || index == 5)
        {
            indices[0] = 0;
            indices[1] = 1;
            indices[2] = 4;
            indices[3] = 5;
            return indices; 
        }
        if( index == 2 || index == 3 || index ==6 || index == 7)
        {
            indices[0] = 2;
            indices[1] = 3;
            indices[2] = 6;
            indices[3] = 7;
            return indices; 
        }
        if( index == 8 || index == 9 || index ==12 || index == 13)
        {
            indices[0] = 8;
            indices[1] = 9;
            indices[2] = 12;
            indices[3] = 13;
            return indices; 
        }
        if( index == 10 || index == 11 || index ==14 || index == 15)
        {
            indices[0] = 10;
            indices[1] = 11;
            indices[2] = 14;
            indices[3] = 15;
            return indices; 
        }
        return indices;
    }
    
    /**
     * Checks whether the provided @param number is valid for the @param index, based on the @param indices
     * @param number the number to be validated
     * @param index the index in the grid that is to be validated
     * @param indices the corresponding indices to the index provided. Row, Column or Quad. 
     * @return 
     */
    private boolean validate(int number, int index, int[] indices)
    {
        for(int i : indices)
        {
            if(i != index && grid[i] == number)
            {
                return false;                
            }
        }
        return true;
    }
    
    
    /**
     * Is used to refresh the list of potential numbers for a given index. 
     * @return a list of integers contaning numbers 1 through 4
     */
    private List<Integer> getFreshNumbers()
    {
        List<Integer> nums = new ArrayList<Integer>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        return nums;
    }
    
    /**
     * Generates and outputs a 4x4 sudoku on the command line. 
     */
    public void generateSudoku()
    {
        List<List<Integer>> remainingNumbers = new ArrayList<List<Integer>>();
        for(int i = 0; i < 16; i++)
        {
           remainingNumbers.add(getFreshNumbers());
        }
        
        int index = 0;
        while( index <16)
        {
            boolean validNumber = false;
            while(validNumber == false)
            {
                if(remainingNumbers.get(index).size() <=0)
                {
                    validNumber = true;
                    remainingNumbers.set(index, getFreshNumbers());
                    index--;
                    remainingNumbers.get(index).remove(grid[index]);
                    grid[index] = 0;
                }
                else
                {
                    Integer number = remainingNumbers.get(index).get(rand.nextInt(remainingNumbers.get(index).size()));
                    if(validate(number, index, getRowIndices(index)) && validate(number, index, getColIndices(index)) && 
                            validate(number, index, getQuadIndices(index)))
                    {
                        grid[index] = number;
                        validNumber = true;
                        index++;
                    }
                    else
                    {
                        remainingNumbers.get(index).remove(number);
                    }
                }
            }
        }
        
        System.out.println(grid[0] + " "+ grid[1] + " " +grid[2] + " " +grid[3] + "\n" +
                           grid[4] + " "+ grid[5] + " " +grid[6] + " " +grid[7] + "\n" + 
                           grid[8] + " "+ grid[9] + " " +grid[10] + " " +grid[11] + "\n" +
                           grid[12] + " "+ grid[13] + " " +grid[14] + " " +grid[15] + "\n");
    }
}
