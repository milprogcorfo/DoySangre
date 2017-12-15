/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static modelo.BD.con;

/**
 *
 * @author Francisco
 */
public class ModbdReceptor extends BD {

    public ModbdReceptor() {

    }

    public ModReceptor buscar(int rut) {

        try {
            PreparedStatement st = con.prepareStatement(
                    "SELECT nombre, apellido, tipoS, requerimiento "
                    + "FROM receptor "
                    + "WHERE rut = ?");

            st.setInt(1, rut);
            ResultSet rs = st.executeQuery();
            //System.err.println("" + rs);

            if (rs.next()) {
                ModReceptor mr = new ModReceptor();
                mr.setRutR(rut);
                mr.setNombreR(rs.getString("nombre"));
                mr.setApellidoR(rs.getString("apellido"));
                mr.setTipoSR(rs.getString("tipoS"));
                mr.setRequerimiento(rs.getInt("requerimiento"));
                
                st.close();
                return mr;
            }
            return null;

        } catch (SQLException se) {
            System.err.println("ERROR SELECT " + se.getMessage());
        }
        return null;
    }

    public boolean agregar(ModReceptor r) {
        try {
            PreparedStatement st = con.prepareStatement(
                    "INSERT INTO receptor(rut, nombre, apellido, tipoS, requerimiento) "
                    + "VALUES (?,?,?,?,?)");
            st.setInt(1, r.getRutR());
            st.setString(2, r.getNombreR().toUpperCase());
            st.setString(3, r.getApellidoR().toUpperCase());
            st.setString(4, r.getTipoSR().toUpperCase());
            st.setInt(5, r.getRequerimiento());

            boolean res = st.execute();
            st.close();
            
            return res;

        } catch (SQLException se) {
            System.err.println("ERROR EXECUTE: " + se.getMessage());
        }
        return false;
    }

    public boolean eliminar(int rut) {

        try {
            PreparedStatement sentencia = con.prepareStatement(
                    "DELETE FROM receptor "
                    + "WHERE rut = ?"
            );

            sentencia.setInt(1, rut);
            int resultado = sentencia.executeUpdate();

        } catch (SQLException se) {
            System.err.println("ERROR SELECT " + se.getMessage());
        }
        return false;
    }
    
    public boolean modificar(int rut, ModReceptor r){
       
        try {
            PreparedStatement sentencia = con.prepareStatement(
                    "UPDATE receptor "
                    + "SET nombre = ?, apellido = ?, "
                    + "tipoS = ?, requerimiento = ? "
                    + "WHERE rut = ?"
            );

            sentencia.setString(1, r.getNombreR().toUpperCase());
            sentencia.setString(2, r.getApellidoR().toUpperCase());
            sentencia.setString(3, r.getTipoSR());
            sentencia.setInt(4, r.getRequerimiento());
            sentencia.setInt(5, rut);
           
            int resultado = sentencia.executeUpdate();
            sentencia.close();

            if (resultado > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException se) {
            System.err.println("ERROR: "+ se.getMessage());
        }        
        return false;
    }
    
    public ArrayList<String> buscarDonacionP(int rut){
        try {
            PreparedStatement st = con.prepareStatement(
                    "SELECT c.codigoD, c.centro_idcentro, c.fechaD, c.donante_rut, e.nombre, e.apellido " +
                    "FROM bloodbank4.donacion c, bloodbank4.receptor p, bloodbank4.donante e " +
                    "WHERE p.rut = ? and c.codigoP = p.rut and c.donante_rut = e.rut");  
            
            st.setInt(1, rut);
            
            ResultSet rs = st.executeQuery();
            ArrayList<String> resultado = new ArrayList<>();
            while (rs.next()){
                String aux = "";
                aux = "" + rs.getInt("c.codigoD");
                resultado.add(aux);
                aux = "" + rs.getInt("c.centro_idcentro");
                resultado.add(aux);
                aux = rs.getString("c.fechaD");
                resultado.add(aux);
                aux = "" + rs.getInt("c.donante_rut");
                resultado.add(aux);
                aux = rs.getString("e.nombre");
                resultado.add(aux);
                aux = rs.getString("e.apellido");
                resultado.add(aux);
            }
            rs.close();
            st.close();
            return resultado;
        } catch (SQLException se){
            System.err.println("ERROR SELECT " + se.getMessage());
        }
                
        return null;
    }     
}
