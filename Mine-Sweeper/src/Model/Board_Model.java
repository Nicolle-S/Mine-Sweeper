/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.Board_View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * Esta clase sera la encargarda de manejar todo lo referente a la logica que 
 * debe tener el tablero de juego, el cual consta de una matriz numerica.
 * A su vez posee dos subclases que definen el diseno del tablero que se jugara
 */
public abstract class Board_Model 
{
    public enum StateGame{ WIN, GAME_OVER, PLAY } //determina el estado de juego
    public static StateGame state; // estado del juego
    private Board_View board_view; // vista del juego
    public static final int SQUARE = 1; // define el juego tradicional
    public static final int HEXAGON = 2; // define el juego hexagon
    protected final int COVER_FOR_CELL; // celda cubierta y vacia
    private final int MARK_FOR_CELL; // celda marcada
    protected final int EMPTY_CELL; // celda vacia
    protected final int MINE_CELL; // celda con una mina
    protected final int COVERED_MINE_CELL; // celda cubierta con una mina
    //private final int MARKED_MINE_CELL; // celda marcada que contiene una mina
    public static final int size_component = 50; // tamano de los elemtos de la GUI del tablero
    protected final int N_MINES; // numero de minas
    protected final int N_ROWS; // filas
    protected final int N_COLS; // columnas
    protected int[][] table;
    private final Timer chrono; // revisa el cronometro
    public static boolean startGame; // determina si el juego ya se inicio
   
    
    /** 
     * Inicializa el modelo del tablero, dando los valores iniciales
     */
    public Board_Model(){
        Board_Model.startGame = false;
        Board_Model.state = StateGame.PLAY;
        COVER_FOR_CELL = 10; 
        MARK_FOR_CELL = 10; 
        EMPTY_CELL = 0; 
        MINE_CELL = 9; 
        COVERED_MINE_CELL = MINE_CELL + COVER_FOR_CELL;
        //MARKED_MINE_CELL = COVERED_MINE_CELL + MARK_FOR_CELL;


        
        if( Game_Model.difficulty != Game_Model.Difficulty.EASY )
            if( Game_Model.difficulty == Game_Model.Difficulty.MEDIUM )
            {
                N_MINES = 40; 
                N_ROWS = 15;
                N_COLS = 15;
            }
            else
            {
                N_MINES = 82;
                N_ROWS = 15;
                N_COLS = 25;
            }
        else {
            N_MINES = 9;
            N_ROWS = 8;
            N_COLS = 8;
        }
        
        table = new int [N_ROWS][N_COLS];
        this.load_Board();
        
        
        this.chrono = new Timer( 1000, new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int act_time = Integer.parseInt( board_view.getTime().getText() );
                board_view.setTime(++act_time);
            }
        });
    }

    public int getN_MINES() {
        return N_MINES;
    }
    

    public int getN_ROWS() {
        return N_ROWS;
    }

    public int getN_COLS() {
        return N_COLS;
    }

    public int[][] getTable() {
        return table;
    }

    public Board_View getBoard_view() {
        return board_view;
    }

    public int getCOVER_FOR_CELL() {
        return COVER_FOR_CELL;
    }

    public Timer getChrono() {
        return chrono;
    }
    
    
    
    protected void load_Board_View()
    {
        board_view = new Board_View( this );
    }
    
    
    /**
     * Metodo encargado de cargar la matriz con las minas, las celdas vacias y los valores adyacentes de cada casilla.
     */
    private void load_Board(){
        
        Random a = new Random();
        int X, Y, mines = 0;
        
        /*
        primeramente cargamos la matriz con celdas vacias
        */
        for (int i = 0; i < N_ROWS; i++) {
            
            for (int j = 0; j < N_COLS; j++) {
                
                table[i][j] = EMPTY_CELL;
            }
            
        }
        
        /*
        cargo las minas de forma aleatoria
        */
        do{
                
            do{

                X = a.nextInt()%N_ROWS;
                Y = a.nextInt()%N_COLS;

                if(X < 0 )
                    X *= -1;

                if(Y < 0)
                    Y *= -1;
                
            }while(table[X][Y] != EMPTY_CELL);

            table[X][Y] = MINE_CELL;
            mines ++;
              
        }while(mines != N_MINES );
        
    }
    
    
    /**
     * Cubro de manera logica el valor de todas las casillas de la matriz
     */
    protected void cover_all_cell(){
        
        for (int i = 0; i < N_ROWS; i++) {
            
            for (int j = 0; j < N_COLS; j++) {
                
                table[i][j] += COVER_FOR_CELL;
            }   
        }  
    }
    
    public void discover_cell(int i, int j)
    {
        int nro_marks; // numero de marcas actuales
        nro_marks = Integer.parseInt( this.board_view.getMarks().getText() );
        
        
        // si la celda esta marcada
        if( table[i][j] >= 20 )
        {
            this.board_view.setMarks(++nro_marks);
        }
        
        table[i][j] -= COVER_FOR_CELL;
        
    }
    
    public void Mark_cell(int i, int j)
    {
        int nro_marks; // numero de marcas actuales
        nro_marks = Integer.parseInt( this.board_view.getMarks().getText() );
        
        /**
         * La celda ya esta marcada, entonces la desmarco
         */
        if( table[i][j] >= 20)
        {
            table[i][j] -= MARK_FOR_CELL;
            this.board_view.setMarks(++nro_marks);
        }
        
        /**
         * de otro modo la marco
         */
        else
        {
            table[i][j] += MARK_FOR_CELL;
            this.board_view.setMarks(--nro_marks);
        }
   
    }
   
    
    /**
     * establezco los valores de la cantidad de minas que posee una celda en particular
     */
    abstract protected void Load_adjacent();
    
    
    /**
     * recibe la posicion de una celda vacia que fue seleccionada y busca
     * todas las celdas vacias contiguas a esta para descubrirlas
     * 
     * @param i coordenada de la fila de la celda que fue seleccionada
     * @param j coordenada de la columna de la celda que fue seleccionada
     */
    abstract public void search_campEmpty(int i, int j);   
}
