/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Board_Model;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Nicolle'S
 */
public class Board_View extends JPanel{
    
    final int QUANTITY_IM; //cantidad de imagenes
    final int SIZE_IMAGEN; //tamano de las imagenes
    JButton [][] tableB;
    ImageIcon [] Im;
    Board_Model tab;
    
    
    
    
    
    public Board_View(Board_Model tab) {
        
        QUANTITY_IM = 11;
        SIZE_IMAGEN = 35;
        this.tab = tab;
        
        tableB = new JButton[tab.getN_ROWS()][tab.getN_COLS()];
        Initialize_TableB();
        
        Im = new ImageIcon[QUANTITY_IM];
        Load_Images();
        
       
        this.setLayout(null);
        this.setSize(SIZE_IMAGEN*tab.getN_COLS(),SIZE_IMAGEN*tab.getN_ROWS());
        this.setVisible(true);            
    }

    public JButton[][] getTableB() {
        return tableB;
    }
    
    
    
    
    /**
     * Carga el vectos de imagenes con las imagenes necesarias para el tablero
     */
    private void Load_Images(){
        
        try{
        for (int i = 0; i < Im.length; i++)
        {
           Im[i]=new ImageIcon(getClass().getResource("/Images/"+i+".jpg"));       
        }
        }catch(NullPointerException eex){
            System.out.println("Problema al cargar las imagenes");
        }  
    }
    
    /**
     * Da memoria, posicion y tamano a cada boton del tablero de juego.
     */
    private void Initialize_TableB(){
        
        for (int i = 0; i < tab.getN_ROWS(); i++) {
            
            for (int j = 0; j < tab.getN_COLS(); j++) {
                
                tableB[i][j] = new JButton();
                tableB[i][j].setBounds(SIZE_IMAGEN*j,SIZE_IMAGEN*i,SIZE_IMAGEN,SIZE_IMAGEN);
                this.add(tableB[i][j]);   
            }   
        }
    }
    
    /**
     * actualiza el tablero con las imagenes que corresponden segun el valor
     * encontrado en el tablero logico.
     */
    public void paint_table(){
        
        for (int i = 0; i < tab.getN_ROWS(); i++) {
            
            for (int j = 0; j < tab.getN_COLS(); j++) {
                
                if(tab.getTable()[i][j] < 10){
                    
                    tableB[i][j].setIcon(Im[tab.getTable()[i][j]]);
                }
                
                if(tab.getTable()[i][j] >= 20){
                    
                    tableB[i][j].setIcon(Im[10]);
                }
                
                if(tab.getTable()[i][j] >=11 && tab.getTable()[i][j] <= 19){
                    
                    tableB[i][j].setIcon(null);
                }
            }   
        }
    } 
}
