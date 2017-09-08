/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.Board_View;
import java.util.Random;

/**
 *
 * Esta clase sera la encargarda de manejar todo lo referente a la logica que 
 * debe tener el tablero de juego, el cual consta de una matriz numerica.
 * A su vez posee dos subclases que definen el diseno del tablero que se jugara
 */
public abstract class Board_Model 
{
    private Board_View board_view; // vista del juego
    public static final int SQUARE = 1; // define el juego tradicional
    public static final int HEXAGON = 2; // define el juego hexagon
    protected final int COVER_FOR_CELL; // celda cubierta
    private final int MARK_FOR_CELL; // celda marcada
    protected final int EMPTY_CELL; // celda vacia
    protected final int MINE_CELL; // celda con una mina
    //private final int COVERED_MINE_CELL; // celda cubierta con una mina
    //private final int MARKED_MINE_CELL; // celda marcada que contiene una mina

    private final int N_MINES; // numero de minas
    protected final int N_ROWS; // filas
    protected final int N_COLS; // columnas
    protected int[][] table;
   
    
    /** 
     * Constructor de la Clase. 
     */
    public Board_Model(){
        
        COVER_FOR_CELL = 10; 
        MARK_FOR_CELL = 10; 
        EMPTY_CELL = 0; 
        MINE_CELL = 9; 
        //COVERED_MINE_CELL = MINE_CELL + COVER_FOR_CELL;
        //MARKED_MINE_CELL = COVERED_MINE_CELL + MARK_FOR_CELL;

        N_MINES = 40; 
        N_ROWS = 8; 
        N_COLS = 8; 
        
        table = new int [N_ROWS][N_COLS];
        this.load_Board();
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
