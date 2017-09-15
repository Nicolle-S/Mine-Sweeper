/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.K_Color_Model;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Nicolle'S
 */
public class K_color_View extends JPanel{
    
    final int QUANTITY_IM; //cantidad de imagenes
    private ImageIcon [] Im; // imagenes del tablero
    private JButton [][] tableB; // matriz de celdas
    private K_Color_Model tab;
    final int SIZE_IMAGEN; //tamano de las imagenes
    
    
    
    public K_color_View() {
        
        QUANTITY_IM = 7;
        Im = new ImageIcon[QUANTITY_IM];
        tab = new K_Color_Model();
        SIZE_IMAGEN = 100;
    }
    
    
    
    private void Load_Images()
    {
        try{
        for (int i = 0; i < Im.length; i++)
        {    
            Im[i]=new ImageIcon(getClass().getResource("/Images/"+i+".png"));         
        }
        }catch(NullPointerException eex){
            System.out.println("Problema al cargar las imagenes");
        }  
    } // fin de Load_Images
    
    
    private void Initialize_TableB()
    {
        
        for (int i = 0; i < tab.getROWS(); i++) {
            
            for (int j = 0; j < tab.getCOLS(); j++) {
                
                tableB[i][j] = new JButton();
                tableB[i][j].setBounds(SIZE_IMAGEN*j,SIZE_IMAGEN*i,SIZE_IMAGEN,SIZE_IMAGEN); 
               
                if(tab.getTable()[i][j] != null){
                    
                    this.add(tableB[i][j]);
                }
                   
            }   
        }
    } // fin de Initialize_TableB
    
    
    public void paint_table(){
        
        for (int i = 0; i < tab.getROWS(); i++) {
            
            for (int j = 0; j < tab.getCOLS(); j++) {
                
                if(tab.getTable()[i][j] != null && tab.getTable()[i][j].isEdge() == true){
                    
                    tableB[i][j].setIcon(Im[ tab.getTable()[i][j].getValor_Edge() ]); // dibujo la arista que corresponde
                }
                
                if(tab.getTable()[i][j] != null && tab.getTable()[i][j].isEdge() ==false ){
                    
                    tableB[i][j].setIcon(Im[ tab.getTable()[i][j].getColor() ]);
                }
            }   
        }
    } // fin de paint_table   
}
