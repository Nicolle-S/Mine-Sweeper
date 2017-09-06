/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mine.sweeper;

import Model.Board_Model;

/**
 *
 * @author Nicolle'S
 */
public class MineSweeper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Board_Model obj = new Board_Model();
        
        obj.load_Board();
        obj.Load_adjacent();
        obj.Print_Board();
    }
    
}
