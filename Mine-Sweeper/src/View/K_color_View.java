/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Board_Model;
import Model.K_Color_Model;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Nicolle'S
 */
public class K_color_View extends JPanel{
    
    final int QUANTITY_IM; //cantidad de imagenes
    private ImageIcon [] Im; // imagenes del tablero
    private JButton [][] tableB; // matriz de celdas
    private K_Color_Model tab;
    final int SIZE_IMAGEN; //tamano de las imagenes
    private JPanel kColor_view_container; // contenedor de la vista del tablero
    private JButton menu; // representa el boton de volver al menu
    private JButton restar; // representa el boton de reiniciar
    private JButton exit; // representa el boton de salir del Juego
    private JLabel mines; // representa la cantidad de minas en la GUI
    
    
    
    public K_color_View(K_Color_Model tab) 
    {   
        // inicializacion de contantes y campos
        QUANTITY_IM = 7;
        this.tab = tab;
        SIZE_IMAGEN = 50;
        
        // inicializa las celdas del juego
        tableB = new JButton[tab.getROWS()][tab.getCOLS()];
        Initialize_TableB();
        
        // inicializa las imagenes del juego
        Im = new ImageIcon[QUANTITY_IM];
        Load_Images();
        
        // da propiedades al tablero del juego
        this.setLayout(null);
        this.setBounds( 0, 0, 
                    SIZE_IMAGEN*tab.getCOLS(),SIZE_IMAGEN*tab.getROWS());
        this.setVisible(true);
        
        // inicio contenedor y componentes
        this.initialize_container();
        this.initialize_components();
        this.load_components();
    }

    public ImageIcon[] getIm() {
        return Im;
    }

    public JButton[][] getTableB() {
        return tableB;
    }

    public K_Color_Model getTab() {
        return tab;
    }

    public JPanel getkColor_view_container() {
        return kColor_view_container;
    }

    public JButton getMenu() {
        return menu;
    }

    public JButton getRestar() {
        return restar;
    }

    public JButton getExit() {
        return exit;
    }
    
    
    
    private void initialize_container(){
        
        // intancio y doy propiedades al contenedor
        this.kColor_view_container = new JPanel( null );  
        
        this.kColor_view_container.setSize( this.getWidth() + SIZE_IMAGEN * 4,
                this.getHeight());
        
        this.kColor_view_container.setVisible(true);
    } // fin de initialize_container
    
    
    private void initialize_components()
    {    
        // instancio y doy propiedades al label de minas
        this.mines = new JLabel( "Mines: " + String.valueOf( this.tab.quantity_Mine() ) );
        this.mines.setBounds( 300 , 0,
                K_Color_Model.size_component, K_Color_Model.size_component / 2);
        this.mines.setVerticalAlignment(JLabel.CENTER);
        this.mines.setHorizontalAlignment(JLabel.CENTER);
        
        // instacio y doy propidades al boton de reiniciar
        this.restar = new JButton(); // falta colocar el icono de la carita al boton
        this.restar.setBounds( 300 , 83,
                K_Color_Model.size_component, K_Color_Model.size_component / 2);
        this.restar.setText("Restar");
        this.restar.setName("Restar");
            
        // instancio y doy propiedad al boton de menu
        this.menu = new JButton(); 
        this.menu.setBounds( 300, 216, K_Color_Model.size_component, K_Color_Model.size_component / 2);
//        this.menu.setOpaque(false);
//        this.menu.setContentAreaFilled(false);
//        this.menu.setBorderPainted(false);
        this.menu.setName("Menu");
        this.menu.setText("Menu");
//        this.menu.setIcon(new ImageIcon(this.getClass().getResource("/Images/volverMenu.png")));
        
        // instancio y doy propiedades al boton salir
        this.exit = new JButton("Exit"); 
        this.exit.setBounds( 300, 349,
                K_Color_Model.size_component, K_Color_Model.size_component / 2);
        this.exit.setName("Exit");
        this.exit.setText("Exit");
//        this.exit.setIcon(new ImageIcon(this.getClass().getResource("/Images/salir2.png")));
//        this.exit.setOpaque(false);
//        this.exit.setContentAreaFilled(false);
//        this.exit.setBorderPainted(false);

    
    } // fin de initialize_components
            
    private void load_components()
    {
        // cargo todos los elementos del juego al contenedor
        this.kColor_view_container.add( this );
        this.kColor_view_container.add( this.restar );
        this.kColor_view_container.add( this.menu );
        this.kColor_view_container.add( this.exit );
        this.kColor_view_container.add(this.mines);
    } // fin de load_components
    
    
    private void Load_Images()
    {
        try{
        for (int i = 0; i < Im.length; i++)
        {    
            Im[i]=new ImageIcon(getClass().getResource("/Images/"+i+".png"));         
        }
        }catch(NullPointerException eex){
            System.out.println("Problema al cargar las imagenes");
        }  
    } // fin de Load_Images
    
    
    private void Initialize_TableB()
    {
        
        for (int i = 0; i < tab.getROWS(); i++) {
            
            for (int j = 0; j < tab.getCOLS(); j++) {
                
                tableB[i][j] = new JButton();
                tableB[i][j].setBounds(SIZE_IMAGEN*j,SIZE_IMAGEN*i,SIZE_IMAGEN,SIZE_IMAGEN); 
               
                if(tab.getTable()[i][j] != null){
                    
                    this.add(tableB[i][j]);
                }
                   
            }   
        }
    } // fin de Initialize_TableB
    
    
    public void paint_table(){
        
        for (int i = 0; i < tab.getROWS(); i++) {
            
            for (int j = 0; j < tab.getCOLS(); j++) {
                if(tab.getTable()[i][j] == null)
                    System.out.print("N  ");
                else if( tab.getTable()[i][j].isEdge() )
//                    System.out.print(tab.getTable()[i][j].getValor_Edge() + "  ");
                        System.out.print("A  ");
                else
                    System.out.print((tab.getTable()[i][j].getColor() == -1 ? 
                            "V": tab.getTable()[i][j].getColor() ) + "  ");
                
                if(tab.getTable()[i][j] != null && tab.getTable()[i][j].isEdge() == true){
                    
                    if( tab.getTable()[i][j].getValor_Edge() == -6 ){
                        tableB[i][j].setIcon(Im[0]);
                    }
                    else
                        tableB[i][j].setIcon(Im[ tab.getTable()[i][j].getValor_Edge() ]); // dibujo la arista que corresponde
                }
                
                if(tab.getTable()[i][j] != null && tab.getTable()[i][j].isEdge() ==false && tab.getTable()[i][j].isDiscovered() ){
                    
                    tableB[i][j].setIcon(Im[ tab.getTable()[i][j].getColor() ]);
                }
            }
            System.out.println("");
        }
        
        System.out.println(tab.quantity_Mine());
        tab.printTableMines();
        tab.quantity_Mine();
    } // fin de paint_table   
}
