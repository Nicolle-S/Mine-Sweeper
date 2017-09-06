/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Random;

/**
 *
 * Esta clase sera la encargarda de manejar todo lo referente a la logica que debe tener el tablero de juego, el cual consta de una matriz numerica.
 */
public class Board_Model {
    
    private final int COVER_FOR_CELL; // cubierta
    private final int MARK_FOR_CELL; // celda marcada
    private final int EMPTY_CELL; // celda vacia
    private final int MINE_CELL; // celda con una mina
    private final int COVERED_MINE_CELL; // celda cubierta con una mina
    private final int MARKED_MINE_CELL; // celda marcada que contiene una mina

    private final int N_MINES; // numero de minas
    private final int N_ROWS; // filas
    private final int N_COLS; // columnas
    
    private int[][] table;

    public Board_Model(){
        
        COVER_FOR_CELL = 10; 
        MARK_FOR_CELL = 10; 
        EMPTY_CELL = 0; 
        MINE_CELL = 9; 
        COVERED_MINE_CELL = MINE_CELL + COVER_FOR_CELL;
        MARKED_MINE_CELL = COVERED_MINE_CELL + MARK_FOR_CELL;

        N_MINES = 40; 
        N_ROWS = 16; 
        N_COLS = 16; 
        
        table= new int [N_ROWS][N_COLS];
    }
    
    /*
    Metodo encargado de cargar la matriz con las minas, las celdas vacias y los valores adyacentes de cada casilla.
    */
    public void load_Board(){
        
        Random a = new Random();
        int X, Y, mines = 0;
        
        /*
        primeramente inicializo en cero y cubiertas todas las casillas
        */
        for (int i = 0; i < N_ROWS; i++) {
            
            for (int j = 0; j < N_COLS; j++) {
                
                table[i][j] = 0;
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
                
            }while(table[X][Y] != 0);

            table[X][Y] = MINE_CELL;
            mines ++;
              
        }while(mines != N_MINES );
        
    }
    
    /*
    establezco los valores de la cantidad de minas que posee una celda en particular
    */
    public void Load_adjacent(){
        
        for (int i = 0; i < N_ROWS; i++) {
            
            for (int j = 0; j < N_COLS; j++) {
                
                if(table[i][j] == 9){
                    
                    /*
                    verifico la celda que se encuenta a la izquiera
                    */
                    if( (j - 1) > 0 && table[i][j-1] != 9){
                        
                        table[i][j-1] += 1;
                    }
                    
                    /*
                    verifico la celda que se encuenta a la derecha
                    */
                    if( (j + 1) < N_COLS && table[i][j+1] != 9){
                        
                        table[i][j+1] += 1;
                    }
                    
                    /*
                    verifico la celda que esta encima de la mina
                    */
                    if( (i - 1) > 0 && table[i-1][j] != 9 ){
                        
                        table[i-1][j] += 1;
                    }
                    
                    /*
                    verifico la celda que esta debajo de la mina
                    */
                    if( (i + 1) < N_ROWS && table[i+1][j] != 9 ){
                        
                        table[i+1][j] += 1;
                    }
                    
                    /*
                    verifico la diagonal superior izquierda
                    */
                    if( (i - 1) > 0 && (j - 1) > 0 && table[i-1][j-1] != 9 ){
                        
                        table[i-1][j-1] += 1;
                    }
                    
                    /*
                    verifico la diagonal superior derecha
                    */
                    if( (i - 1) > 0 && (j + 1) < N_COLS && table[i-1][j+1] != 9 ){
                        
                        table[i-1][j+1] += 1;
                    }
                    
                    /*
                    verifico la diagonal inferior izquierda
                    */
                    if( (i + 1) < N_ROWS && (j - 1) > 0 && table[i+1][j-1] != 9 ){
                        
                        table[i+1][j-1] += 1;
                    }
                    
                    /*
                    verifico la diagonal inferior derecha
                    */
                    if( (i + 1) < N_ROWS && (j + 1) < N_COLS && table[i+1][j+1] != 9 ){
                        
                        table[i+1][j+1] += 1;
                    }
                }   
            }   
        }    
    }
    
    public void Print_Board(){
        
        for (int i = 0; i < N_ROWS; i++) {
            
            System.out.println("");
            
            for (int j = 0; j < N_COLS; j++) {
                
                System.out.print(" "+table[i][j]);
            }
        }
    }    
    
    
}
