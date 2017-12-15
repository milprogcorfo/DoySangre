package modelo;

public class ModDonante {
    
    private String nombre;
    private String apellido;
    private int rut;
    private String tipoS;
    private String sexo;
    private String ciudad;
    private String fechaN;
    private String telefono;
    private String email;
    private String fechaD;

    public ModDonante(String nombre, String apellido, int rut, String tipoS, String sexo, String ciudad, String fechaN, String telefono, String email, String fechaD) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.tipoS = tipoS;
        this.sexo = sexo;
        this.ciudad = ciudad;
        this.fechaN = fechaN;
        this.telefono = telefono;
        this.email = email;
        this.fechaD = "";
    }     
    
    public ModDonante() {
        this.nombre = null;
        this.apellido = null;
        this.rut = 0;
        this.tipoS = null;
        this.sexo = null;
        this.ciudad = null;
        this.fechaN = null;
        this.telefono = null;
        this.email = null;
        this.fechaD = "";
    } 

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public String getTipoS() {
        return tipoS;
    }

    public void setTipoS(String tipoS) {
        this.tipoS = tipoS;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFechaN() {
        return fechaN;
    }

    public void setFechaN(String fechaN) {
        this.fechaN = fechaN;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFechaD() {
        return fechaD;
    }

    public void setFechaD(String fechaD) {
        this.fechaD = fechaD;
    }
    
    public boolean equals(Object o){
        
        if(((ModDonante)o).getRut() == this.getRut())
            return true;
        else
            return false;
            
    }
    
}
