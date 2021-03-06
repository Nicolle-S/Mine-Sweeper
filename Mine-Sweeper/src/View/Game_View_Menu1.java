/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Game_Model;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *  Representa el menu principal de la palicacion
 * @author DilanU
 */
public class Game_View_Menu1 extends javax.swing.JPanel 
{
    // Variables declaration - do not modify                     
    private javax.swing.JButton exit;
    private javax.swing.JButton kColor;
    private javax.swing.JButton mines;
    //private javax.swing.JLabel tittle;
    // End of variables declaration     

    /**
     * Creates new form Game_view_menu1
     */
    public Game_View_Menu1() 
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        mines = new javax.swing.JButton();
        kColor = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        
        mines.setName("MinesWipper");
        mines.setBounds(15, 350, 249, 89);
        mines.setIcon(new ImageIcon(this.getClass().getResource("/Images/Boton1.png")));
        
        kColor.setName("K-Color");
        kColor.setBounds(330, 350, 249, 89);
        kColor.setIcon(new ImageIcon(this.getClass().getResource("/Images/Boton2.png")));

        exit.setName("Exit");
        exit.setBounds(250, 455, 100, 100);
        exit.setIcon(new ImageIcon(this.getClass().getResource("/Images/salir.png")));
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);


        this.setSize( Game_Model.WIDTH, Game_Model.HEIGHT);
        this.setLayout(null);
        this.add(this.mines);
        this.add(this.kColor);
        this.add(this.exit);
        
    }// </editor-fold>                        


    /**
     * Devuelve el boton para salir
     * @return boton para salir
     */
    public JButton getExit() {
        return exit;
    }
    
    
    /**
     * Devuelve el boton del KColor
     * @return boton del KColor
     */
    public JButton getkColor() {
        return kColor;
    }

    
    /**
     * Devuelve el boton de mineswipper
     * @return boton de mineswipper
     */
    public JButton getMines() {
        return mines;
    }
    
    @Override
    public void paint(Graphics g) {
        
        g.drawImage( new ImageIcon(getClass().getResource("/Images/fondo.png")).getImage(), 0, 0, getWidth(), getHeight(),
                        this);
 
        setOpaque(false);
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        
        
    }
    
} // fin de Game_View_Menu1
