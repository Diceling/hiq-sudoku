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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SudokuGenerator generator = new SudokuGenerator();
        for(int i = 0; i < 10; i ++)
        {
            
           generator.generateSudoku();
           System.out.println(generator.presentSudoku(i));
        }
    }
}
