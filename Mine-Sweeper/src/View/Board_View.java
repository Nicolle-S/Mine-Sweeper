/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Board_Model;
import Model.Game_Model;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Representa la vista del juego, es decir, el tablero de juego.
 * Contiene todos los elementos graficos e interactivos del tablero
 * @author Nicolle'S
 */
public class Board_View extends JPanel{
    
    final int QUANTITY_IM; //cantidad de imagenes
    final int SIZE_IMAGEN; //tamano de las imagenes
    private JButton [][] tableB; // matriz de celdas
    private ImageIcon [] Im; // imagenes del tablero
    private Board_Model tab; // modelo del tablero
    private JPanel board_view_container; // contenedor de la vista del tablero
    private JLabel marks; // representa las marcas restantes
    private JLabel time; // representa el tiempo de juego
    private JButton restar; // representa el boton de reiniciar
    private JButton menu; // representa el boton de volver al menu
    private JButton exit; // representa el boton de salir del Juego
    
    
    /**
     * Inicializa el tablero de juego
     * @param tab modelo del tablero
     */
    public Board_View(Board_Model tab) 
    {
        // inicializacion de contantes y campos
        QUANTITY_IM = 11;
        SIZE_IMAGEN = 35;
        this.tab = tab;
        
        // inicializa las celdas del juego
        tableB = new JButton[tab.getN_ROWS()][tab.getN_COLS()];
        Initialize_TableB();
        
        // inicializa las imagenes del juego
        Im = new ImageIcon[QUANTITY_IM];
        Load_Images();
        
       // da propiedades al tablero del juego
        this.setLayout(null);
        
        if( Game_Model.game_mode == Game_Model.Game_Mode.SQUARE )
        {
            this.setBounds( 0, Board_Model.size_component, 
                    SIZE_IMAGEN*tab.getN_COLS(),SIZE_IMAGEN*tab.getN_ROWS());
            this.setVisible(true);
        }
        else
        {
            this.setBounds( 0, Board_Model.size_component, 
                    SIZE_IMAGEN*tab.getN_COLS() + 17,SIZE_IMAGEN*tab.getN_ROWS());
            this.setVisible(true); 
        }
        
        // inicio contenedor y componentes
        this.initialize_container();
        this.initialize_components();
        this.load_components();
    } // fin de Board_View
    
    
    private void initialize_container()
    {
        // intancio y doy propiedades al contenedor
        this.board_view_container = new JPanel( null );
        this.board_view_container.setSize( this.getWidth(),
                this.getHeight() + (Board_Model.size_component * 2));
        this.board_view_container.setVisible(true);
    } // fin de initialize_container
    
    
    private void initialize_components()
    {
        Font labels =  new Font("Castellar", Font.BOLD, 36 ); // fuente para labels
        Color labels_color = new Color(160, 5, 9);
        
        // intacio y doy propiedades al label de las marcas
        this.marks = new JLabel(); // se debe modificar el por el numero de minas
        this.setMarks( tab.getN_MINES() );
        this.marks.setBounds( 0, 0, Board_Model.size_component * 2, Board_Model.size_component);
        this.marks.setHorizontalAlignment( JLabel.CENTER );
        this.marks.setVerticalAlignment( JLabel.CENTER );
        this.marks.setFont( labels );
        this.marks.setForeground( labels_color );
        this.marks.setBackground( Color.BLACK );
        this.marks.setOpaque(true);
        
        // intacio y doy propiedades al label del tiempo
        this.time = new JLabel(); // se debe modificar el por el numero de minas
        this.setTime( 0 );
        this.time.setBounds( this.board_view_container.getWidth() - Board_Model.size_component * 2, 0,
                Board_Model.size_component * 2, Board_Model.size_component);
        this.time.setHorizontalAlignment( JLabel.CENTER );
        this.time.setVerticalAlignment( JLabel.CENTER );
        this.time.setFont( labels );
        this.time.setForeground( labels_color );
        this.time.setBackground( Color.BLACK );
        this.time.setOpaque(true);
        
        // instacio y doy propidades al boton de reiniciar
        this.restar = new JButton(); // falta colocar el icono de la carita al boton
        this.restar.setBounds( (this.board_view_container.getWidth() / 2) - (Board_Model.size_component / 2), 0,
                Board_Model.size_component, Board_Model.size_component);
        this.restar.setName("Restar");
            
        // instancio y doy propiedad al boton de menu
        this.menu = new JButton(); 
        this.menu.setBounds( 0, this.board_view_container.getHeight() - Board_Model.size_component,
                Board_Model.size_component * 2, Board_Model.size_component);
        this.menu.setName("Menu");
        this.menu.setText("Menu");
        
        // instancio y doy propiedades al boton salir
        this.exit = new JButton("Exit"); 
        this.exit.setBounds( this.board_view_container.getWidth() - Board_Model.size_component * 2,
                this.board_view_container.getHeight() - Board_Model.size_component,
                Board_Model.size_component * 2, Board_Model.size_component);
        this.exit.setName("Exit");
        this.exit.setText("Exit");
    } // fin de initialize_components
    
    
    private void load_components()
    {
        // cargo todos los elementos del juego al contenedor
        this.board_view_container.add( this );
        this.board_view_container.add( this.marks );
        this.board_view_container.add( this.time );
        this.board_view_container.add( this.restar );
        this.board_view_container.add( this.menu );
        this.board_view_container.add( this.exit );
    } // fin de load_components

    
    /**
     * Retorna las celdas del juego
     * @return celdas del juego
     */
    public JButton[][] getTableB(){return tableB;}

    public Board_Model getTab() {
        return tab;
    }

    public JPanel getBoard_view_container() {
        return board_view_container;
    }

    public JLabel getMarks() {
        return marks;
    }

    public JLabel getTime() {
        return time;
    }

    public JButton getRestar() {
        return restar;
    }

    public JButton getMenu() {
        return menu;
    }

    public JButton getExit() {
        return exit;
    }

    public void setMarks( int marks ) 
    {
        this.marks.setText( (marks < 10 && marks >= 0 ? "0" + String.valueOf( marks ) : String.valueOf( marks ) ) );
    }

    public void setTime( int t ) {
        this.time.setText( ( t < 10 ? "0" + String.valueOf( t ) : String.valueOf( t ) ) );
    }
    
    
    /**
     * Carga el vectos de imagenes con las imagenes necesarias para el tablero
     */
    private void Load_Images()
    {
        try{
        for (int i = 0; i < Im.length; i++)
        {
            if( Game_Model.game_mode == Game_Model.Game_Mode.SQUARE)
            {
                Im[i]=new ImageIcon(getClass().getResource("/Images/"+i+".jpg"));
            }
            else
            {
               Im[i]=new ImageIcon(getClass().getResource("/Images/"+i+"H.png"));
            }
                  
        }
        }catch(NullPointerException eex){
            System.out.println("Problema al cargar las imagenes");
        }  
    } // fin de Load_Images
    
    
    /**
     * Da memoria, posicion y tamano a cada boton del tablero de juego.
     */
    private void Initialize_TableB()
    {
        
        for (int i = 0; i < tab.getN_ROWS(); i++) {
            
            for (int j = 0; j < tab.getN_COLS(); j++) {
                
                tableB[i][j] = new JButton();
                
                if( Game_Model.game_mode == Game_Model.Game_Mode.SQUARE)
                {
                    tableB[i][j].setBounds(SIZE_IMAGEN*j,SIZE_IMAGEN*i,SIZE_IMAGEN,SIZE_IMAGEN);
                }
                else
                {
                    
                    tableB[i][j].setBackground( new Color(71, 70, 48));
                    
                    // para las filas impares
                    if( i % 2 != 0 )
                        tableB[i][j].setBounds(SIZE_IMAGEN*j + 17,SIZE_IMAGEN*i,SIZE_IMAGEN,SIZE_IMAGEN);
                    else
                        tableB[i][j].setBounds(SIZE_IMAGEN*j,SIZE_IMAGEN*i,SIZE_IMAGEN,SIZE_IMAGEN);
                }
                
                
                this.add(tableB[i][j]);   
            }   
        }
    } // fin de Initialize_TableB
    
    
    /**
     * actualiza el tablero con las imagenes que corresponden segun el valor
     * encontrado en el tablero logico.
     */
    public void paint_table(){
        
        for (int i = 0; i < tab.getN_ROWS(); i++) {
            
            for (int j = 0; j < tab.getN_COLS(); j++) {
                
                if(tab.getTable()[i][j] < 10){
                    
                    tableB[i][j].setIcon(Im[tab.getTable()[i][j]]);
                }
                
                if(tab.getTable()[i][j] >= 20){
                    
                    tableB[i][j].setIcon(Im[10]);
                }
                
                if(tab.getTable()[i][j] >=10 && tab.getTable()[i][j] <= 19){
                    
                    tableB[i][j].setIcon(null);
                }
            }   
        }
    } // fin de paint_table
} // fin de Board_View
