package com.helpers;
/**
 *
 * @author Arian Castillo
 */
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.clinica.modelo.Ingreso;
import com.dao.manager.ControladorIngresos;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class HelperIngresos {

    public HelperIngresos() {
    }
    
    public boolean camposVacios(JTextField txtConcepto, JTextField txtTipoIngreso, JTextField txtMonto, JDateChooser dcFecha){
        String concepto = txtConcepto.getText();
        String tipoIngreso = txtTipoIngreso.getText();
        String monto = txtMonto.getText();
        Date fecha = dcFecha.getDate();
        if(concepto.isEmpty()||tipoIngreso.isEmpty()||monto.isEmpty()||fecha==null)
            return true;
        else
            return false;
    }
    
    public void bloquearBotones(JButton btnActualizar, JButton btnBorrar, JButton btnEditar){
        btnActualizar.setEnabled(false);
        btnBorrar.setEnabled(false);
        btnEditar.setEnabled(false);
    }
    public void desbloquearBotones(JButton btnActualizar, JButton btnBorrar) {
        btnActualizar.setEnabled(true);
        btnBorrar.setEnabled(true);
    }
    
    public void bloquearCamposTexto(JTextField txtConcepto, JTextField txtTipoIngreso, JTextField txtMonto, JDateChooser dcFecha) {
        txtConcepto.setEnabled(false);
        txtTipoIngreso.setEnabled(false);
        txtMonto.setEnabled(false);
        dcFecha.setEnabled(false);
    }
    public void desbloquearCamposTextoE(JTextField txtConcepto, JTextField txtTipoIngreso, JTextField txtMonto, JDateChooser dcFecha) {
        txtConcepto.setEnabled(true);
        txtTipoIngreso.setEnabled(true);
        txtMonto.setEnabled(true);
        dcFecha.setEnabled(true);
    }
    
    public void limpiarCamposTexto(JTextField txtBusqueda, JTextField txtID, JTextField txtConcepto, JTextField txtTipoIngreso, JTextField txtMonto, JDateChooser dcFecha) {
        txtBusqueda.setText("");
        txtID.setText("");
        txtConcepto.setText("");
        txtTipoIngreso.setText("");
        txtMonto.setText("");
        dcFecha.setDate(null);
    }
    
    public ArrayList obtenerDatosFilaSeleccionada(JTable tablaIngresos) {
        arregloIngreso.clear();
        int filaSeleccionada = tablaIngresos.getSelectedRow();
        Object id = (Object) tablaIngresos.getValueAt(filaSeleccionada, columnaID);
        String concepto = (String) tablaIngresos.getValueAt(filaSeleccionada, columnaConcepto);
        String tipoIngreso = (String) tablaIngresos.getValueAt(filaSeleccionada, columnaTipoIngreso);
        Double monto = Double.parseDouble((String) tablaIngresos.getValueAt(filaSeleccionada, columnaMonto));
        Date fechaDeVenta = (Date) tablaIngresos.getValueAt(filaSeleccionada, columnaFechaVenta);
        this.colocarDatosEnArreglo(id, concepto, tipoIngreso, monto, fechaDeVenta);
        return arregloIngreso;
    }
    private void colocarDatosEnArreglo(Object id, String concepto, String tipoIngreso, Double monto, Date fechaDeVenta) {
        arregloIngreso.add(id);
        arregloIngreso.add(concepto);
        arregloIngreso.add(tipoIngreso);
        arregloIngreso.add(monto);
        arregloIngreso.add(fechaDeVenta);
    }
    public void ponerDatosSeleccionados(JTextField txtID, JTextField txtConcepto, JTextField txtTipoIngreso, JTextField txtMonto, JDateChooser dcFecha) {
        ArrayList arregloDatosIngreso = (ArrayList) arregloIngreso.clone();
        Object id = arregloDatosIngreso.get(columnaID);
        String concepto = (String) arregloDatosIngreso.get(columnaConcepto);
        String tipoIngreso = (String) arregloDatosIngreso.get(columnaTipoIngreso);
        Double monto = Double.parseDouble((String) arregloDatosIngreso.get(columnaMonto));
        Date fechaDeVenta = (Date) arregloDatosIngreso.get(columnaFechaVenta);
        txtID.setText(String.valueOf(id));
        txtConcepto.setText(concepto);
        txtTipoIngreso.setText(tipoIngreso);
        txtMonto.setText(String.valueOf(monto));
        dcFecha.setDate(fechaDeVenta);
    }
    
    public void buscarIngreso(JRadioButton opcionID, JRadioButton opcionNombre, JTextField txtBusqueda, JTable tablaIngresos) {
        RowFilter<TableModel, Object> filtradorFilas = null;
        int columnaBuscar = buscarColumnaAUsar(opcionID, opcionNombre);

        try {
            String textoABuscar = txtBusqueda.getText();
            if (textoABuscar.isEmpty()) {
                filtradorFilas = RowFilter.regexFilter(textoABuscar, columnaBuscar);
            } else {
                String textoAEncontrar = txtBusqueda.getText().trim();
                textoAEncontrar = textoAEncontrar.substring(0, 1).toUpperCase() + textoAEncontrar.substring(1, textoAEncontrar.length());
                filtradorFilas = RowFilter.regexFilter(textoAEncontrar, columnaBuscar);
            }
        } catch (java.util.regex.PatternSyntaxException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar en la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
        TableRowSorter sorter = new TableRowSorter<TableModel>(tablaIngresos.getModel());
        tablaIngresos.setRowSorter(sorter);
        tablaIngresos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sorter.setRowFilter(filtradorFilas);

    }
    
    public void cargarTablaIngresos(JTable tablaIngresos) {
        try {
            final DefaultTableModel modelosTabla = (DefaultTableModel) tablaIngresos.getModel();
            modelosTabla.setRowCount(0);
            List ingresos = control.cargarIngresos();
            Object datosIngreso[] = new Object[5];//ARRAY DE 4

            Iterator<Ingreso> iterador = ingresos.iterator();
            while (iterador.hasNext()) {
                Ingreso ingreso = (Ingreso) iterador.next();
                datosIngreso[0] = ingreso.getId();
                datosIngreso[1] = ingreso.getConcepto();
                datosIngreso[2] = ingreso.getTipoDeIngreso();
                datosIngreso[3] = ingreso.getMonto();
                datosIngreso[4] = ingreso.getFechaDeVenta();
                modelosTabla.addRow(datosIngreso);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private ControladorIngresos control = new ControladorIngresos();
    private ArrayList arregloIngreso = new ArrayList();
    private static final int columnaID = 0;
    private static final int columnaConcepto = 1;
    private static final int columnaTipoIngreso = 2;
    private static final int columnaMonto = 3;
    private static final int columnaFechaVenta = 4;
    
    private int buscarColumnaAUsar(JRadioButton opcionID, JRadioButton opcionNombre){
        if (opcionID.isSelected()) {
            return 0;
        } else if (opcionNombre.isSelected()) {
            return 1;
        } else {
            return 2;
        }
    }
}
