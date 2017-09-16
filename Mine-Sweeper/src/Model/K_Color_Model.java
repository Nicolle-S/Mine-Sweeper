/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.Board_View;
import View.K_color_View;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Nicolle'S
 */
public class K_Color_Model {
    
    public enum StateGame{ WIN, GAME_OVER, PLAY } //determina el estado de juego
    public static StateGame state; // estado del juego
    final int TOTAL_MINE;
    final int YELLOW;
    final int BLUE;
    final int RED;
    final int HORIZONTAL;
    final int VERTICAL;
    final int EMPTY;
    final int COLS;
    final int ROWS;
    int Diag;
    int Mines;
    public static final int size_component = 50; // tamano de los elemtos de la GUI del tablero
    private K_color_View kColor_view;
    private Cell_Color_Model[][] table;
    
    
    public K_Color_Model() 
    {
        
        K_Color_Model.state = K_Color_Model.StateGame.PLAY;
        TOTAL_MINE = 8;
        YELLOW = 1;
        BLUE = 2;
        RED = 3;
        HORIZONTAL = 4;
        VERTICAL = 5;
        Diag = 6;
        ROWS = 9;
        COLS = 5;
        table = new Cell_Color_Model[ROWS][COLS];
        EMPTY = 0;
        Mines = 0;
        
        this.generateTable();
        this.kColor_view = new K_color_View(this);
    }
    
    
    public Cell_Color_Model[][] getTable() {
        return table;
    }

    public int getCOLS() {
        return COLS;
    }

    public int getROWS() {
        return ROWS;
    }

    public K_color_View getColor_view() {
        return kColor_view;
    }

    
        
        protected void load_kColor_View()
    {
        this.kColor_view = new K_color_View( this );
    }
    
    /**
     * hago todos los objetos nulos, para luego si darle memoria a solo
     * las celdas que voy a usar
     */
    public void InitializeTable(){
        
        for (int i = 0; i < ROWS; i++) {
            
            for (int j = 0; j < COLS; j++) {
                
                table[i][j] = null;
            }
            
        }
    }
    
    /**
     * establece cuales seran las celdas que representaran las aristas del tablero y les da su correspondiente valor.
     */
    public void Load_edge(){
        
        for (int i = 0; i < ROWS; i++) {
            
            if( i % 2 != 0){
                Diag *= -1;
            }
            
            for (int j = 0; j < COLS; j++) {
                
                /**
                 * ubico las aristas horizontales
                 */
                if( (i % 4) == 0 && (j % 2) != 0){
                    
                    table[i][j] = new Cell_Color_Model();
                    table[i][j].edge = true;
                    table[i][j].valor_Edge = HORIZONTAL;
                }
                    
                
                /**
                 * ubico las aristas verticales
                 */
                if( ( j == 0 || j == (COLS - 1) ) && ( (i % 2) != 0 ) ) {
                    
                    table[i][j] = new Cell_Color_Model();
                    table[i][j].edge = true;
                    table[i][j].valor_Edge = VERTICAL;
                }
                
                /**
                 * ubico las diagonales
                 */
                if( (i % 2 != 0) && (j % 2 != 0) ){
                    
                    table[i][j] = new Cell_Color_Model();
                    table[i][j].edge = true;
                    table[i][j].valor_Edge = Diag;
                    Diag *= -1;
                }       
            }   
        }   
    }
    
    /**
     * Carga cada vertice del tablero con uno de los tres colores permitidos.
     */
    public boolean Load_Colors( int i, int j ){
        Scanner in = new Scanner(System.in);
        Random nums = new Random();
        int[] colors = new int[3];
        boolean invalid;
        int newi = i;
        int newj = j;

        
        for( int c = 0; c < 3; c++ )
        {   
            do{
                
                colors[c] = nums.nextInt(3) + 1;
                invalid = false;

                for( int v = c - 1; v >= 0; v-- )
                {
                    if( colors[c] == colors[v] )
                        invalid = true;
                }
            }while( invalid );
        }
        
        // si se llego al final de la matriz retorna true
        
        for( int c = 0; c < 3; c++ ){
            table[i][j].color = colors[c];
            
            // chequeo si es una mina, aumenta las minas y si se pasan hago continue
            if( quantity_Mine() > TOTAL_MINE )
                continue;
            
            // aumetar la i y la j
            if( j == 4 )
            {
                newi = i + 2;
                newj = 0;
            }
            else
            {
                newj = j + 2;
            }
            
            if( newi > 8 )
                return true;
            
            
            if(Load_Colors( newi, newj))
                return true;
        }
        
        // retorno false
        this.printTable();
        System.out.println(this.quantity_Mine());
//        in.next();
        
        table[i][j].color = -1;
        table[i][j].Mine = false;
        restart();
        
        return false;
    }
    
    public void restart(){
        
        for (int i = 0; i < ROWS; i++) {
            
            for (int j = 0; j < COLS; j++) {
                
                if( i % 2 == 0 && j % 2 ==0){
                    
                    table[i][j].Mine = false;
                    
                }
                
            }
            
        }
        
        
        
        
        
        
    }
    
    private void printTable()
    {
        for (int i = 0; i < ROWS; i++) {
            
            for (int j = 0; j < COLS; j++) {
                if(table[i][j] == null)
                    System.out.print("N  ");
                else if( table[i][j].isEdge() )
//                    System.out.print(tab.getTable()[i][j].getValor_Edge() + "  ");
                        System.out.print("A  ");
                else
                    System.out.print((table[i][j].getColor() == -1 ? 
                            "V": table[i][j].getColor() ) + "  ");
            }
            System.out.println("");
        }
    }
    
    public void Load_adjacent(){
        
        int pos = 0;
        
        for (int i = 0; i < ROWS; i++) {
            
            for (int j = 0; j < COLS; j++) {
//                System.out.println("-----[" + i + "/" + j + "]----------");
                pos = 0;
                
                if( i % 2 ==0 && j % 2 == 0){ // si estoy parada en un vertice
                    
                    table[i][j] = new Cell_Color_Model();
                    
                    if( (i - 1) >= 0 && table[i-1][j] != null ){ // si esto se cumple entonces el vertice de arriba es un vertice adyacente
//                        System.out.println("ARR");
//                        System.out.println(pos);
                        table[i][j].Xadjacent[pos] = (i-2);
                        table[i][j].Yadjacent[pos] = j;
//                        System.out.println(table[i][j].Xadjacent[pos] + "/" + table[i][j].Yadjacent[pos] );
//                        System.out.println("----------------------------------------");
                        pos ++;
                    }
                    
                    if( (i + 1) < ROWS && table[i+1][j] != null ){ // si esto se cumple entonces el vertice de abajo es un vertice adyacente
//                        System.out.println("ABJ");
//                        System.out.println(pos);
                        table[i][j].Xadjacent[pos] = (i+2);
                        table[i][j].Yadjacent[pos] = j;
//                        System.out.println(table[i][j].Xadjacent[pos] + "/" + table[i][j].Yadjacent[pos] );
//                         System.out.println("----------------------------------------");
                        pos ++;
                    }
                    
                    if( (j - 1) >= 0 && table[i][j-1] != null){ //quiere decir que el vertice a la izquiera de este es adyacente
//                        System.out.println("I");
//                        System.out.println(pos);
                        table[i][j].Xadjacent[pos] = i;
                        table[i][j].Yadjacent[pos] = (j-2);
//                        System.out.println(table[i][j].Xadjacent[pos] + "/" + table[i][j].Yadjacent[pos] );
//                         System.out.println("----------------------------------------");
                        pos ++; 
                        
                    }
                    
                    if( (j + 1) < COLS && table[i][j+1] != null){ //quiere decir que el vertice a la derecha de este es adyacente
//                        System.out.println("D");
//                        System.out.println(pos);
                        table[i][j].Xadjacent[pos] = i;
                        table[i][j].Yadjacent[pos] = (j+2);
//                        System.out.println(table[i][j].Xadjacent[pos] + "/" + table[i][j].Yadjacent[pos] );
//                         System.out.println("----------------------------------------");
                        pos ++; 
                        
                    }
                    
                    if( (( (j==0) || (j == COLS-1)) && (i % 4 == 0)) || ((j == 2) && ( i == 2 || i == 6)) ){
                        
                        if( (i - 1) >= 0 && (j - 1) >=0 && table[i-1][j-1] != null ){ // hay vertice adyacente en la diagonal superior izquierda
//                            System.out.println("DSI");
//                            System.out.println(pos);
                            table[i][j].Xadjacent[pos] = i-2;
                            table[i][j].Yadjacent[pos] = (j-2);
//                            System.out.println(table[i][j].Xadjacent[pos] + "/" + table[i][j].Yadjacent[pos] );
//                             System.out.println("----------------------------------------");
                            pos ++;
                        }
                    
                        if( (i - 1) >= 0 && (j + 1) < COLS && table[i-1][j+1] != null ){ // hay vertice adyacente en la diagonal superior derecha
//                            System.out.println("DSD");
//                            System.out.println(pos);
                            table[i][j].Xadjacent[pos] = i-2;
                            table[i][j].Yadjacent[pos] = (j+2);
//                            System.out.println(table[i][j].Xadjacent[pos] + "/" + table[i][j].Yadjacent[pos] );
//                             System.out.println("----------------------------------------");
                            pos ++;
                        }
                    
                        if( (i + 1) < ROWS && (j - 1) >= 0 && table[i+1][j-1] != null ) { // adyaencia en la diagonal inferior izquierda
//                            System.out.println("DII");
//                            System.out.println(pos);
                            table[i][j].Xadjacent[pos] = i+2;
                            table[i][j].Yadjacent[pos] = (j-2);
//                            System.out.println(table[i][j].Xadjacent[pos] + "/" + table[i][j].Yadjacent[pos] );
//                             System.out.println("----------------------------------------");
                            pos ++;
                        }

                        if( (i + 1) < ROWS && (j + 1) < COLS && table[i+1][j+1] != null ) { // adyaencia en la diagonal inferior derecha
//                            System.out.println("DID");
//                            System.out.println(pos);
                            table[i][j].Xadjacent[pos] = i+2;
                            table[i][j].Yadjacent[pos] = (j+2);
//                            System.out.println(table[i][j].Xadjacent[pos] + "/" + table[i][j].Yadjacent[pos] );
//                             System.out.println("----------------------------------------");
                            pos ++;
                        }
  
                    }
                    
                }
            }
            
        }
        
    }
    
    public int quantity_Mine(){
        
        int mines = 0;
        
        for (int i = 0; i < ROWS; i++) {
            
            for (int j = 0; j < COLS; j++) {
                
                for (int k = 0; table[i][j] != null && k < table[i][j].Xadjacent.length; k++) {
                    
                    if(table[i][j].Xadjacent[k] == -1){
                        
                        break;
                    }
//                    System.out.println("i=" + i + "/" + "j=" + j + "/" + "k=" + k + "/" +
//                            "x=" + table[i][j].Xadjacent[k] + "/" + "y=" + table[i][j].Yadjacent[k]);
                    /**
                     * verifico si hay un vertice que contenga un vertice adyacente con el mismo color
                     */
                    if( (i % 2 ) == 0 && (j % 2 )== 0 && table[i][j].color != -1 && table[i][j].color == table[table[i][j].Xadjacent[k]][table[i][j].Yadjacent[k]].color){
                        
                        table[i][j].Mine = true;
                        table[ table[i][j].Xadjacent[k] ] [ table[i][j].Yadjacent[k] ].Mine =true;
                    }
                }
            }
        }
        
        /**
         * Cuento ahora cuantas minas se me generaron
         */
        for (int i = 0; i < ROWS; i++) {
            
            for (int j = 0; j < COLS; j++) {
                
                if( (i % 2 ) == 0 && (j % 2 )== 0 && table[i][j].Mine == true)
                    mines++;   
            }    
        }
        
        return mines;
    }
    
    /**
     * genera el tablero de juego las veces que sean necesarias hasta que 
     * consiga una distribucion de colores en donde solo hayan la cantidad
     * de minas establecidas en "TOTAL_MINE"
     */
    public void generateTable(){
        
        InitializeTable(); // inicializo todo en vacio
        Load_edge(); // doy memoria y valor a las que son aristas
        Load_adjacent(); // doy memoria a los que son vertices y establezco que valor de arista tienen 
        
        Load_Colors(0, 0);
    }
    
    /**
     * destapa la celda que fue seleccionada por el jugador para ver que 
     * color contiene.
     * 
     * @param i coordenada de la fila en donde dieron clik 
     * @param j coordenada de la columna
     */
    public void Discovered_cell(int i, int j){
        
        table[i][j].discovered = true;
    }
    
    public void check_Mine(int i, int j){
        
        /**
         * si la celda destapada por el jugador es una mina, verifico si la otra
         * mina esta tambien destapada.
         */
        if( table[i][j].Mine == true){
            
            for (int k = 0; k < table[i][i].Xadjacent.length; k++) {
                
                if(table[i][j].Xadjacent[k] == -1){
                    return;
                }
                
                if(table[ table[i][j].Xadjacent[k] ][ table[i][j].Yadjacent[k] ].Mine == true
                   && table[ table[i][j].Xadjacent[k] ][ table[i][j].Yadjacent[k] ].discovered == true 
                   && table[i][j].color == table[ table[i][j].Xadjacent[k] ][ table[i][j].Yadjacent[k] ].color ){
                    
                    K_Color_Model.state = StateGame.GAME_OVER;
                    JOptionPane.showMessageDialog(null, "HAS DESCUBIERTO DOS VERTICES QUE VIOLAN LA REGLA DE COLOR. PERDISTE");
                }
                
            }
        }
    }
    
    public void check_win(){
        
        for (int i = 0; i < ROWS; i++) {
            
            for (int j = 0; j < COLS; j++) {
                
                /**
                 * si hay una celda que no es una mina y ademas aun no ha sido 
                 * destapada, aun no ha ganado
                 */
                if(table[i][j].Mine == false && table[i][j].discovered == false){
                    return;
                }
                
                /**
                 * si hay una mina que esta aun cubierta y tiene minas adyacentes
                 * que tambien estan cubiertas aun no ha ganado. debe destapar al menos 
                 * las minas adyacentes que no violan la configuracion del color
                 * 
                 */
                if(table[i][j].Mine == true && table[i][j].discovered == false){
                    
                    for (int k = 0; k < table[i][j].Xadjacent.length; k++) {
                        
                        if(table[i][j].Xadjacent[k] == -1){
                            break;
                        }
                        
                        if(table[ table[i][j].Xadjacent[k] ][ table[i][j].Yadjacent[k] ].Mine == true
                           && table[ table[i][j].Xadjacent[k] ][ table[i][j].Yadjacent[k] ].discovered == false ){
                           
                            return;
                        }   
                    }
                }
            }
        }
        K_Color_Model.state = StateGame.WIN;
        JOptionPane.showMessageDialog(null, "FELICIDADES, GANASTE");
    }
    
 
}

