/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moonzenith
 */
public class ConnectSQL {

  final String url = "jdbc:oracle:thin:adminDB2/Un4B3ll3z4@moonzenith.2waky.com:1521:ORCLCDB";
  Connection conn;
  ResultSet vendedores;
  ResultSet clientes;

  public ConnectSQL() {
    try {
      conn = DriverManager.getConnection(url);
    } catch (SQLException ex) {
      Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
    }

    if (conn != null) {
      System.out.println("Connected to SQL!");
    }
  }

  public ResultSet getVendedores() {
    try {
      this.fetchVendedores();
    } catch (SQLException ex) {
      Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
    }
    return this.vendedores;
  }

  public ResultSet getClientes() throws SQLException, SQLException {
    try {
      this.fetchClientes();
    } catch (SQLException ex) {
      Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
    }
    return this.clientes;
  }

  private void fetchVendedores() throws SQLException {
    Statement stmt = this.conn.createStatement();
    vendedores = stmt.executeQuery("SELECT codigovendedor, SUM(detalle.nrounidades * detalle.valorunitario) AS totalesvendedor "
                                + "FROM factura "
                                + "INNER JOIN detalle ON factura.codigofac = detalle.codigofac "
                                + "GROUP BY codigovendedor");
  }

  private void fetchClientes() throws SQLException {
    Statement stmt = this.conn.createStatement();
    clientes = stmt.executeQuery("SELECT codigocliente, SUM(detalle.nrounidades * detalle.valorunitario) AS totalescliente "
                              + "FROM factura "
                              + "INNER JOIN detalle ON factura.codigofac = detalle.codigofac "
                              + "GROUP BY codigocliente");
  }

  public void printClientes() {
    try {
      this.fetchClientes();
      while(clientes.next()) {
        System.out.println(clientes.getInt(1)+" "+clientes.getInt(2));
      }
    } catch (SQLException ex) {
      Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
