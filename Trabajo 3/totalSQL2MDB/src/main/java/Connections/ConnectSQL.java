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

  public ConnectSQL() {
    try {
      conn = DriverManager.getConnection(url);
    } catch (SQLException ex) {
      Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    if(conn != null) {
      System.out.println("Connected to SQL!");
      try {
        mostrarData();
      } catch (SQLException ex) {
        Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  private void mostrarData() throws SQLException {
    Statement stmt = this.conn.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT codigovendedor, SUM(detalle.nrounidades * detalle.valorunitario) AS totalesvendedor FROM factura INNER JOIN detalle ON factura.codigofac = detalle.codigofac GROUP BY codigovendedor");
    while(rs.next()){
      System.out.println(rs.getInt(1)+" "+rs.getInt(2));
    }
  }
  
  
}
