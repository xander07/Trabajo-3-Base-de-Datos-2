/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Connections.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author moonzenith
 */
public class Main extends javax.swing.JFrame {
  
  ConnectMongo mongoC;
  ConnectSQL sqlC;
  
  /**
   * Creates new form Main
   */
  public Main() {
    initComponents();
    mongoC = new ConnectMongo();
    sqlC = new ConnectSQL();
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
   * content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    btnGetMongoV = new javax.swing.JButton();
    btnSetMongo = new javax.swing.JButton();
    btnGetMongoC = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    btnGetMongoV.setText("Show Total Vendors");
    btnGetMongoV.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnGetMongoVActionPerformed(evt);
      }
    });

    btnSetMongo.setText("Send Data 2 Mongo");
    btnSetMongo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnSetMongoActionPerformed(evt);
      }
    });

    btnGetMongoC.setText("Show Total Clients");
    btnGetMongoC.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnGetMongoCActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(btnGetMongoC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
            .addComponent(btnSetMongo)
            .addGap(0, 0, Short.MAX_VALUE))
          .addComponent(btnGetMongoV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(btnSetMongo)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(btnGetMongoC)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(btnGetMongoV)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void btnGetMongoVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetMongoVActionPerformed
    vendorTable tablaVendedores = new vendorTable();
    tablaVendedores.setVendors(this.mongoC.getTotalesVendedor());
    tablaVendedores.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    tablaVendedores.setVisible(true);
  }//GEN-LAST:event_btnGetMongoVActionPerformed

  private void btnSetMongoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetMongoActionPerformed
    try {
      ResultSet resultClients = this.sqlC.getClientes();
      ResultSet resultVendors = this.sqlC.getVendedores();
      while(resultClients.next()) {
        this.mongoC.saveCliente(resultClients.getInt("CODIGOCLIENTE"), resultClients.getInt("TOTALESCLIENTE"));
      }
      while(resultVendors.next()) {
        this.mongoC.saveVendedor(resultVendors.getInt("CODIGOVENDEDOR"), resultVendors.getInt("TOTALESVENDEDOR"));
      }
    } catch (SQLException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
  }//GEN-LAST:event_btnSetMongoActionPerformed

  private void btnGetMongoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetMongoCActionPerformed
    clientTable tablaClientes = new clientTable();
    tablaClientes.setClients(this.mongoC.getTotalesCliente());
    tablaClientes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    tablaClientes.setVisible(true);
  }//GEN-LAST:event_btnGetMongoCActionPerformed

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
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>
    
    //</editor-fold>
    
    /* Create and display the form */
    java.awt.EventQueue.invokeLater(() -> {
      new Main().setVisible(true);
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnGetMongoC;
  private javax.swing.JButton btnGetMongoV;
  private javax.swing.JButton btnSetMongo;
  // End of variables declaration//GEN-END:variables
}
