package Conexion;


import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PARRA
 */
public class Conectar {

    public static void main(String[] args) {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

        } catch (Exception ex) {
            // handle the error
        }

        Connection conn = null;
        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/escuela", "root", "");

            if (conn != null) {
                System.out.println("Conexion exitosa");
            }
            // Do something with the Connection
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        //-------------------------------------Consultas---------------------------------------------------
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM persona where id=2");

            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...
            if (stmt.execute("SELECT * FROM persona where id=2")) {
                rs = stmt.getResultSet();
            }
            if (rs.next()) { // una sola linea
                JOptionPane.showMessageDialog(null, rs.getString("nombre") + " " + rs.getString("domicilio"));
            } else {
                JOptionPane.showMessageDialog(null, "No existen datos");
            }


            // Now do something with the ResultSet ....
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

//        PreparedStatement ps;
//        Resultset rs = null;
//
//        try {
//            
//            ps = (PreparedStatement) conn.prepareStatement("SELECT * FROM persona");
//            rs = (Resultset) ps.executeQuery();
//            for (int i = 0; i < 5; i++) {
//                System.out.println(rs.getColumnDefinition().getColumnLabelToIndex());    
//            }
//            
//            if(rs.hasRows())
//            
//            
//            if (rs.next()) { // una sola linea
//                JOptionPane.showMessageDialog(null, rs.getResultId() + " " + rs.getRows());
//            } else {
//                JOptionPane.showMessageDialog(null, "No existen datos");
//            }
//            stmt = conn.createStatement();
//            rs = (Resultset) stmt.executeQuery("SELECT * FROM persona");
//
//            // or alternatively, if you don't know ahead of time that
//            // the query will be a SELECT...
////            if (stmt.execute("SELECT *FROM persona")) {
////                rs = (Resultset) stmt.getResultSet();
////            }
//            
//            if (rs.next()) { // una sola linea
//                JOptionPane.showMessageDialog(null, rs.getString("nombre") + " " + rs.getString("domicilio"));
//            } else {
//                JOptionPane.showMessageDialog(null, "No existen datos");
//            }
        // Now do something with the ResultSet ....
//        } catch (SQLException ex) {
//            // handle any errors
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//        }
//    }
    }
}
