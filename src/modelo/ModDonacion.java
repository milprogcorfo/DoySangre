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
public class ModDonacion {
    
    private int codigoD;
    private String fechaD;
    private String tipoD;
    private int codigoP;
    private int codigoC;
    private int rutD;

    public ModDonacion(int codigoD, String fechaD, String tipoD, int codigoP, int codigoC, int rutD) {
        this.codigoD = codigoD;
        this.fechaD = fechaD;
        this.tipoD = tipoD;
        this.codigoP = codigoP;
        this.codigoC = codigoC;
        this.rutD = rutD;
    }

    public ModDonacion() {
        this.codigoD = 0;
        this.fechaD = null;
        this.tipoD = null;
        this.codigoP = 0;
        this.codigoC = 0;
        this.rutD = 0;
    }

    public int getCodigoD() {
        return codigoD;
    }

    public void setCodigoD(int codigoD) {
        this.codigoD = codigoD;
    }

    public String getFechaD() {
        return fechaD;
    }

    public void setFechaD(String fechaD) {
        this.fechaD = fechaD;
    }

    public String getTipoD() {
        return tipoD;
    }

    public void setTipoD(String tipoD) {
        this.tipoD = tipoD;
    }

    public int getCodigoP() {
        return codigoP;
    }

    public void setCodigoP(int codigoP) {
        this.codigoP = codigoP;
    }
    
    public int getRutD() {
        return rutD;
    }

    public void setRutD(int rutD) {
        this.rutD = rutD;
    }
    
    public int getCodigoC() {
        return codigoC;
    }

    public void setCodigoC(int codigoC) {
        this.codigoC = codigoC;
    }
    
    public boolean equals(Object o){
        
        if(((ModDonacion)o).getCodigoD() == this.getCodigoD())
            return true;
        else
            return false;            
    }         
}
