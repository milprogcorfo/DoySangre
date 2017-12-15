package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static modelo.BD.con;

/**
 *
 * @author USUARIO
 */
public class ModbdDonante extends BD{
    
    public ModbdDonante(){
        
    }    
    
    public boolean agregar(ModDonante d){
        try {
            PreparedStatement st = con.prepareStatement( 
                    "INSERT INTO donante(rut, nombre, apellido, tipoS, sexo, fechaN, ciudad, tel, email)"+
                    "VALUES (?,?,?,?,?,?,?,?,?)");                        
            st.setInt(1, d.getRut());
            st.setString(2, d.getNombre().toUpperCase());
            st.setString(3, d.getApellido().toUpperCase());
            st.setString(4, d.getTipoS().toUpperCase());            
            st.setString(5, d.getSexo().toUpperCase());
            st.setString(6, d.getFechaN());
            st.setString(7, d.getCiudad().toUpperCase());
            st.setString(8, d.getTelefono());
            st.setString(9, d.getEmail());
            
            boolean res = st.execute();
            st.close();
            return res;            
            
        } catch (SQLException se){            
            System.err.println("ERROR EXECUTE: " + se.getMessage());
        }
        return false;
    }
    
       
    public boolean eliminar(int rut){
        
        try{            
            PreparedStatement sentencia = con.prepareStatement(
                    "DELETE FROM donante "
                    + "WHERE rut = ?"            
            );
            
            sentencia.setInt(1,rut);
            int resultado = sentencia.executeUpdate();
            
        }catch (SQLException se) {
            System.err.println("ERROR SELECT " + se.getMessage());
        }              
        return false;
    }
    
    public ModDonante buscar(int cod){
        
        try {
            PreparedStatement st = con.prepareStatement(
                    "SELECT rut, nombre, apellido, tipoS , sexo, fechaN, ciudad, tel, email "
                    + "FROM donante "
                    + "WHERE rut = ?");

            st.setInt(1, cod);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                ModDonante md = new ModDonante();
                md.setRut(rs.getInt("rut"));
                md.setNombre(rs.getString("nombre"));
                md.setApellido(rs.getString("apellido"));
                md.setTipoS(rs.getString("tipoS"));                
                md.setSexo(rs.getString("sexo"));
                md.setFechaN(rs.getString("fechaN"));
                md.setCiudad(rs.getString("ciudad"));
                md.setTelefono(rs.getString("tel"));
                md.setEmail(rs.getString("email"));
                st.close();
                return md;
            }
            return null;
            
        } catch (SQLException se) {
            System.err.println("ERROR SELECT " + se.getMessage());
        }
        return null;

    }
       
    public boolean modificar(int rut, ModDonante d){
       
        try {
            PreparedStatement sentencia = con.prepareStatement(
                    "UPDATE donante "
                    + "SET nombre = ?, apellido = ?, " //+ "SET rut = ?, nombre = ?, apellido = ?, "
                    + "tipoS = ?, sexo = ?, fechaN = ?, "
                    + "ciudad = ?, tel = ?, email = ? "
                    + "WHERE rut = ?"
            );

            //sentencia.setInt(1, d.getRut());
            sentencia.setString(1, d.getNombre().toUpperCase());
            sentencia.setString(2, d.getApellido().toUpperCase());
            sentencia.setString(3, d.getTipoS().toUpperCase());
            sentencia.setString(4, d.getSexo().toUpperCase());
            sentencia.setString(5, d.getFechaN());
            sentencia.setString(6, d.getCiudad().toUpperCase());
            sentencia.setString(7, d.getTelefono());
            sentencia.setString(8, d.getEmail());
            sentencia.setInt(9, rut);
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
    
    public ArrayList<ModDonante> buscarTodos(){
        try {
            PreparedStatement st = con.prepareStatement(
                    "SELECT rut, nombre, apellido, tipoS, sexo, fechaN, ciudad, tel, email " +
                    "FROM datos "                   
            );
            ResultSet rs = st.executeQuery();
            ArrayList<ModDonante> resultado = new ArrayList<>();
            while (rs.next()){
                ModDonante aux = new ModDonante();
                aux.setRut(rs.getInt("rut"));
                aux.setNombre(rs.getString("nombre"));
                aux.setApellido(rs.getString("Apellido"));
                aux.setTipoS(rs.getString("tipoS"));
                aux.setSexo(rs.getString("sexo"));
                aux.setFechaN(rs.getString("fechaN"));
                aux.setCiudad(rs.getString("ciudad"));
                aux.setTelefono(rs.getString("tel"));
                aux.setEmail(rs.getString("email"));
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
