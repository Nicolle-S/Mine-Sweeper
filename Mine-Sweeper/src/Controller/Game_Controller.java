/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Board_Model;
import Model.Game_Model;
import View.Board_View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setTitle( "Mineswipper" );
        this.setSize( Game_Model.WIDTH, Game_Model.HEIGHT);
        this.setLocationRelativeTo( null );
        
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
        this.game_model.getDifficulty_menu().getEasy().addActionListener(listener_button);
        this.game_model.getDifficulty_menu().getMedium().addActionListener(listener_button);
        this.game_model.getDifficulty_menu().getHard().addActionListener(listener_button);
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
            switch( source.getName() )
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
                    Game_Model.game_mode = Game_Model.Game_Mode.SQUARE;
                    setActualView( game_model.getDifficulty_menu() );                    
                    break;
                    
                case "Hexagon":
                    Game_Model.game_mode = Game_Model.Game_Mode.HEXAGON;
                    setActualView( game_model.getDifficulty_menu() );
                    break;
                    
                case "Menu":
                    setSize( Game_Model.WIDTH, Game_Model.HEIGHT);
                    setActualView( game_model.getMain_menu() );
                    break;
                    
                case "Easy":
                    Game_Model.difficulty = Game_Model.Difficulty.EASY;
                    startGame();
                    break;

                case "Medium":
                    Game_Model.difficulty = Game_Model.Difficulty.MEDIUM;
                    startGame();
                    break;

                case "Hard":
                    Game_Model.difficulty = Game_Model.Difficulty.HARD;
                    startGame();
                    break;
                    
                case "Restar":
                    startGame();
                    break;
            } // fin del switch
        } // fin de actionPerformed   
    } // fin de ButonAdaparte
    
    
    /**
     * inicia un nuevo juego, estableciendo la vista del juego
     */
    private void startGame()
    {
        // instacio en modo de juego elegido
        if( Game_Model.game_mode == Game_Model.Game_Mode.SQUARE )
        {
            game_model.load_Board_Controller(Board_Model.SQUARE);
        }
        else
        {
            game_model.load_Board_Controller( Board_Model.HEXAGON );
        }
        
        
        // ajusto la ventana al modo de juego y la dificultad elejida
        this.setSize(
                game_model.getBoard_controller().getBoard_model().
                        getBoard_view().getBoard_view_container().getWidth() + 6,
                game_model.getBoard_controller().getBoard_model().
                        getBoard_view().getBoard_view_container().getHeight() + 29);
        
        this.setActualView(game_model.getBoard_controller().getBoard_model().
                getBoard_view().getBoard_view_container());
        
        
        // agrego eventos a los componentes del juego
        Board_View view = this.game_model.getBoard_controller().getBoard_model().getBoard_view();
        view.getRestar().addActionListener( this.listener_button );
        view.getMenu().addActionListener( this.listener_button );
        view.getExit().addActionListener( this.listener_button );
    } // fin de startGame

    
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
        this.setLocationRelativeTo(null);
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