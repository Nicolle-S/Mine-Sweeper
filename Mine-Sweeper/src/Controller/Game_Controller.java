/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Game_Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Representa la venatana de la aplicacion.
 * Controla la interacciones entrantes y cambia el estado del juego.
 * @author DilanU
 */
public class Game_Controller extends JFrame 
{
    private Game_Model game_model; // modelo del juego
    private ButtonAdapter listener_button; // listenner
    
    
    /**
     * Inicializar el controlador del juego
     */
    public Game_Controller() 
    {
        // inicializo valores de la ventana
        this.setResizable( false );
        this.setLocationRelativeTo( null );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setTitle( "Mineswipper" );
        this.setSize( Game_Model.WIDTH, Game_Model.HEIGHT);
        
        this.game_model = new Game_Model(); // creo modelo
        this.add( this.game_model.getMain_menu() ); // cargo vista
        this.listener_button = new ButtonAdapter(); // creo listener
        
        // agrego listener a botones
        this.game_model.getMain_menu().getMines().addActionListener(listener_button);
        this.game_model.getMain_menu().getkColor().addActionListener(listener_button);
        this.game_model.getMain_menu().getExit().addActionListener(listener_button);
        this.game_model.getMines_menu().getSquare().addActionListener(listener_button);
        this.game_model.getMines_menu().getHexagon().addActionListener(listener_button);
        this.game_model.getMines_menu().getMenu().addActionListener(listener_button);
    } // fin de Game_Controller
    
    
    /**
     * recibe los eventos provenientes de botones
     */
    class ButtonAdapter implements ActionListener
    {
        /**
         * Procesa los eventos provenientes de botones
         * @param e almacena informacion relativa al evento
         */
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            JButton source =  (JButton)e.getSource(); // fuente del evento
            
            
            switch( source.getText() )
            {
                case "MinesWipper":
                    System.out.println("MinesWiper");
                    break;
                    
                case "K-Color":
                    System.out.println("K-Color");
                    break;
                    
                case "Exit":
                    System.out.println("Exit");
                    break;
                    
                case "Square":
                    System.out.println("Square");
                    break;
                    
                case "Hexagon":
                    System.out.println("Hexagon");
                    break;
                    
                case "Menu":
                    System.out.println("Menu");
                    break; 
            } // fin del switch
        } // fin de actionPerformed   
    } // fin de ButonAdaparte
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        Game_Controller ventana = new Game_Controller();
        ventana.setVisible(true);
    } // fin de main
} // fin de Game_Controller
