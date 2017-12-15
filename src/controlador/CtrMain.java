/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Francisco
 */
public class CtrMain implements ActionListener{
    
    vista.FrmMain vMain;
    modelo.ModbdDonante mbdDonante;
    modelo.ModbdDonacion mbdDonacion;
    modelo.ModbdReceptor mbdReceptor;
    
    public CtrMain(vista.FrmMain vMain, modelo.ModbdDonante mbdDonante, modelo.ModbdDonacion mbdDonacion, modelo.ModbdReceptor mbdReceptor){
        
        this.vMain = vMain;
        this.mbdDonante = mbdDonante;
        this.mbdDonacion = mbdDonacion;
        this.mbdReceptor = mbdReceptor;
        
        vMain.mnuDonante.addActionListener(this);
        vMain.mnuDonacion.addActionListener(this);   
        vMain.mnuPaciente.addActionListener(this);
        vMain.mnuSalir.addActionListener(this);
        vMain.btnRefresh.addActionListener(this);
           
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        String cmd = ae.getActionCommand();        
        System.out.println(cmd);
        switch(cmd){
            case "CMD_F5":
                int centro1[] = new int[9];
                int centro2[] = new int[9];
                int centro3[] = new int[9];
                int aux1, aux2, aux3;
                
                centro1 = mbdDonacion.donaciones(1);
                centro2 = mbdDonacion.donaciones(2);
                centro3 = mbdDonacion.donaciones(3);
                
                vMain.labelOP1.setText("" + centro1[0]);
                vMain.labelON1.setText("" + centro1[1]);
                vMain.labelAP1.setText("" + centro1[2]);
                vMain.labelAN1.setText("" + centro1[3]);
                vMain.labelBP1.setText("" + centro1[4]);
                vMain.labelBN1.setText("" + centro1[5]);
                vMain.labelABP1.setText("" + centro1[6]);
                vMain.labelABN1.setText("" + centro1[7]);
                
                vMain.labelOP2.setText("" + centro2[0]);
                vMain.labelON2.setText("" + centro2[1]);
                vMain.labelAP2.setText("" + centro2[2]);
                vMain.labelAN2.setText("" + centro2[3]);
                vMain.labelBP2.setText("" + centro2[4]);
                vMain.labelBN2.setText("" + centro2[5]);
                vMain.labelABP2.setText("" + centro2[6]);
                vMain.labelABN2.setText("" + centro2[7]);
                
                vMain.labelOP3.setText("" + centro3[0]);
                vMain.labelON3.setText("" + centro3[1]);
                vMain.labelAP3.setText("" + centro3[2]);
                vMain.labelAN3.setText("" + centro3[3]);
                vMain.labelBP3.setText("" + centro3[4]);
                vMain.labelBN3.setText("" + centro3[5]);
                vMain.labelABP3.setText("" + centro3[6]);
                vMain.labelABN3.setText("" + centro3[7]);
                
                aux1 = centro1[0] + centro1[1] + centro1[2] + centro1[3] +
                       centro1[4] + centro1[5] + centro1[6] + centro1[7];
                aux2 = centro2[0] + centro2[1] + centro2[2] + centro2[3] +
                       centro2[4] + centro2[5] + centro2[6] + centro2[7];
                aux3 = centro3[0] + centro3[1] + centro3[2] + centro3[3] +
                       centro3[4] + centro3[5] + centro3[6] + centro3[7];
                
                vMain.labelTotal1.setText("" + (aux1 + centro1[8]));
                vMain.labelAlt1.setText("" + aux1);
                
                vMain.labelTotal2.setText("" + (aux2 + centro2[8]));
                vMain.labelAlt2.setText("" + aux2);
                
                vMain.labelTotal3.setText("" + (aux3 + centro3[8]));
                vMain.labelAlt3.setText("" + aux3);
                
                break;
            case "CMD_DONOR":
                vista.FrmDonante vDonante = new vista.FrmDonante();
                controlador.CtrDonante cDonante = new controlador.CtrDonante(vDonante, mbdDonante, vMain);
                vMain.setEnabled(false);
                vDonante.setVisible(true);        
                break;                
            case "CMD_PATIENT":
                vista.FrmReceptor vReceptor = new vista.FrmReceptor();
                controlador.CtrReceptor cReceptor = new controlador.CtrReceptor(vReceptor, mbdReceptor, vMain);
                vMain.setEnabled(false);
                vReceptor.setVisible(true);
                break;
            case "CMD_DONATE":
                vista.FrmDonacion vDonacion = new vista.FrmDonacion();
                controlador.CtrDonacion cDonacion = new controlador.CtrDonacion(mbdDonacion, vDonacion, vMain, mbdDonante, mbdReceptor);
                vMain.setEnabled(false);
                vDonacion.setVisible(true);
                break;
            case "CMD_EXIT":
                System.exit(0);
                break;
        }
    }
}
