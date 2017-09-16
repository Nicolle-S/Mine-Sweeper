/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.K_Color_Model;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Nicolle'S
 */
public class K_Color_Controller {
    
    private final K_Color_Model colorModel;

    public K_Color_Controller() 
    {
        
        colorModel =  new K_Color_Model();
        addEvente_Table();
        colorModel.getColor_view().paint_table();
        System.out.println(colorModel.quantity_Mine());
    }

    public K_Color_Model getColorModel() {
        return colorModel;
    }
    
    
    private void addEvente_Table()
    {
        for (int i = 0; i < colorModel.getROWS(); i++) 
        {
            for (int j = 0; j < colorModel.getCOLS(); j++) 
            {   
                final int a = i, b = j;
                
                colorModel.getColor_view().getTableB()[i][j].addMouseListener(new MouseAdapter() {
                    
                    
                    /**
                     * Procesa los click en las celdas del tablero
                     * @param e evento del raton
                     */
                    @Override
                    public void mouseClicked(MouseEvent e) 
                    {     
                        super.mouseClicked(e);
                        
                        
                        // si ya se acabo el juego
                        if( K_Color_Model.state != K_Color_Model.StateGame.PLAY )
                            return;
                        
                        
                        /**
                         * Verifico si fue el izquierdo
                         */
                        if( e.getButton() == MouseEvent.BUTTON1){
                              
                            colorModel.Discovered_cell(a, b);
                            colorModel.getColor_view().paint_table();
                            colorModel.check_Mine(a, b);
                            colorModel.check_win();
               
                        } 
                    }
     
                });
    
                        
            }
        
        } 
    } // fin de addEvent_Table
    
}
