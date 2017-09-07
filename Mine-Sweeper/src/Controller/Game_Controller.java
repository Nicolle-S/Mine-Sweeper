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
import javax.swing.JPanel;

/**
 * Representa la venatana de la aplicacion.
 * Controla la interacciones entrantes y cambia el estado del juego.
 * @author DilanU
 */
public class Game_Controller extends JFrame 
{
    private Game_Model game_model; // modelo del juego
    private ButtonAdapter listener_button; // listenner
    private JPanel actualView; // vista actual  
    
    
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
        this.actualView = this.game_model.getMain_menu(); // obtengo la vista
        this.getContentPane().add( this.actualView ); // cargo vista
        this.invalidate();
        this.validate();
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
            
            
            // actua de acuerdo a la entrada del usuario
            switch( source.getText() )
            {
                case "MinesWipper":
                    setActualView( game_model.getMines_menu() );
                    break;
                    
                case "K-Color":
                    System.out.println("Barredor de color");
                    break;
                    
                case "Exit":
                    System.exit(0); // termina le ejecucion
                    break;
                    
                case "Square":
                    System.out.println("Square");
                    break;
                    
                case "Hexagon":
                    System.out.println("Hexagon");
                    break;
                    
                case "Menu":
                    setActualView( game_model.getMain_menu() );
                    break; 
            } // fin del switch
        } // fin de actionPerformed   
    } // fin de ButonAdaparte

    
    /**
     * Establece la vista actual
     * @param view vista a ser establecido
     */
    public void setActualView(JPanel view) 
    {
        this.getContentPane().remove( this.actualView ); // remuevo vista actual
        this.actualView = view; // obtengo nuevo vista
        this.getContentPane().add( this.actualView ); // establezco nueva vista
        this.invalidate(); 
        this.validate();
        this.getContentPane().repaint(); // repinta el tablero
    } // fin de setActualView
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        Game_Controller ventana = new Game_Controller();
        ventana.setVisible(true);
    } // fin de main
} // fin de Game_Controller
