
package com.dao.manager;
/**
 *
 * @author Arian Castillo
 */
import com.clinica.modelo.Ingreso;
import com.dao.implementaciones.IngresoDAOImpl;
import com.toedter.calendar.JDateChooser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ControladorIngresos {

    public ControladorIngresos() {
        ingresoDAO = new IngresoDAOImpl();
    }

    public void añadirIngreso(JTextField txtConcepto, JTextField txtTipoIngreso, JTextField txtMontoIngreso, JDateChooser dcFecha) {
        String concepto = txtConcepto.getText().trim();
        String tipoDeIngreso = txtTipoIngreso.getText().trim();
        Double monto = Double.parseDouble(txtMontoIngreso.getText().trim());
        Date fechaDeVenta = dcFecha.getDate();
        Ingreso ingreso = new Ingreso(concepto, tipoDeIngreso, monto, fechaDeVenta);
        this.ingresoDAO.guardarIngresoNuevo(ingreso);
    }
    public void borrarIngreso(JTable tablaIngresos) {
        int columnaID =0;
        int filaSeleccionada = tablaIngresos.getSelectedRow();
        Object id = (Object) tablaIngresos.getValueAt(filaSeleccionada, columnaID);
        String ID = String.valueOf(id);
        int numID = Integer.parseInt(ID);
        Ingreso ingreso = ingresoDAO.buscarIngresoPorID(numID);
        this.ingresoDAO.borrarIngreso(ingreso);
    }
    public void actualizarIngreso(JTextField txtID, JTextField txtConcepto, JTextField txtTipoIngreso, JTextField txtMontoIngreso, JDateChooser dcFecha) {
        String id = txtID.getText();
        int numID = Integer.parseInt(id);
        Ingreso ingreso = ingresoDAO.buscarIngresoPorID(numID);
        //Actualizar registro
        String concepto = txtConcepto.getText().trim();
        String tipoDeIngreso = txtTipoIngreso.getText().trim();
        Double monto = Double.parseDouble(txtMontoIngreso.getText().trim());
        Date fechaDeVenta = dcFecha.getDate();
        ingreso.setConcepto(concepto);
        ingreso.setTipoDeIngreso(tipoDeIngreso);
        ingreso.setMonto(monto);
        ingreso.setFechaDeVenta(fechaDeVenta);
        this.ingresoDAO.actualizarIngreso(ingreso);
    }
    
    public List<Ingreso> cargarIngresos() {
        List<Ingreso> ingresos = new ArrayList<Ingreso>();
        ingresos = this.ingresoDAO.cargarTodosLosIngresos();
        return ingresos;
    }

    private IngresoDAOImpl ingresoDAO;
}
