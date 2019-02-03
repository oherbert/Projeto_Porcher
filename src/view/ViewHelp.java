/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Util.Config;
import Util.ConfigList;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author Herbert
 */
public class ViewHelp extends javax.swing.JDialog {

    /**
     * Creates new form ViewHelp
     */
    public ViewHelp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtLink.setText(Config.loadConfig(ConfigList.CloudFolder));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        linkLabel = new javax.swing.JLabel();
        txtLink = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Para acessar os registros remotamente abra o link:");

        linkLabel.setBackground(new java.awt.Color(204, 204, 204));
        linkLabel.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        linkLabel.setForeground(new java.awt.Color(255, 0, 0));
        linkLabel.setText("Ou click aqui!");
        linkLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                linkLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                linkLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                linkLabelMouseExited(evt);
            }
        });

        txtLink.setEditable(false);
        txtLink.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        txtLink.setText("Link");
        txtLink.setCaretColor(new java.awt.Color(240, 240, 240));
        txtLink.setDisabledTextColor(new java.awt.Color(240, 240, 240));
        txtLink.setSelectedTextColor(new java.awt.Color(255, 0, 0));
        txtLink.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLinkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(linkLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtLink))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(linkLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void linkLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linkLabelMouseClicked
        try {
		
		Desktop.getDesktop().browse(new URI(Config.loadConfig(ConfigList.CloudFolder)));
		
	} catch (IOException | URISyntaxException e1) {
            System.out.println("Erro ao tentar abri a Url: " + e1.getMessage());
	}
    }//GEN-LAST:event_linkLabelMouseClicked

    private void linkLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linkLabelMouseEntered
        linkLabel.setBackground(Color.red);
        linkLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_linkLabelMouseEntered

    private void linkLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linkLabelMouseExited
        linkLabel.setBackground(Color.gray);
    }//GEN-LAST:event_linkLabelMouseExited

    private void txtLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLinkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLinkActionPerformed

 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewHelp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewHelp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewHelp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewHelp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewHelp dialog = new ViewHelp(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel linkLabel;
    private javax.swing.JTextField txtLink;
    // End of variables declaration//GEN-END:variables
}
