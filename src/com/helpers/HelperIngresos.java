
package com.helpers;


import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.clinica.modelo.Ingreso;
import com.dao.manager.IngresoMgrImpl;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
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
        conceptosCol = new Vector();
        conceptosCol.add("ID");
        conceptosCol.add("Concepto");
        conceptosCol.add("TipoIngreso");
        conceptosCol.add("Monto");
        conceptosCol.add("FechaVenta");

    }
    
    private Vector VectorToArrayList(ArrayList<Ingreso> a) {
        Vector vectorTabla = new Vector();

        for (int i = 0; i < a.size(); i++) {
            Ingreso c = a.get(i);
            Vector v = new Vector();
            v.add(c.getId());
            v.add(c.getConcepto());
            v.add(c.getTipoDeIngreso());
            v.add(c.getMonto());
            v.add(c.getFechaDeVenta());
            
            vectorTabla.add(v);
        }
        return vectorTabla;

    }
    public void cargarTabla(JTable tablaIngresos) {
        ArrayList<Ingreso> ingresoMgrs = 
                (ArrayList<Ingreso>) ingresoMgr.cargarTodosLosIngresos();
        Vector v = VectorToArrayList(ingresoMgrs);
        DefaultTableModel dtm = new DefaultTableModel(v, conceptosCol);
        tablaIngresos.setModel(dtm);
    }
    public void llenarCampos(JTable tablaIngresos, JTextField txtID, 
            JTextField txtConcepto, JTextField txtTipoIngreso, JTextField txtMonto, 
            JDateChooser dcFecha){
        int filaSeleccionada = tablaIngresos.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) tablaIngresos.getModel();
        txtID.setText(String.valueOf((Integer)modelo.getValueAt(filaSeleccionada, 
                columnaID)));
        txtConcepto.setText((String) modelo.getValueAt(filaSeleccionada, 
                columnaConcepto));
        txtTipoIngreso.setText((String) modelo.getValueAt(filaSeleccionada, 
                columnaTipoIngreso));
        txtMonto.setText(String.valueOf((Double)modelo.getValueAt(
                filaSeleccionada, columnaMonto)));
        dcFecha.setDate((Date) modelo.getValueAt(filaSeleccionada, 
                columnaFechaVenta));
    }
    public void limpiar(JTextField txtID, JTextField txtConcepto, 
            JTextField txtTipoIngreso, JTextField txtMonto, JDateChooser dcFecha){
        txtID.setText("");
        txtConcepto.setText("");
        txtTipoIngreso.setText("");
        txtMonto.setText("");
        dcFecha.setDate(null);
    }
    
    public void buscarIngreso(JRadioButton rbID, JRadioButton rbNombre, 
            JTextField txtBusqueda, JTable tablaIngresos){
        RowFilter<TableModel, Object> rowFilter = null;
        int columnaBuscar = columnaABuscar(rbID, rbNombre);
        try {
            String textoABuscar = txtBusqueda.getText();
            if (textoABuscar.isEmpty()) {
                rowFilter = RowFilter.regexFilter(textoABuscar, columnaBuscar);
            } else {
                String nombre = txtBusqueda.getText().trim();
                nombre = nombre.substring(0, 1).toUpperCase() + 
                        nombre.substring(1, nombre.length());
                rowFilter = RowFilter.regexFilter(nombre, columnaBuscar);
            }
        } catch (java.util.regex.PatternSyntaxException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar en la tabla", 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        TableRowSorter sorter = 
                new TableRowSorter<TableModel>(tablaIngresos.getModel());
        tablaIngresos.setRowSorter(sorter);
        tablaIngresos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sorter.setRowFilter(rowFilter);
    }
    
    public Ingreso obtenerIngreso(JTable tablaIngresos){
        int filaSeleccionada = tablaIngresos.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) tablaIngresos.getModel();
        String concepto = (String) modelo.getValueAt(filaSeleccionada, 
                columnaConcepto);
        String tipoIngreso = (String) modelo.getValueAt(filaSeleccionada, 
                columnaTipoIngreso);
        Double monto = (Double) modelo.getValueAt(filaSeleccionada, 
                columnaMonto);
        Date fechaDeVenta = (Date) modelo.getValueAt(filaSeleccionada, 
                columnaFechaVenta);
        Ingreso ingreso = new Ingreso(concepto, tipoIngreso, monto, 
                fechaDeVenta);
        int id = (Integer) modelo.getValueAt(filaSeleccionada, columnaID);
        ingreso.setId(id);
        return ingreso;
    }
    public Ingreso obtenerIngreso(JTextField txtConcepto, 
            JTextField txtTipoIngreso, JTextField txtMontoIngreso, 
            JDateChooser dcFecha){
        String concepto = txtConcepto.getText();
        String tipoDeIngreso = txtTipoIngreso.getText();
        Double monto = Double.parseDouble(txtMontoIngreso.getText());
        Date fechaDeIngreso = dcFecha.getDate();
        Ingreso ingreso = new Ingreso(concepto, tipoDeIngreso, monto, fechaDeIngreso);
        return ingreso;
    }    
    public boolean algunCampoVacio(JTextField txtConcepto, 
            JTextField txtTipoIngreso, JTextField txtMonto, JDateChooser dcFecha){
        String concepto = txtConcepto.getText();
        String tipoIngreso = txtTipoIngreso.getText();
        String monto = txtMonto.getText();
        Date fecha = dcFecha.getDate();
        if(concepto.isEmpty()||tipoIngreso.isEmpty()||monto.isEmpty()||fecha==null)
            return true;
        else
            return false;
    }
    
    private IngresoMgrImpl ingresoMgr;
    private Vector conceptosCol;
    private static final int columnaID = 0;
    private static final int columnaConcepto = 1;
    private static final int columnaTipoIngreso = 2;
    private static final int columnaMonto = 3;
    private static final int columnaFechaVenta = 4;
    
    private int columnaABuscar(JRadioButton rbID, JRadioButton rbNombre){
        if (rbID.isSelected()) {
            return 0;
        } else if (rbNombre.isSelected()) {
            return 1;
        } else {
            return 2;
        }
    }
}
