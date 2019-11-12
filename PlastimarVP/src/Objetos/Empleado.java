/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;
import java.sql.ResultSet;
import Conexion.Conexiones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;





/**
 *
 * @author PARRA
 */
public class Empleado implements Conexion.BD{
    private int id_empleado;
    private int id_empresa;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private char sexo;
    private Date fecha_nacimiento;
    private String curp;
    private String telefono;
    private char estado_empleado;
    
    
    PreparedStatement ps;
    ResultSet rs;

    public Empleado(int id_empleado, int id_empresa, String nombre, String apellido_paterno, String apellido_materno, char sexo, Date fecha_nacimiento, String curp, String telefono, char estado_empleado) {
        this.id_empleado = id_empleado;
        this.id_empresa = id_empresa;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.curp = curp;
        this.telefono = telefono;
        this.estado_empleado = estado_empleado;
    }

    public Empleado() {
    }
    
    
    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public char getEstado_empleado() {
        return estado_empleado;
    }

    public void setEstado_empleado(char estado_empleado) {
        this.estado_empleado = estado_empleado;
    }

    @Override
    public void inserta(Object obj, Connection con) {
       Empleado emp =(Empleado)obj;
        try{
            ps=con.prepareStatement("INSERT INTO Empleado(id_empleado,id_empresa,nombre,apellido_paterno,apellido_materno,sexo,fecha_nacimiento,curp,telefono_contacto,estado_empleado) VALUES (?,?,?,?,?,?)");//por seguridad
            ps.setInt(1, emp.getId_empleado());
            ps.setInt(2, emp.getId_empresa());
            ps.setString(3, emp.getNombre());       // Codigo para insertar
            ps.setString(4, emp.getApellido_paterno());
            ps.setString(5, emp.getApellido_materno());
            ps.setObject(6, emp.sexo,java.sql.Types.CHAR);
            ps.setDate(7, (java.sql.Date) emp.getFecha_nacimiento());  // agregar un registro
            ps.setString(8, emp.getCurp());
            ps.setString(9, emp.getTelefono());
            ps.setObject(10, emp.getEstado_empleado(),java.sql.Types.CHAR);
            
            int res=ps.executeUpdate();
            
            if (res>0) {
                JOptionPane.showMessageDialog(null, "Se registro exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "ERROR");
            }
            
        }catch(Exception e){
            System.out.println(e.toString());
        }
        
    }

    @Override
    public void elimina(int id, Connection con) {
     try{
            con = Conexiones.conectar();
            ps = con.prepareStatement("DELETE FROM empleados WHERE id=?"); //Eliminar
            ps.setInt(1,id);
            int res=ps.executeUpdate();
            
            if (res>0) {
                JOptionPane.showMessageDialog(null, "Se elimino exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "ERROR");
            }
            
            
        }catch(Exception e){
            System.out.println(e.toString());
        }   
    }

    @Override
    public Object consultaUnica(int id, Connection con) {
        Empleado emp = new Empleado() ;
        try{
            con = Conexiones.conectar();
            ps = con.prepareStatement("SELECT * FROM empleados WHERE id = ?"); //traer un dato
            ps.setInt(1, id);
            
            rs= ps.executeQuery();
            if (rs.next()) {
                emp.setId_empleado(rs.getInt("id_empleado"));
                emp.setId_empresa(rs.getInt("id_empleado"));
                emp.setNombre(rs.getString("nombre"));
                emp.setApellido_paterno(rs.getString("apellido_paterno"));
                emp.setApellido_materno(rs.getString("apellido_materno"));
                emp.setSexo(rs.getString("sexo").charAt(0));
                emp.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                emp.setCurp(rs.getString("curp"));
                emp.setTelefono(rs.getString("telefono_contacto"));
                emp.setEstado_empleado(rs.getString("estado_empleado").charAt(0));
            }else{
                JOptionPane.showConfirmDialog(null,"No se encontro el registro");
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return emp;
    }

    @Override
    public ArrayList<Object> ConsultaMultiple(Object obj, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
