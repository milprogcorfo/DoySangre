
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;


public class CtrDonante implements ActionListener, WindowListener,  MouseListener {
    
    modelo.ModbdDonante mbdDonante;
    vista.FrmDonante vDonante;
    JFrame callWindow;

    public CtrDonante(vista.FrmDonante vDonante, modelo.ModbdDonante mbdDonante, JFrame callWindow) {
        this.mbdDonante = mbdDonante;
        this.vDonante = vDonante;
        this.callWindow = callWindow;
        
        vDonante.addWindowListener(this);
        vDonante.btnIngresar.addActionListener(this);
        vDonante.btnLimpiar.addActionListener(this);
        vDonante.btnEliminar.addActionListener(this);
        vDonante.btnModificar.addActionListener(this);
        vDonante.btnBuscar.addActionListener(this);
        vDonante.btnListar.addActionListener(this);
        vDonante.tblDonante.addMouseListener(this);
    }  

    @Override
    public void actionPerformed(ActionEvent ae) {                
        String cmd = ae.getActionCommand();
        modelo.ModDonante aux = new modelo.ModDonante();      
        
        switch (cmd) {
            case "CMD_ADD":                
                aux.setNombre(vDonante.txtNombre.getText());
                aux.setApellido(vDonante.txtApellido.getText());
                aux.setRut(Integer.parseInt(vDonante.txtRut.getText()));
                aux.setTipoS(vDonante.cboxTipoSangre.getSelectedItem().toString());
                aux.setSexo(vDonante.cboxSexo.getSelectedItem().toString());
                aux.setCiudad(vDonante.txtCiudad.getText());
                aux.setTelefono(vDonante.txtTel.getText());
                aux.setEmail(vDonante.txtEmail.getText());
                aux.setFechaN(vDonante.txtFechaN.getText());
                mbdDonante.agregar(aux);
                
                System.out.println("Agregar Donante");
                break;
                
            case "CMD_DEL":
                mbdDonante.eliminar(Integer.parseInt(vDonante.txtRut.getText()));
                System.out.println("Eliminar Donante");
                break;
                
            case "CMD_MOD":                
                aux.setNombre(vDonante.txtNombre.getText());
                aux.setApellido(vDonante.txtApellido.getText());
                aux.setRut(Integer.parseInt(vDonante.txtRut.getText()));
                aux.setTipoS(vDonante.cboxTipoSangre.getSelectedItem().toString());
                aux.setSexo(vDonante.cboxSexo.getSelectedItem().toString());
                aux.setCiudad(vDonante.txtCiudad.getText());
                aux.setTelefono(vDonante.txtTel.getText());
                aux.setEmail(vDonante.txtEmail.getText());
                aux.setFechaN(vDonante.txtFechaN.getText());
                mbdDonante.modificar(Integer.parseInt(vDonante.txtBuscarRut.getText()), aux);
                
                System.out.println("Modificar Donante");
                break;
                
            case "CMD_CLEAR":
                this.vDonante.txtNombre.setText("");
                this.vDonante.txtApellido.setText("");
                this.vDonante.txtRut.setText("");
                this.vDonante.cboxSexo.setSelectedIndex(0);
                this.vDonante.cboxTipoSangre.setSelectedIndex(0);
                this.vDonante.txtFechaN.setText("");
                this.vDonante.txtCiudad.setText("");
                this.vDonante.txtTel.setText("");
                this.vDonante.txtEmail.setText("");
                
                System.out.println("Limpiar");
                break;
                
            case "CMD_FIND":
                int tS = -1, s = -1;
                aux = mbdDonante.buscar(Integer.parseInt(vDonante.txtBuscarRut.getText()));
                if (aux != null) {
                    vDonante.txtNombre.setText(aux.getNombre());
                    vDonante.txtApellido.setText(aux.getApellido());
                    vDonante.txtRut.setText("" + aux.getRut());
                    vDonante.txtFechaN.setText(aux.getFechaN());
                    vDonante.txtCiudad.setText(aux.getCiudad());
                    vDonante.txtTel.setText(aux.getTelefono());
                    vDonante.txtEmail.setText(aux.getEmail());
                    
                    if (aux.getSexo().equals("VAR")) { s = 0;}
                    if (aux.getSexo().equals("MUJ")) { s = 1;}
                    vDonante.cboxSexo.setSelectedIndex(s);
                    
                    if (aux.getTipoS().equals("O POSITIVO")) { tS = 0;}
                    if (aux.getTipoS().equals("O NEGATIVO")) { tS = 1;}
                    if (aux.getTipoS().equals("A POSITIVO")) { tS = 2;}
                    if (aux.getTipoS().equals("A NEGATIVO")) { tS = 3;}
                    if (aux.getTipoS().equals("B POSITIVO")) { tS = 4;}
                    if (aux.getTipoS().equals("B NEGATIVO")) { tS = 5;}
                    if (aux.getTipoS().equals("AB POSITIVO")) { tS = 6;}
                    if (aux.getTipoS().equals("AB NEGATIVO")) { tS = 7;}
                    vDonante.cboxTipoSangre.setSelectedIndex(tS);                    
                }
                System.out.println("Buscar Donante");
                break;
                
            case "CMD_LIST":                
                ArrayList<modelo.ModDonante> don;
                DefaultTableModel modelo = (DefaultTableModel)vDonante.tblDonante.getModel();
                modelo.setRowCount(0);
                don = mbdDonante.buscarTodos();
                
                for (modelo.ModDonante donante:don){                    
                    Object[] fila = new Object[9];

                    fila[0] = "" + donante.getNombre();
                    fila[1] = "" + donante.getApellido();
                    fila[2] = donante.getRut();
                    fila[3] = "" + donante.getTipoS();
                    fila[4] = "" + donante.getSexo();
                    fila[5] = "" + donante.getFechaN();
                    fila[6] = "" + donante.getCiudad();
                    fila[7] = "" + donante.getTelefono();
                    fila[8] = "" + donante.getEmail();
                    
                    modelo.addRow(fila);                
                }
                System.out.println("Desplegar Lista Donante");
                break;
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println(me.paramString());
        int row = vDonante.tblDonante.getSelectedRow(), tS = -1, sex = -1;
        System.out.println("fila:" + row);
        String nom = (String)vDonante.tblDonante.getModel().getValueAt(row, 0);
        String ap = (String)vDonante.tblDonante.getModel().getValueAt(row, 1);
        int rut = (int)vDonante.tblDonante.getModel().getValueAt(row, 2);
        
        String fn = (String)vDonante.tblDonante.getModel().getValueAt(row, 5);
        String c = (String)vDonante.tblDonante.getModel().getValueAt(row, 6);
        String t = (String)vDonante.tblDonante.getModel().getValueAt(row, 7);
        String e = (String)vDonante.tblDonante.getModel().getValueAt(row, 8);        
        String ts = (String)vDonante.tblDonante.getModel().getValueAt(row, 3);
        String s = (String)vDonante.tblDonante.getModel().getValueAt(row, 4);
        
        if (s.equals("VAR")) { sex = 0;}
        if (s.equals("MUJ")) { sex = 1;}
        vDonante.cboxSexo.setSelectedIndex(sex);
        
        if (ts.equals("O POSITIVO")) { tS = 0;}
        if (ts.equals("O NEGATIVO")) { tS = 1;}
        if (ts.equals("A POSITIVO")) { tS = 2;}
        if (ts.equals("A NEGATIVO")) { tS = 3;}
        if (ts.equals("B POSITIVO")) { tS = 4;}
        if (ts.equals("B NEGATIVO")) { tS = 5;}
        if (ts.equals("AB POSITIVO")) { tS = 6;}
        if (ts.equals("AB NEGATIVO")) { tS = 7;}
        vDonante.cboxTipoSangre.setSelectedIndex(tS);
        
        vDonante.txtNombre.setText(nom);
        vDonante.txtApellido.setText(ap);
        vDonante.txtRut.setText("" + rut);
        vDonante.txtFechaN.setText(fn);
        vDonante.txtCiudad.setText(c);
        vDonante.txtTel.setText(t);
        vDonante.txtEmail.setText(e);        
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

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}
