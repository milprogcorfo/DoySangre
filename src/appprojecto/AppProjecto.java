package appprojecto;

public class AppProjecto {
    
    public static void main(String[] args) {
        
        //modelo.ModbdDonante mi = new modelo.ModbdDonante();
        //modelo.ModbdDonacion 
        
        modelo.BD.conectar();
        
        modelo.ModbdDonante mde = new modelo.ModbdDonante();
        modelo.ModbdDonacion mdn = new modelo.ModbdDonacion();
        modelo.ModbdReceptor mrr = new modelo.ModbdReceptor();
        vista.FrmMain v = new vista.FrmMain();
        controlador.CtrMain c = new controlador.CtrMain(v, mde, mdn, mrr);
        v.setVisible(true);           
        
    }
    
}
