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
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import modelo.ModbdReceptor;
import vista.FrmReceptor;

/**
 *
 * @author Francisco
 */
public class CtrReceptor implements ActionListener, WindowListener {

    modelo.ModbdReceptor mbdReceptor;
    vista.FrmReceptor vReceptor;
    JFrame callWindow;

    public CtrReceptor(vista.FrmReceptor vReceptor, modelo.ModbdReceptor mbdReceptor, JFrame callWindow) {
        this.mbdReceptor = mbdReceptor;
        this.vReceptor = vReceptor;
        this.callWindow = callWindow;

        vReceptor.addWindowListener(this);
        vReceptor.btnIngresar.addActionListener(this);
        vReceptor.btnModificar.addActionListener(this);
        vReceptor.btnEliminar.addActionListener(this);
        vReceptor.btnBuscar.addActionListener(this);
        vReceptor.btnLimpiar.addActionListener(this);
        vReceptor.btnListarD.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        String cmd = ae.getActionCommand();
        modelo.ModReceptor aux = new modelo.ModReceptor();
        
        switch (cmd) {
            case "CMD_ADD":
                aux.setRutR(Integer.parseInt(vReceptor.txtRut.getText()));
                aux.setNombreR(vReceptor.txtNombre.getText());
                aux.setApellidoR(vReceptor.txtApellido.getText());
                aux.setTipoSR(vReceptor.cboxTipoS.getSelectedItem().toString()); 
                aux.setRequerimiento(Integer.parseInt(vReceptor.txtRequerimiento.getText()));
                mbdReceptor.agregar(aux);
                
                System.out.println("Agregar Paciente");
                break;
                
            case "CMD_DEL":
                mbdReceptor.eliminar(Integer.parseInt(vReceptor.txtRut.getText()));                
                System.out.println("Eliminar Paciente");
                break;
                
            case "CMD_MOD":
                aux.setNombreR(vReceptor.txtNombre.getText());
                aux.setApellidoR(vReceptor.txtApellido.getText());
                aux.setRequerimiento(Integer.parseInt(vReceptor.txtRequerimiento.getText()));
                aux.setTipoSR(vReceptor.cboxTipoS.getSelectedItem().toString());                
                mbdReceptor.modificar(Integer.parseInt(vReceptor.txtRut.getText()), aux);
                
                System.out.println("Modificar Paciente");
                break;
                
            case "CMD_CLEAR":
                this.vReceptor.txtNombre.setText("");
                this.vReceptor.txtApellido.setText("");
                this.vReceptor.cboxTipoS.setSelectedIndex(0);
                this.vReceptor.txtRequerimiento.setText("");
                this.vReceptor.labelContadorR.setText("-");
                this.vReceptor.tblDonP.setVisible(false);

                System.out.println("Limpiar");
                break;
                
            case "CMD_FIND":
                int tS = -1;
                aux = mbdReceptor.buscar(Integer.parseInt(vReceptor.txtRut.getText()));
                if (aux != null) {
                    vReceptor.txtNombre.setText(aux.getNombreR());
                    vReceptor.txtApellido.setText(aux.getApellidoR());                    
                    vReceptor.txtRequerimiento.setText("" + aux.getRequerimiento());
                    if (aux.getTipoSR().equals("O POSITIVO")) { tS = 0; }
                    if (aux.getTipoSR().equals("O NEGATIVO")) { tS = 1; }
                    if (aux.getTipoSR().equals("A POSITIVO")) { tS = 2; }
                    if (aux.getTipoSR().equals("A NEGATIVO")) { tS = 3; }
                    if (aux.getTipoSR().equals("B POSITIVO")) { tS = 4; }
                    if (aux.getTipoSR().equals("B NEGATIVO")) { tS = 5; }
                    if (aux.getTipoSR().equals("AB POSITIVO")) { tS = 6; }
                    if (aux.getTipoSR().equals("AB NEGATIVO")) { tS = 7; }
                    vReceptor.cboxTipoS.setSelectedIndex(tS);
                    
                    ArrayList<String> don;
                    don = mbdReceptor.buscarDonacionP(Integer.parseInt(vReceptor.txtRut.getText()));
                    int n = don.size() / 6;
                    
                    /*if(Integer.parseInt(vReceptor.txtRequerimiento.getText()) == n){                        
                        vReceptor.labelContadorR.setText("Sin donaciones.");}*/
                    /*if(Integer.parseInt(vReceptor.txtRequerimiento.getText()) - n <= 0){                        
                        vReceptor.labelContadorR.setText("Req. completado.");} */
                    if(Integer.parseInt(vReceptor.txtRequerimiento.getText()) - n > 0){                        
                        vReceptor.labelContadorR.setText("Faltan "
                                + ((Integer.parseInt(vReceptor.txtRequerimiento.getText())) - n) 
                                + " donaciones.");}
                }
                
                System.out.println("Buscar Paciente");
                break;
                
            case "CMD_LISTARD":                
                System.out.println("Listar Donaciones");
                this.vReceptor.tblDonP.setVisible(true);
                ArrayList<String> don;
                DefaultTableModel modelo = (DefaultTableModel)vReceptor.tblDonP.getModel();
                modelo.setRowCount(0);
                don = mbdReceptor.buscarDonacionP(Integer.parseInt(vReceptor.txtRut.getText()));
                int n = don.size() / 6;
                
                for (int i = 0; i < n; i++){                    
                    Object[] fila = new Object[6];

                    fila[0] = don.get(0 + i*6);
                    fila[1] = don.get(1 + i*6);
                    fila[2] = don.get(2 + i*6);
                    fila[3] = don.get(3 + i*6);
                    fila[4] = don.get(4 + i*6);
                    fila[5] = don.get(5 + i*6);

                    modelo.addRow(fila);
                }                       
                System.out.println("Desplegar Listado Donaciones");
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) { }

    @Override
    public void windowClosing(WindowEvent e) {
          callWindow.setEnabled(true);    
    }

    @Override
    public void windowClosed(WindowEvent e) { }

    @Override
    public void windowIconified(WindowEvent e) { }

    @Override
    public void windowDeiconified(WindowEvent e) { }

    @Override
    public void windowActivated(WindowEvent e) { }

    @Override
    public void windowDeactivated(WindowEvent e) { }

}
