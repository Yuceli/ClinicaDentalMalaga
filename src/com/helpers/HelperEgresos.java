/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.clinica.modelo.Egreso;
import com.dao.manager.EgresoMgr;
import com.dao.manager.EgresoMgrImpl;
import com.vistas.VistaEgresos;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Yuceli
 */
public class HelperEgresos {

    public HelperEgresos() {
        egresos = new EgresoMgrImpl();
        nombresColumnas = new Vector();
        nombresColumnas.add("ID");
        nombresColumnas.add("Concepto");
        nombresColumnas.add("Tipo de egreso");
        nombresColumnas.add("Proveedor");
        nombresColumnas.add("Monto");
//      nombresColumnas.add("Fecha");

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
//          v.add(c.getFecha());
            vectorTabla.add(v);
        }
        return vectorTabla;

    }

    public void recargarTabla(JTable tablaEgresos) {
        this.cargarTabla(tablaEgresos);
    }

    public void cargarTabla(JTable tabla) {
        ArrayList<Egreso> egresoArrayList = (ArrayList<Egreso>) egresos.cargarEgresos();
        Vector egresosArrayList = VectorToArrayList(egresoArrayList);
        DefaultTableModel dtm = new DefaultTableModel(egresosArrayList, nombresColumnas);
        //Para ordenar las columnas
        TableRowSorter<TableModel> ordenColumnas = new TableRowSorter<TableModel>(dtm);
        tabla.setRowSorter(ordenColumnas);
        tabla.setModel(dtm);
    }

    public void actualizar(JTextField idField, JTextField conceptoField, JTextField tipoEgresoField, JTextField proveedorField, JTextField montoField) {
        int id = Integer.parseInt(idField.getText());
        String concepto = conceptoField.getText();
        String tipoEgreso = tipoEgresoField.getText();
        String proveedor = proveedorField.getText();
        Double monto = Double.parseDouble(montoField.getText());
        Egreso egreso = new Egreso(concepto, tipoEgreso, proveedor, monto);
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

    public void añadirEgreso(JTextField conceptoField, JTextField tipoEgresoField, JTextField proveedorField, JTextField montoField) {
        String concepto = conceptoField.getText();
        String tipoEgreso = tipoEgresoField.getText();
        String proveedor = proveedorField.getText();
        Double monto = Double.parseDouble(montoField.getText());
        Egreso egreso = new Egreso(concepto, tipoEgreso, proveedor, monto);
        this.egresos.guardarEgresoNuevo(egreso);
    }

    public void borrarEgreso(JTextField idField, JTextField conceptoField, JTextField tipoEgresoField, JTextField proveedorField, JTextField montoField) {
        String concepto = conceptoField.getText();
        String tipoEgreso = tipoEgresoField.getText();
        String proveedor = proveedorField.getText();
        Double monto = Double.parseDouble(montoField.getText());
        int id = Integer.parseInt(idField.getText());
        Egreso egreso = new Egreso(concepto, tipoEgreso, proveedor, monto);
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

    private Egreso parseEgreso(JTable tabla, int row) {
        TableModel tableModel = tabla.getModel();
        Egreso egreso = new Egreso();
        int idEgreso = (Integer) tableModel.getValueAt(row, 0);
        egreso.setId(idEgreso);
        egreso.setConcepto((String) tableModel.getValueAt(row, 1));
        egreso.setTipoEgreso((String) tableModel.getValueAt(row, 2));
        egreso.setProveedor((String) tableModel.getValueAt(row, 3));
        egreso.setMonto(((Double) tableModel.getValueAt(row, 4)));

        return egreso;
    }

    private EgresoMgr egresos;
    private VistaEgresos vistaEgresos;
    Vector nombresColumnas;

}
