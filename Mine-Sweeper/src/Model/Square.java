/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *Contiene la logica usada en el tablero cuyo diseno es cuadrado, aqui se
 *implementan los metodos abstractos de la clase padre especificas para
 * este tipo de tablero.
 */
public class Square extends Board_Model{

    public Square() 
    {
        super();
        this.Load_adjacent();
        super.cover_all_cell();
        super.load_Board_View();
    }
    
    /**
     * establezco los valores de la cantidad de minas que posee una celda en particular
     */
    @Override
    public void Load_adjacent(){
        
        for (int i = 0; i < N_ROWS; i++) {
            
            for (int j = 0; j < N_COLS; j++) {
                
                if(table[i][j] == 9){
                    
                    /*
                    verifico la celda que se encuenta a la izquiera
                    */
                    if( (j - 1) >= 0 && table[i][j-1] != 9){
                        
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
                    if( (i - 1) >= 0 && table[i-1][j] != 9 ){
                        
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
                    if( (i - 1) >= 0 && (j - 1) >= 0 && table[i-1][j-1] != 9 ){
                        
                        table[i-1][j-1] += 1;
                    }
                    
                    /*
                    verifico la diagonal superior derecha
                    */
                    if( (i - 1) >= 0 && (j + 1) < N_COLS && table[i-1][j+1] != 9 ){
                        
                        table[i-1][j+1] += 1;
                    }
                    
                    /*
                    verifico la diagonal inferior izquierda
                    */
                    if( (i + 1) < N_ROWS && (j - 1) >= 0 && table[i+1][j-1] != 9 ){
                        
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
    
    
    /**
     * recibe la posicion de una celda vacia que fue seleccionada y busca
     * todas las celdas vacias contiguas a esta para descubrirlas
     * 
     * @param i coordenada de la fila de la celda que fue seleccionada
     * @param j coordenada de la columna de la celda que fue seleccionada
     */
    @Override
    public void search_campEmpty(int i, int j){
        
        /**
         * primero que nada destapo la celda en la que estoy ubicada
         */
        table[i][j] -= COVER_FOR_CELL;
         
         
        /**
         * verifico si la celda de encima no contiene mina
         */
        if( (i - 1) >= 0 && table[i-1][j] >= COVER_FOR_CELL && table[i-1][j] < MINE_CELL ){
           
            search_campEmpty(i-1, j); 
        }
        
        /**
         * verifico si la celda de abajo no contiene mina
         */
        if( (i + 1) < N_ROWS && table[i+1][j] >= COVER_FOR_CELL && table[i-1][j] < MINE_CELL  ){
           
            search_campEmpty(i+1, j);    
        }
        
        /**
         * verifico si la celda izquierda no contiene mina
         */
        if( (j - 1) >= 0 && table[i][j-1] >= COVER_FOR_CELL && table[i-1][j] < MINE_CELL  ){
            
            search_campEmpty(i, j-1);
        }
        
        /**
         * verifico si la celda derecha no contiene mina
         */
        if( (j + 1) < N_COLS && table[i][j+1] >= COVER_FOR_CELL && table[i-1][j] < MINE_CELL  ){
            
            search_campEmpty(i, j+1);
        }
        
        /**
         * verifico si la celda superior diagonal izquierda no contiene mina
         */
        if( (j - 1) >= 0 && (i - 1) >= 0 && table[i-1][j-1] >= COVER_FOR_CELL && table[i-1][j] < MINE_CELL  ){
            
            search_campEmpty(i-1, j-1);    
        }
        
        /**
         * verifico si la celda superior diagonal derecha no contiene mina
         */
        if( (j + 1) < N_COLS && (i - 1) >= 0 && table[i-1][j+1] >= COVER_FOR_CELL && table[i-1][j] < MINE_CELL  ){
            
            search_campEmpty(i-1, j+1);   
        }
        
        /**
         * verifico si la celda inferior diagonal izquierda no contiene mina
         */
        if( (j - 1) >= 0 && (i + 1) < N_ROWS && table[i+1][j-1] >= COVER_FOR_CELL && table[i-1][j] < MINE_CELL ){
            
            search_campEmpty(i+1, j-1);    
        }
        
        /**
         * verifico si la celda inferior diagonal derecha no contiene mina
         */
        if( (j + 1) < N_COLS && (i + 1) < N_ROWS && table[i+1][j+1] >= COVER_FOR_CELL && table[i-1][j] < MINE_CELL ){
            
            search_campEmpty(i+1, j+1);
       
        }
    }
    
       
}
