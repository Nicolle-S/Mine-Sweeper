/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Board_Controller;
import View.Game_View_Menu1;
import View.Game_View_Menu2;
import View.Game_View_Menu3;

/**
 * Almacena y encapsula los datos relevantes del juego
 * @author DilanU
 */
public class Game_Model 
{
    public enum Game_Mode{ SQUARE, HEXAGON} // define el tipo de juego
    public enum Difficulty{ EASY, MEDIUM, HARD } // define las dificultades del juego
    public static Difficulty difficulty; // dificultad del juego
    public static Game_Mode game_mode; // modo de juego
    public static final int WIDTH = 300;
    public static final int HEIGHT = 400;
    private final Game_View_Menu1 main_menu;
    private final Game_View_Menu2 mines_menu;
    private final Game_View_Menu3 difficulty_menu;
    private Board_Controller board_controller;
    
    
    /**
     * Inicialia el modelo del juego
     */
    public Game_Model() 
    {
        Game_Model.difficulty = null;
        Game_Model.game_mode = null;
        this.main_menu = new Game_View_Menu1();
        this.mines_menu = new Game_View_Menu2();
        this.difficulty_menu = new Game_View_Menu3();
    } // fin del Game_Model
    
    
    /**
     * Intanscia el controlador del tablero con el modo de juego elegido
     * @param modoJuego 
     */
    public void load_Board_Controller( int modoJuego )
    {
        this.board_controller = new Board_Controller( modoJuego );
    }

    /**
     * Retorna el board_controller
     * @return board_controller
     */
    public Board_Controller getBoard_controller() 
    {
        return board_controller;
    }
    
    
    /**
     * Retorna la vista del menu principal
     * @return panel del menu principal
     */
    public Game_View_Menu1 getMain_menu() 
    {
        return main_menu;
    } // fin de getMain_menu

    
    /**
     * Retorna la vista del menu secundario
     * @return panel del menu secundario
     */
    public Game_View_Menu2 getMines_menu() 
    {
        return mines_menu;
    } // fin de getMines_menu

    
    /**
     * Retorna la vista del menu de dificultad
     * @return panel de dificultad
     */
    public Game_View_Menu3 getDifficulty_menu() {
        return difficulty_menu;
    } // fin de getDifficulty_menu
} // fin de Game_Model