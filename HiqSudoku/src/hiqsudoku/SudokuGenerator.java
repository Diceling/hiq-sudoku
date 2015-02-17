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
     * Calculates and returns all indices in the quad of a provided index
     * @param index the index of which to calculate quad indices
     * @return an array containing the four indices in the quad
     */
    private int[] getConcernedIndices(int index)
    {
        int counter = 0;
        int[] indices = new int[9];
        
        int diagonal;
        int row = index/4;
        int col = index%4;
        
        if(row %2 != 0)
        {
            if(col%2 != 0)
            {
                diagonal = index - 5;
            }
            else
            {
                diagonal = index -3;
            }
        }
        else 
        {
            if(col%2 != 0)
            {
                diagonal = index +3;
            }
            else
            {
                diagonal = index +5;
            }
        }
        indices[counter++] = diagonal;
        
        for(int i = 0; i < 4; i++)
        {
            indices[counter++] = col+i*4;
            indices[counter++] = row*4+i;
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
    private boolean validateNumber(int number, int index)
    {
        
        for(int i : getConcernedIndices(index))
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
     * @return a list of integers containing numbers 1 through 4
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
                    if(validateNumber(number, index))
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
        
        
    }
    
    public String presentSudoku(int difficulty)
    {
        
        String sudokuString = grid[0] + " "+ grid[1] + " " +grid[2] + " " +grid[3] + "\n" +
                              grid[4] + " "+ grid[5] + " " +grid[6] + " " +grid[7] + "\n" + 
                              grid[8] + " "+ grid[9] + " " +grid[10] + " " +grid[11] + "\n" +
                              grid[12] + " "+ grid[13] + " " +grid[14] + " " +grid[15] + "\n";
        return sudokuString;
    }
}
