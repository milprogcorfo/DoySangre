/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFrame;
import modelo.ModDonacion;
import vista.FrmDonacion;

/**
 *
 * @author Francisco
 */
public class CtrDonacion implements ActionListener, WindowListener {

    modelo.ModbdDonacion mbdDonacion;
    modelo.ModbdDonante mbdDonante;
    modelo.ModbdReceptor mbdReceptor;
    vista.FrmDonacion vDonacion;
    JFrame callWindow;

    public CtrDonacion(modelo.ModbdDonacion mbdDonacion, vista.FrmDonacion vDonacion, JFrame callWindow, modelo.ModbdDonante mbdDonante,  modelo.ModbdReceptor mbdReceptor) {
        this.mbdDonacion = mbdDonacion;
        this.vDonacion = vDonacion;
        this.callWindow = callWindow;
        this.mbdDonante = mbdDonante;        
        this.mbdReceptor = mbdReceptor;

        vDonacion.addWindowListener(this);
        vDonacion.btnBuscarRut.addActionListener(this);
        vDonacion.btnBuscarCodigoD.addActionListener(this);
        vDonacion.btnIngresarD.addActionListener(this);
        vDonacion.btnModificarD.addActionListener(this);
        vDonacion.btnEliminarD.addActionListener(this);
        vDonacion.btnLimpiar.addActionListener(this);
        vDonacion.cboxTipoDonacion.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String cmd = ae.getActionCommand();
        modelo.ModDonacion aux1 = new modelo.ModDonacion();
        modelo.ModDonante aux2 = new modelo.ModDonante();
        modelo.ModReceptor aux3 = new modelo.ModReceptor();
        
        switch (cmd) {
            case "CMD_TDBOX":
                if(vDonacion.cboxTipoDonacion.getSelectedIndex() == 0){
                    vDonacion.txtCodPaciente.setEnabled(false);
                }
                if(vDonacion.cboxTipoDonacion.getSelectedIndex() == 1){
                    vDonacion.txtCodPaciente.setEnabled(true);
                }
                
                System.out.println("Tipo de Donacion");
                break;
                
            case "CMD_FINDR":
                aux2 = mbdDonante.buscar(Integer.parseInt(vDonacion.txtBuscarRut.getText()));
                if (aux2 != null) {
                    vDonacion.displayNombre.setText(aux2.getNombre());
                    vDonacion.displayApellido.setText(aux2.getApellido());
                    vDonacion.displaySexo.setText(aux2.getSexo());
                    vDonacion.displayTipoS.setText(aux2.getTipoS());
                    vDonacion.displayFechaN.setText(aux2.getFechaN());
                    vDonacion.displayCiudad.setText(aux2.getCiudad());
                }
                
                System.out.println("Buscar Rut Donacion");
                break;
                
            case "CMD_FINDD":
                aux1 = mbdDonacion.buscar(Integer.parseInt(vDonacion.txtCodDonacion.getText()));
                aux2 = mbdDonante.buscar(aux1.getRutD());
                int d = -1, c = -1;
                if (aux1 != null && aux2 != null) {                    
                    vDonacion.txtBuscarRut.setText("" + aux1.getRutD());
                    vDonacion.txtFechaD.setText(aux1.getFechaD());
                    vDonacion.txtCodPaciente.setText("" + aux1.getCodigoP());
                    if (aux1.getTipoD().equals("ALTRUISTA")) { d = 0; }
                    if (aux1.getTipoD().equals("REPOSICIÓN")) { d = 1; }
                    vDonacion.cboxTipoDonacion.setSelectedIndex(d);
                    vDonacion.displayNombre.setText(aux2.getNombre());
                    vDonacion.displayApellido.setText(aux2.getApellido());
                    vDonacion.displaySexo.setText(aux2.getSexo());
                    vDonacion.displayTipoS.setText(aux2.getTipoS());
                    vDonacion.displayFechaN.setText(aux2.getFechaN());
                    vDonacion.displayCiudad.setText(aux2.getCiudad());
                    if (aux1.getCodigoC() == 1) { c = 0; }
                    if (aux1.getCodigoC() == 2) { c = 1; }
                    if (aux1.getCodigoC() == 3) { c = 2; }
                    vDonacion.cboxCentro.setSelectedIndex(c);
                }
                
                System.out.println("Buscar Donacion");
                break;
                
            case "CMD_ENTERD":
                aux2 = mbdDonante.buscar(Integer.parseInt(vDonacion.txtBuscarRut.getText()));                
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                aux1.setCodigoD(-1);
                aux1.setFechaD(dateFormat.format(cal.getTime()));
                aux1.setTipoD(vDonacion.cboxTipoDonacion.getSelectedItem().toString());
                
                if(vDonacion.cboxTipoDonacion.getSelectedIndex() == 0){
                    aux1.setCodigoP(-1);
                }
                if(vDonacion.cboxTipoDonacion.getSelectedIndex() == 1){
                    aux1.setCodigoP(Integer.parseInt(vDonacion.txtCodPaciente.getText()));
                    aux3 = mbdReceptor.buscar(Integer.parseInt(vDonacion.txtCodPaciente.getText()));
                }
                
                aux1.setRutD(aux2.getRut());
            
                int auxCC1 = 0;
                if(vDonacion.cboxCentro.getSelectedIndex() == 0){auxCC1 = 1;}
                if(vDonacion.cboxCentro.getSelectedIndex() == 1){auxCC1 = 2;}
                if(vDonacion.cboxCentro.getSelectedIndex() == 2){auxCC1 = 3;} 
                
                aux1.setCodigoC(auxCC1);
                //aux1.setCodigoC(Integer.parseInt(vDonacion.cboxCentro.getSelectedItem().toString()));
                
                if(aux1.getCodigoP() == -1){
                    mbdDonacion.agregar(aux1);
                    vDonacion.txtCodDonacion.setText("" + mbdDonacion.COD);
                    vDonacion.txtFechaD.setText(dateFormat.format(cal.getTime()));                    
                }
                if(aux1.getCodigoP() != -1 && aux2.getTipoS().equals(aux3.getTipoSR())){
                    mbdDonacion.agregar(aux1);
                    vDonacion.txtCodDonacion.setText("" + mbdDonacion.COD);
                    vDonacion.txtFechaD.setText(dateFormat.format(cal.getTime()));                     
                }
                
                System.out.println("Ingresar Donacion");
                break;
                
            case "CMD_MODD":
                aux1.setCodigoD(Integer.parseInt(vDonacion.txtCodDonacion.getText()));
                aux1.setFechaD(vDonacion.txtFechaD.getText());
                aux1.setTipoD(vDonacion.cboxTipoDonacion.getSelectedItem().toString());
                
                if(vDonacion.cboxTipoDonacion.getSelectedIndex() == 0){
                    aux1.setCodigoP(-1);
                }
                if(vDonacion.cboxTipoDonacion.getSelectedIndex() == 1){
                    aux1.setCodigoP(Integer.parseInt(vDonacion.txtCodPaciente.getText()));
                }
                
                int auxCC2 = 0;
                if(vDonacion.cboxCentro.getSelectedIndex() == 0){auxCC2 = 1;}
                if(vDonacion.cboxCentro.getSelectedIndex() == 1){auxCC2 = 2;}
                if(vDonacion.cboxCentro.getSelectedIndex() == 2){auxCC2 = 3;}                
                
                //aux1.setCodigoC(Integer.parseInt(vDonacion.cboxCentro.getSelectedItem().toString()));
                aux1.setCodigoC(auxCC2);
                mbdDonacion.modificar(Integer.parseInt(vDonacion.txtCodDonacion.getText()), aux1);
                
                System.out.println("Modificar Donacion");
                break;
                
            case "CMD_DELETED":
                mbdDonacion.eliminar(Integer.parseInt(vDonacion.txtCodDonacion.getText()));
                System.out.println("Borrar Donacion");
                break;
                
            case "CMD_CLEAR":
                this.vDonacion.txtFechaD.setText("");
                this.vDonacion.txtBuscarRut.setText("");
                this.vDonacion.txtCodDonacion.setText("");
                this.vDonacion.txtCodPaciente.setText("");
                this.vDonacion.displayNombre.setText("-");
                this.vDonacion.displayApellido.setText("-");
                this.vDonacion.displaySexo.setText("-");
                this.vDonacion.displayTipoS.setText("-");
                this.vDonacion.displayFechaN.setText("-");
                this.vDonacion.displayCiudad.setText("-");
                this.vDonacion.cboxCentro.setSelectedIndex(0);
                this.vDonacion.cboxTipoDonacion.setSelectedIndex(1);
                
                System.out.println("Limpiar");
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        callWindow.setEnabled(true);
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}

}
