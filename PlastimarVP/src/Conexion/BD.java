package Conexion;


import java.sql.Connection;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PARRA
 */
public interface BD {
    public void inserta (Object obj,Connection con);
    public void elimina (int id,Connection con);
    public Object consultaUnica (int id,Connection con);
    public ArrayList<Object> ConsultaMultiple (Object obj,Connection con);
}
