/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Nicolle'S
 */
public class Cell_Color_Model {
    
    int color;
    int[] Xadjacent;
    int[] Yadjacent;
    boolean Mine;
    boolean discovered;
    boolean edge;
    int valor_Edge;

    public Cell_Color_Model() {
        
        color = -1;
        Xadjacent = new int[5];
        Yadjacent = new int[5];
        Mine = false;
        discovered = false;
        boolean edge = false;
        Initialize_Adjacent();
    }

    public int getColor() {
        return color;
    }

    public int[] getXadjacent() {
        return Xadjacent;
    }

    public int[] getYadjacent() {
        return Yadjacent;
    }

    public boolean isMine() {
        return Mine;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public boolean isEdge() {
        return edge;
    }

    public int getValor_Edge() {
        return valor_Edge;
    }
    
    private void Initialize_Adjacent(){
        
        for (int i = 0; i < 5; i++) {
            
            Xadjacent[i] = -1;   
            Yadjacent[i] = -1;   
            
        }
    }
    
    
    
}
