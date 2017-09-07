/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Board_Controller;
import View.Game_View_Menu1;
import View.Game_View_Menu2;

/**
 * Almacena y encapsula los datos relevantes del juego
 * @author DilanU
 */
public class Game_Model 
{
    public static final int WIDTH = 300;
    public static final int HEIGHT = 400;
    private Game_View_Menu1 main_menu;
    private Game_View_Menu2 mines_menu;
    private Board_Controller board_controller;
    
    
    /**
     * Inicialia el modelo del juego
     */
    public Game_Model() 
    {
        this.main_menu = new Game_View_Menu1();
        this.mines_menu = new Game_View_Menu2();
    } // fin del Game_Model

    
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
    
    
} // fin de Game_Model
