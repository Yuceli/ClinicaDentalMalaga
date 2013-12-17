package com.helpers;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.clinica.modelo.Ingreso;
import com.dao.manager.IngresoMgrImpl;
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
        ingresoMgr = new IngresoMgrImpl();
        vConceptosCol = new Vector();
        vConceptosCol.add("ID");
        vConceptosCol.add("Concepto");
        vConceptosCol.add("Tipo de Ingreso");
        vConceptosCol.add("Monto");
        vConceptosCol.add("Fecha de Venta");

    }
    
    private Vector VectorToArrayList(ArrayList<Ingreso> arrayL) {
        Vector vectorTabla = new Vector();

        for (int i = 0; i < arrayL.size(); i++) {
            Ingreso c = arrayL.get(i);
            Vector vectorAtributos = new Vector();
            vectorAtributos.add(c.getId());
            vectorAtributos.add(c.getConcepto());
            vectorAtributos.add(c.getTipoDeIngreso());
            vectorAtributos.add(c.getMonto());
            vectorAtributos.add(c.getFechaDeVenta());
            
            vectorTabla.add(vectorAtributos);
        }
        return vectorTabla;

    }
    
    public void recargarTablaIngresos(JTable tablaIngresos) {
        try {
            final DefaultTableModel modelosTabla = (DefaultTableModel) tablaIngresos.getModel();
            modelosTabla.setRowCount(0);
            List proveedores = ingresoMgr.cargarTodosLosIngresos();
            Object datosIngresos[] = new Object[5];//ARRAY DE 4

            Iterator<Ingreso> iterador = proveedores.iterator();
            while (iterador.hasNext()) {
                Ingreso ingr = (Ingreso) iterador.next();
                datosIngresos[0] = ingr.getId();
                datosIngresos[1] = ingr.getConcepto();
                datosIngresos[2] = ingr.getTipoDeIngreso();
                datosIngresos[3] = ingr.getMonto();
                datosIngresos[4] = ingr.getFechaDeVenta();
                modelosTabla.addRow(datosIngresos);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void ponerDatosSeleccionados(JTable tablaIngresos, int filaSeleccionada, JTextField txtID, JTextField txtConcepto, JTextField txtTipoIngreso, JTextField txtMonto, JDateChooser dcFecha){
        DefaultTableModel modelo = (DefaultTableModel) tablaIngresos.getModel();
        txtID.setText(String.valueOf((Integer)modelo.getValueAt(filaSeleccionada, columnaID)));
        txtConcepto.setText((String) modelo.getValueAt(filaSeleccionada, columnaConcepto));
        txtTipoIngreso.setText((String) modelo.getValueAt(filaSeleccionada, columnaTipoIngreso));
        txtMonto.setText(String.valueOf((Double)modelo.getValueAt(filaSeleccionada, columnaMonto)));
        dcFecha.setDate((Date) modelo.getValueAt(filaSeleccionada, columnaFechaVenta));
    }
    public void limpiarCampos(JTextField txtBusqueda, JTextField txtID, JTextField txtConcepto, JTextField txtTipoIngreso, JTextField txtMonto, JDateChooser dcFecha){
        txtBusqueda.setText("");
        txtID.setText("");
        txtConcepto.setText("");
        txtTipoIngreso.setText("");
        txtMonto.setText("");
        dcFecha.setDate(null);
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
    
    public void bloquearBotones(JButton btnBorrar, JButton btnEditar){
        btnBorrar.setEnabled(false);
        btnEditar.setEnabled(false);
    }
        
    public Ingreso obtenerIngreso(JTextField txtConcepto, JTextField txtTipoIngreso, JTextField txtMontoIngreso, JDateChooser dcFecha){
        String concepto = txtConcepto.getText().trim();
        String tipoDeIngreso = txtTipoIngreso.getText().trim();
        Double monto = Double.parseDouble(txtMontoIngreso.getText().trim());
        Date fechaDeIngreso = dcFecha.getDate();
        Ingreso ingreso = new Ingreso(concepto, tipoDeIngreso, monto, fechaDeIngreso);
        return ingreso;
    }
    public Ingreso obtenerIngreso(JTable tablaIngresos, int filaSeleccionada){
        DefaultTableModel modelo = (DefaultTableModel) tablaIngresos.getModel();
        String concepto = (String) modelo.getValueAt(filaSeleccionada, columnaConcepto);
        String tipoIngreso = (String) modelo.getValueAt(filaSeleccionada, columnaTipoIngreso);
        Double monto = (Double) modelo.getValueAt(filaSeleccionada, columnaMonto);
        Date fechaDeVenta = (Date) modelo.getValueAt(filaSeleccionada, columnaFechaVenta);
        Ingreso ingreso = new Ingreso(concepto, tipoIngreso, monto, fechaDeVenta);
        int id = (Integer) modelo.getValueAt(filaSeleccionada, columnaID);
        ingreso.setId(id);
        return ingreso;
    }
    
    public void buscarIngreso(JRadioButton rbID, JRadioButton rbNombre, JTextField txtBusqueda, JTable tablaIngresos){
        RowFilter<TableModel, Object> rowFilter = null;
        int columnaBuscar = this.columnaABuscar(rbID, rbNombre);
        try {
            String textoABuscar = txtBusqueda.getText();
            if (textoABuscar.isEmpty()) {
                rowFilter = RowFilter.regexFilter(textoABuscar, columnaBuscar);
            } else {
                String nombre = txtBusqueda.getText().trim();
                nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1, nombre.length());
                rowFilter = RowFilter.regexFilter(nombre, columnaBuscar);
            }
        } catch (java.util.regex.PatternSyntaxException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar en la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
        TableRowSorter sorter = new TableRowSorter<TableModel>(tablaIngresos.getModel());
        tablaIngresos.setRowSorter(sorter);
        tablaIngresos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sorter.setRowFilter(rowFilter);
    }

    private IngresoMgrImpl ingresoMgr;
    private Vector vConceptosCol;
    private static final int columnaID = 0;
    private static final int columnaConcepto = 1;
    private static final int columnaTipoIngreso = 2;
    private static final int columnaMonto = 3;
    private static final int columnaFechaVenta = 4;

    private int columnaABuscar(JRadioButton rbID, JRadioButton rbNombre) {
        if (rbID.isSelected()) {
            return 0;
        } else if (rbNombre.isSelected()) {
            return 1;
        } else {
            return 2;
        }
    }
}
