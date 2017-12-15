/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Francisco
 */
public class ModbdDonacion extends BD{
    
    public static int COD = 0;
    
    public ModbdDonacion(){
        
    }          
    
    public ModDonacion buscar(int cod){
        
        try {
            PreparedStatement st = con.prepareStatement(
                    "SELECT fechaD, tipoD, codigoP, donante_rut, centro_idcentro "
                    + "FROM donacion "
                    + "WHERE codigoD = ?");

            st.setInt(1, cod);
            ResultSet rs = st.executeQuery();
            System.err.println(""+ rs);

            if (rs.next()) {
                ModDonacion md = new ModDonacion();
                md.setFechaD(rs.getString("fechaD"));
                md.setTipoD(rs.getString("tipoD"));
                md.setCodigoP(rs.getInt("codigoP"));
                md.setRutD(rs.getInt("donante_rut"));
                md.setCodigoC(rs.getInt("centro_idcentro"));
                st.close();
                return md;
            }
            return null;
            
        } catch (SQLException se) {
            System.err.println("ERROR SELECT " + se.getMessage());
        }
        return null;     
        
    }
    
    public boolean agregar(ModDonacion d){
        
        try {
            PreparedStatement st = con.prepareStatement( 
                    "INSERT INTO donacion(fechaD, tipoD, codigoP, donante_rut, centro_idcentro) " +
                    "VALUES (?,?,?,?,?) ");                                          
            
            st.setString(1, d.getFechaD());
            st.setString(2, d.getTipoD());
            st.setInt(3, d.getCodigoP());            
            st.setInt(4, d.getRutD());
            st.setInt(5, d.getCodigoC());
            
            boolean res1 = st.execute();           
            st.close();
            
            PreparedStatement st2 = con.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet rs2 = st2.executeQuery();
            if (rs2.next()) { COD = rs2.getInt("LAST_INSERT_ID()"); st2.close(); }
            
            PreparedStatement st3 = con.prepareStatement(                     
                    "UPDATE donante " +
                    "SET fechaD = ? " + 
                    "WHERE rut = ? " );
                                          
            st3.setString(1, d.getFechaD());
            st3.setInt(2, d.getRutD());
            
            int res3  = st3.executeUpdate();                
            st3.close();
            System.out.println("ingreso de ultima donacion " + res3 );            
            
            return res1;            
            
        } catch (SQLException se){            
            System.err.println("ERROR EXECUTE: " + se.getMessage());
        }
        return false;
    }    
       
    public boolean eliminar(int cod){
        
        try{            
            PreparedStatement sentencia = con.prepareStatement(
                    "DELETE FROM donacion "
                    + "WHERE codigoD = ?"            
            );
            
            sentencia.setInt(1, cod);
            int resultado = sentencia.executeUpdate();
            
        }catch (SQLException se) {
            System.err.println("ERROR SELECT " + se.getMessage());
        }              
        return false;
    }    
       
    public boolean modificar(int cod, ModDonacion d){
        
        try {
            PreparedStatement sentencia = con.prepareStatement(
                    "UPDATE donacion "
                    + "SET fechaD = ?, tipoD = ?, "
                    + "codigoP = ? "
                    + "WHERE codigoD = ?"
            );
            
            sentencia.setString(1, d.getFechaD());
            sentencia.setString(2, d.getTipoD());
            sentencia.setInt(3, d.getCodigoP());
            sentencia.setInt(4, d.getCodigoD());            
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
    
    public int[] donaciones(int cod){
        
        try {
            
            PreparedStatement st1 = con.prepareStatement(
                    "SELECT c.codigoD, c.centro_idcentro, e.rut, e.tipoS, e.fechaD " +
                    "FROM bloodbank4.donacion c, bloodbank4.donante e " +
                    "WHERE c.donante_rut = e.rut and c.codigoP = -1 " +
                    "and c.centro_idcentro = ? and e.tipoS = ? ");
                        
            st1.setInt(1, cod);
            st1.setString(2, "O POSITIVO");            
            ResultSet rs = st1.executeQuery();
            //System.err.println(""+ rs);            
            int aux1[] = new int[10];
            int aux2 = 0;            
            while (rs.next()){ aux2++;}
            aux1[0] = aux2;            
            rs.close();
            st1.close();
            
            PreparedStatement st2 = con.prepareStatement(
                    "SELECT c.codigoD, c.centro_idcentro, e.rut, e.tipoS, e.fechaD " +
                    "FROM bloodbank4.donacion c, bloodbank4.donante e " +
                    "WHERE c.donante_rut = e.rut and c.codigoP = -1 " +
                    "and c.centro_idcentro = ? and e.tipoS = ? ");
                        
            st2.setInt(1, cod);
            st2.setString(2, "O NEGATIVO");            
            rs = st2.executeQuery();
            //System.err.println(""+ rs);            
            aux2 = 0;            
            while (rs.next()){ aux2++;}
            aux1[1] = aux2;            
            rs.close();
            st2.close();
            
            PreparedStatement st3 = con.prepareStatement(
                    "SELECT c.codigoD, c.centro_idcentro, e.rut, e.tipoS, e.fechaD " +
                    "FROM bloodbank4.donacion c, bloodbank4.donante e " +
                    "WHERE c.donante_rut = e.rut and c.codigoP = -1 " +
                    "and c.centro_idcentro = ? and e.tipoS = ? ");
                        
            st3.setInt(1, cod);
            st3.setString(2, "A POSITIVO");            
            rs = st3.executeQuery();
            //System.err.println(""+ rs);            
            aux2 = 0;            
            while (rs.next()){ aux2++;}
            aux1[2] = aux2;            
            rs.close();
            st3.close();
            
            PreparedStatement st4 = con.prepareStatement(
                    "SELECT c.codigoD, c.centro_idcentro, e.rut, e.tipoS, e.fechaD " +
                    "FROM bloodbank4.donacion c, bloodbank4.donante e " +
                    "WHERE c.donante_rut = e.rut and c.codigoP = -1 " +
                    "and c.centro_idcentro = ? and e.tipoS = ? ");
                        
            st4.setInt(1, cod);
            st4.setString(2, "A NEGATIVO");            
            rs = st4.executeQuery();
            //System.err.println(""+ rs);            
            aux2 = 0;            
            while (rs.next()){ aux2++;}
            aux1[3] = aux2;            
            rs.close();
            st4.close();
            
            PreparedStatement st5 = con.prepareStatement(
                    "SELECT c.codigoD, c.centro_idcentro, e.rut, e.tipoS, e.fechaD " +
                    "FROM bloodbank4.donacion c, bloodbank4.donante e " +
                    "WHERE c.donante_rut = e.rut and c.codigoP = -1 " +
                    "and c.centro_idcentro = ? and e.tipoS = ? ");            
            
            st5.setInt(1, cod);
            st5.setString(2, "B POSITIVO");            
            rs = st5.executeQuery();
            //System.err.println(""+ rs);            
            aux2 = 0;            
            while (rs.next()){ aux2++;}
            aux1[4] = aux2;            
            rs.close();
            st5.close();
            
            PreparedStatement st6 = con.prepareStatement(
                    "SELECT c.codigoD, c.centro_idcentro, e.rut, e.tipoS, e.fechaD " +
                    "FROM bloodbank4.donacion c, bloodbank4.donante e " +
                    "WHERE c.donante_rut = e.rut and c.codigoP = -1 " +
                    "and c.centro_idcentro = ? and e.tipoS = ? ");
                        
            st6.setInt(1, cod);
            st6.setString(2, "B NEGATIVO");            
            rs = st6.executeQuery();
            //System.err.println(""+ rs);            
            aux2 = 0;            
            while (rs.next()){ aux2++;}
            aux1[5] = aux2;            
            rs.close();
            st6.close();
            
            PreparedStatement st7 = con.prepareStatement(
                    "SELECT c.codigoD, c.centro_idcentro, e.rut, e.tipoS, e.fechaD " +
                    "FROM bloodbank4.donacion c, bloodbank4.donante e " +
                    "WHERE c.donante_rut = e.rut and c.codigoP = -1 " +
                    "and c.centro_idcentro = ? and e.tipoS = ? ");            
            
            st7.setInt(1, cod);
            st7.setString(2, "AB POSITIVO");            
            rs = st7.executeQuery();
            //System.err.println(""+ rs);            
            aux2 = 0;            
            while (rs.next()){ aux2++;}
            aux1[6] = aux2;            
            rs.close();
            st7.close();
            
            PreparedStatement st8 = con.prepareStatement(
                    "SELECT c.codigoD, c.centro_idcentro, e.rut, e.tipoS, e.fechaD " +
                    "FROM bloodbank4.donacion c, bloodbank4.donante e " +
                    "WHERE c.donante_rut = e.rut and c.codigoP = -1 " +
                    "and c.centro_idcentro = ? and e.tipoS = ? ");            
            
            st8.setInt(1, cod);
            st8.setString(2, "AB NEGATIVO");            
            rs = st8.executeQuery();
            //System.err.println(""+ rs);            
            aux2 = 0;            
            while (rs.next()){ aux2++;}
            aux1[7] = aux2;            
            rs.close();
            st8.close();
            
            PreparedStatement st9 = con.prepareStatement(
                    "SELECT c.codigoD, c.centro_idcentro, e.rut, e.tipoS, e.fechaD " +
                    "FROM bloodbank4.donacion c, bloodbank4.donante e " +
                    "WHERE e.rut = c.donante_rut and c.centro_idcentro = ? and c.codigoP != -1 ");           
            
            st9.setInt(1, cod);                       
            rs = st9.executeQuery();
            //System.err.println(""+ rs);            
            aux2 = 0;            
            while (rs.next()){ aux2++;}
            aux1[8] = aux2;            
            rs.close();
            st9.close();
            
            return aux1;
            
        } catch (SQLException se){
            System.err.println("ERROR SELECT " + se.getMessage());
        }
                
        return null;
    }    

}
