/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hiqsudoku;



/**
 *
 * @author gunnarsodergren
 */
public class HiqSudoku {
    
    
    
    /**
     * @param args the command line arguments First argument should be the number of Sudokus to generate (only positive digits). 
     * Second argument should be the difficulty of the sudoku (only digits between 1 and 15). 
     */
    public static void main(String[] args) {
        int difficulty = 8;
        int nrOfSudokus = 10;
        if(args.length ==1)
        {
           
                try{
                    nrOfSudokus = Integer.parseInt(args[0]);
                }catch(NumberFormatException e)
                {
                    System.out.println(e.getMessage() + ": Couldn't parse Nr of Sudokus. Using standard number (10)");
                
                }
        }
        if(args.length ==2)
        {
            try{
                difficulty = Integer.parseInt(args[1]);
            }catch(NumberFormatException e)
            {
                 System.out.println(e.getMessage() + ": Couldn't parse difficulty. Using standard number (8)");
            }
        }
        
        SudokuGenerator generator = new SudokuGenerator();
        for(int i = 0; i < nrOfSudokus-1; i ++)
        {
           generator.generateSudoku();
           System.out.println("Sudoku:");
           System.out.println(generator.presentSudoku(difficulty));
           System.out.println("Intended solution:");
           System.out.println(generator.presentIntendedSolution());
           
        }
    }
}
