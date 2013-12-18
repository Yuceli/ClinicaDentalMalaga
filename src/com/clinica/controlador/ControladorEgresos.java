/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.controlador;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.clinica.modelo.Egreso;
import com.dao.implementaciones.EgresoDAOImpl;
import com.toedter.calendar.JDateChooser;
import com.vistas.VistaEgresos;
import java.util.Date;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Yuceli
 */
public class ControladorEgresos {

    public ControladorEgresos() {
        egresos = new EgresoDAOImpl();
        nombresColumnas = new Vector();
        nombresColumnas.add("ID");
        nombresColumnas.add("Concepto");
        nombresColumnas.add("Tipo de egreso");
        nombresColumnas.add("Proveedor");
        nombresColumnas.add("Monto");
        nombresColumnas.add("Fecha");

    }

    public void recargarTabla(JTable tablaEgresos) {
        this.cargarTablaEgresos(tablaEgresos);
    }

    public void cargarTablaEgresos(JTable tabla) {
        ArrayList<Egreso> egresoArrayList = (ArrayList<Egreso>) egresos.cargarEgresos();
        Vector egresosArrayList = VectorToArrayList(egresoArrayList);
        DefaultTableModel modeloTabla = new DefaultTableModel(egresosArrayList, nombresColumnas);
        TableRowSorter<TableModel> ordenColumnas = new TableRowSorter<TableModel>(modeloTabla);
        tabla.setRowSorter(ordenColumnas);
        tabla.setModel(modeloTabla);
    }

    public void actualizar(JTextField idField, JTextField conceptoField, JTextField tipoEgresoField, JTextField proveedorField, JTextField montoField, JDateChooser fechaField) {
        int id = Integer.parseInt(idField.getText());
        String concepto = conceptoField.getText();
        String tipoEgreso = tipoEgresoField.getText();
        String proveedor = proveedorField.getText();
        Double monto = Double.parseDouble(montoField.getText());
        Date fechaDeEgreso = fechaField.getDate();
        Egreso egreso = new Egreso(concepto, tipoEgreso, proveedor, monto, fechaDeEgreso);
        egreso.setId(id);
        this.egresos.actualizarDatosEgreso(egreso);
    }

    public void editarEgresos(JTable tabla, int row, JTextField id, JTextField concepto, JTextField tipoEgreso, JTextField proveedor, JTextField monto) {
        Egreso egreso = parseEgreso(tabla, row);
        id.setText(String.valueOf(egreso.getId()));
        concepto.setText(egreso.getConcepto());
        tipoEgreso.setText(egreso.getTipoEgreso());
        proveedor.setText(egreso.getProveedor());
        monto.setText(Double.toString(egreso.getMonto()));

    }

    public void añadirEgreso(JTextField conceptoField, JTextField tipoEgresoField, JTextField proveedorField, JTextField montoField, JDateChooser fechaField) {
        String concepto = conceptoField.getText();
        String tipoEgreso = tipoEgresoField.getText();
        String proveedor = proveedorField.getText();
        Double monto = Double.parseDouble(montoField.getText());
        Date fechaDeEgreso = fechaField.getDate();
        Egreso egreso = new Egreso(concepto, tipoEgreso, proveedor, monto, fechaDeEgreso);
        this.egresos.guardarEgresoNuevo(egreso);
    }

    public void borrarEgreso(JTextField idField, JTextField conceptoField, JTextField tipoEgresoField, JTextField proveedorField, JTextField montoField, JDateChooser fechaField) {
        String concepto = conceptoField.getText();
        String tipoEgreso = tipoEgresoField.getText();
        String proveedor = proveedorField.getText();
        Double monto = Double.parseDouble(montoField.getText());
        Date fechaDeEgreso = fechaField.getDate();
        int id = Integer.parseInt(idField.getText());
        Egreso egreso = new Egreso(concepto, tipoEgreso, proveedor, monto, fechaDeEgreso);
        egreso.setId(id);
        this.egresos.borrarEgreso(egreso);
    }

    public void buscarDatosPorId(Integer ID, JTextField id, JTextField concepto, JTextField tipoEgreso, JTextField proveedor, JTextField monto) {
        Egreso egreso = egresos.buscarEgresoPorID(ID);
        id.setText(String.valueOf(egreso.getId()));
        concepto.setText(egreso.getConcepto());
        tipoEgreso.setText(egreso.getTipoEgreso());
        proveedor.setText(egreso.getProveedor());
        monto.setText(Double.toString(egreso.getMonto()));

    }

    public void buscarDatosPorNombre(String concepto, JTextField id, JTextField concept, JTextField tipoEgreso, JTextField proveedor, JTextField monto) {
        Egreso egreso = egresos.buscarEgresoPorConcepto(concepto);
        id.setText(String.valueOf(egreso.getId()));
        concept.setText(egreso.getConcepto());
        tipoEgreso.setText(egreso.getTipoEgreso());
        proveedor.setText(egreso.getProveedor());
        monto.setText(Double.toString(egreso.getMonto()));
    }
    
    
     private Vector VectorToArrayList(ArrayList<Egreso> array) {
        Vector vectorTabla = new Vector();

        for (int i = 0; i < array.size(); i++) {
            Egreso egresos = array.get(i);
            Vector v = new Vector();
            v.add(egresos.getId());
            v.add(egresos.getConcepto());
            v.add(egresos.getTipoEgreso());
            v.add(egresos.getProveedor());
            v.add(egresos.getMonto());
            v.add(egresos.getFechaDeCompra());
            vectorTabla.add(v);
        }
        return vectorTabla;

    }

    private Egreso parseEgreso(JTable tabla, int row) {
        TableModel tableModel = tabla.getModel();
        Egreso egreso = new Egreso();
        int idEgreso = (Integer) tableModel.getValueAt(row, 0);
        egreso.setId(idEgreso);
        egreso.setConcepto((String) tableModel.getValueAt(row, 1));
        egreso.setTipoEgreso((String) tableModel.getValueAt(row, 2));
        egreso.setProveedor((String) tableModel.getValueAt(row, 3));
        egreso.setMonto(((Double) tableModel.getValueAt(row, 4)));
        egreso.setFechaDeCompra((Date) tableModel.getValueAt(row, 5));

        return egreso;
    }

    private EgresoDAOImpl egresos;
    private VistaEgresos vistaEgresos;
    Vector nombresColumnas;

}
