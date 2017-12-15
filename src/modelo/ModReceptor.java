/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Francisco
 */
public class ModReceptor {
    
    private int rutR;
    private String nombreR;
    private String apellidoR;
    private String tipoSR;
    private int requerimiento;

    public ModReceptor() {
        this.rutR = 0;
        this.nombreR = null;
        this.apellidoR = null;
        this.tipoSR = null;
        this.requerimiento = 0;
    }

    public ModReceptor(int rutR, String nombreR, String apellidoR, String tipoSR, int requerimiento) {
        this.rutR = rutR;
        this.nombreR = nombreR;
        this.apellidoR = apellidoR;
        this.tipoSR = tipoSR;
        this.requerimiento = requerimiento;
    }

    public int getRequerimiento() {
        return requerimiento;
    }

    public void setRequerimiento(int requerimiento) {
        this.requerimiento = requerimiento;
    }

    public int getRutR() {
        return rutR;
    }

    public void setRutR(int rutR) {
        this.rutR = rutR;
    }

    public String getNombreR() {
        return nombreR;
    }

    public void setNombreR(String nombreR) {
        this.nombreR = nombreR;
    }

    public String getApellidoR() {
        return apellidoR;
    }

    public void setApellidoR(String apellidoR) {
        this.apellidoR = apellidoR;
    }

    public String getTipoSR() {
        return tipoSR;
    }

    public void setTipoSR(String tipoSR) {
        this.tipoSR = tipoSR;
    }
    
    public boolean equals(Object o){
        
        if(((ModReceptor)o).getRutR() == this.getRutR())
            return true;
        else
            return false;            
    }
    
    
    
}
