/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Board_Model;
import Model.Hexagon;
import Model.Square;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Representa el controlador del para el juego mineswipper.
 * Se encarga de recibir y procesar las entradas producto de la 
 * interaccion con el usuario.
 * @author DilanU
 */
public class Board_Controller 
{
    private final Board_Model board_model; // modelo del mineswipper

    
    /**
     * Instancia el modelo de juego de acuerdo al modo de juego elegido
     * @param modoJuego 
     */
    public Board_Controller( int modoJuego ) 
    {
        if( modoJuego == Board_Model.SQUARE )
        {
            this.board_model = new Square();
        }
        else
        {
            this.board_model = new Hexagon();
        }
        
        addEvente_Table();
    } // fin de Board_Controller

    
    /**
     * Retorna el board_model
     * @return board_model
     */
    public Board_Model getBoard_model() 
    {
        return board_model;
    }
    
    private void addEvente_Table(){
        
        for (int i = 0; i < board_model.getN_ROWS(); i++) {
            
            for (int j = 0; j < board_model.getN_COLS(); j++) {
                
                final int a = i, b = j;
                
                board_model.getBoard_view().getTableB()[i][j].addMouseListener(new MouseAdapter() {
                    
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                        super.mouseClicked(e);
                        
                        /**
                         * Verifico si el click presionado es el derecho, si es
                         * asi, quiere decir que marco la celda
                         */
                        if( e.getButton() == MouseEvent.BUTTON3){
                            
                            /**
                             * valido que solo pueda marcar una celda que aun no
                             * ha descubierto
                             */
                            if(board_model.getTable()[a][b] >= 10 && board_model.getTable()[a][b] < 20){
                                
                                board_model.Mark_cell(a, b);
                                board_model.getBoard_view().paint_table(); //actualizo el tablero
                                
                            }
                            
                        }
                        
                        
                        /**
                         * Verifico si fue el izquierdo
                         */
                        if( e.getButton() == MouseEvent.BUTTON1){
                            
                            /**
                             * Valido que solo pueda descubrir celdas aun
                             * cubiertas
                             */
                            if(board_model.getTable()[a][b] >= 10){
                                
                                /**
                                 * Verifico si es una celda vacia, de ser asi, 
                                 * busco todas las celdas sin mina circundantes
                                 */
                                if(board_model.getTable()[a][b] == board_model.getCOVER_FOR_CELL()){

                                    board_model.search_campEmpty(a,b);
                                    board_model.getBoard_view().paint_table(); //actualizo el tablero
                                }

                                /**
                                 * Si lo que presiono no fue una celda vacia
                                 * entonces descubro unicamente la celda 
                                 * seleccionada
                                 */
                                else {

                                    board_model.discover_cell(a,b);
                                    board_model.getBoard_view().paint_table(); //actualizo el tablero
                                }


                            }
                            
                        }
                        
                    }
     
                });
    
                        
            }
        
        } 
    } // fin de addEvent_Table 
} // fin de Board_Controller
