/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JButton;

/**
 *
 * @author DilanU
 */
public class Game_view_Menu3 extends javax.swing.JPanel {

    /**
     * Creates new form Game_view_Menu3
     */
    public Game_view_Menu3() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tittle = new javax.swing.JLabel();
        easy = new javax.swing.JButton();
        medium = new javax.swing.JButton();
        hard = new javax.swing.JButton();

        tittle.setFont(new java.awt.Font("Wide Latin", 0, 14)); // NOI18N
        tittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tittle.setText("MinesWipper");

        easy.setFont(new java.awt.Font("Wide Latin", 0, 12)); // NOI18N
        easy.setText("Easy");
        easy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                easyMouseClicked(evt);
            }
        });

        medium.setFont(new java.awt.Font("Wide Latin", 0, 12)); // NOI18N
        medium.setText("Medium");
        medium.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mediumMouseClicked(evt);
            }
        });

        hard.setFont(new java.awt.Font("Wide Latin", 0, 12)); // NOI18N
        hard.setText("Hard");
        hard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hardMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(tittle, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(medium, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(easy, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hard, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tittle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(easy, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(medium, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(hard, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void easyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_easyMouseClicked

    }//GEN-LAST:event_easyMouseClicked

    private void mediumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mediumMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_mediumMouseClicked

    private void hardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hardMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_hardMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton easy;
    private javax.swing.JButton hard;
    private javax.swing.JButton medium;
    private javax.swing.JLabel tittle;
    // End of variables declaration//GEN-END:variables

    public JButton getEasy() {
        return easy;
    }

    public JButton getHard() {
        return hard;
    }

    public JButton getMedium() {
        return medium;
    }

    
    

}