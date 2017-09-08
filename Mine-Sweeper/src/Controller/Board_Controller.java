/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Board_Model;
import Model.Hexagon;
import Model.Square;

/**
 * Representa el controlador del para el juego mineswipper.
 * Se encarga de recibir y procesar las entradas producto de la 
 * interaccion con el usuario.
 * @author DilanU
 */
public class Board_Controller 
{
    Board_Model board_model; // modelo del mineswipper

    
    
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
    } // fin de Board_Controller
    
    
    
} // fin de Board_Controller
