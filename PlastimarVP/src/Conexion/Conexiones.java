package Conexion;








import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class Conexiones {

    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String url = "jdbc:mysql://localhost:3306/horario";
    public static String user = "root";
    public static String password = "";

    public static Connection conectar() {

        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName(driver).newInstance();  //Definicion del driver 

        } catch (Exception ex) {
            // handle the error
        }

        Connection conn = null;
        try {

            conn = DriverManager.getConnection(url, user, password);

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

        return conn;
    }

    public static void desconectar(Connection con) throws SQLException {
        try{
        con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public Resultset consultas(Connection conn, String query) {

        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);//"SELECT * FROM persona where id=2

            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...
            if (stmt.execute(query)) {//"SELECT * FROM persona where id=2"
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

        return (Resultset) rs;
    }

    public static Boolean conss(String nombre, String contra) {
        Connection con = Conexiones.conectar();
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = con.prepareStatement("INSERT INTO cat_usuario (usuario,contrasenia,semestre_actual,bactive,correo) VALUES (?,?,?,?,?)");
            ps.setString(1, nombre);
            ps.setString(2, contra);
            ps.setInt(3, 1);       
            ps.setBoolean(4, true);
            ps.setString(5, contra);  
            int res = ps.executeUpdate();

            if (res > 0) {
                System.out.println("Se registro exitosamente");
                con.close();
                return true;

            } else {
                System.out.println("error");
                con.close();
                return false;
            }

            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }
}
