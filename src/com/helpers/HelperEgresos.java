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
import com.dao.manager.EgresoMgrImpl;
import com.vistas.VistaEgresos;
import java.util.Date;
import javax.swing.table.TableModel;

/**
 *
 * @author Yucegresoli
 */
public class HelperEgresos {

    private EgresoMgrImpl egresos;
    private VistaEgresos vistaEgresos;
    Vector nombresColumnas;

    public HelperEgresos() {
        egresos = new EgresoMgrImpl();
        nombresColumnas = new Vector();
        nombresColumnas.add("ID");
        nombresColumnas.add("Concepto");
        nombresColumnas.add("Tipo de egreso");
        nombresColumnas.add("Proveedor");
        nombresColumnas.add("Monto");

    }

    private Vector VectorToArrayList(ArrayList<Egreso> a) {
        Vector vectorTabla = new Vector();

        for (int i = 0; i < a.size(); i++) {
            Egreso c = a.get(i);
            Vector v = new Vector();
            v.add(c.getId());
            v.add(c.getConcepto());
            v.add(c.getTipoEgreso());
            v.add(c.getProveedor());
            v.add(c.getMonto());
//            v.add(c.getFecha());
            vectorTabla.add(v);
        }
        return vectorTabla;

    }

    public void recargarTabla(JTable tablaEgresos) {
        this.cargarTabla(tablaEgresos);
    }

    public void cargarTabla(JTable tabla) {
        ArrayList<Egreso> egreso = (ArrayList<Egreso>) egresos.cargarEgresos();
        Vector v = VectorToArrayList(egreso);
        DefaultTableModel dtm = new DefaultTableModel(v, nombresColumnas);
        tabla.setModel(dtm);
    }

    public void actualizar(JTextField idField, JTextField conceptoField, JTextField tipoEgresoField, JTextField proveedorField, JTextField montoField) {
        String concepto = conceptoField.getText();
        String tipoEgreso = tipoEgresoField.getText();
        String proveedor = proveedorField.getText();
        Double monto = Double.parseDouble(montoField.getText());
        int id = Integer.parseInt(idField.getText());
        Egreso egreso = new Egreso(concepto, tipoEgreso, proveedor, monto);
        egreso.setId(id);
        this.egresos.actualizarDatosEgreso(egreso);
    }

    public void editarEgresos(JTable tabla, int row, JTextField concepto, JTextField tipoEgreso, JTextField proveedor, JTextField monto) {
        Egreso egreso = parseEgreso(tabla, row);
//      id.setText(String.valueOf(egreso.getId()));
        concepto.setText(egreso.getConcepto());
        tipoEgreso.setText(egreso.getTipoEgreso());
        proveedor.setText(egreso.getProveedor());
        monto.setText(Double.toString(egreso.getMonto()));

    }

    public void borrar(int id, String nombre, String direccion, String correo, Double monto) {
        Egreso egreso = new Egreso(nombre, direccion, correo, monto);
        egreso.setId(id);
        this.egresos.borrarEgreso(egreso);
    }

    public void añadirEgreso(JTextField name, JTextField address, JTextField email, JTextField montoField) {
        String nombre = name.getText();
        String direccion = address.getText();
        String correo = email.getText();
        Double monto = Double.parseDouble(montoField.getText());
        Egreso cliente = new Egreso(nombre, direccion, correo, monto);
        this.egresos.guardarEgresoNuevo(cliente);
    }

    public void borrarCliente(JTextField idField, JTextField nombreField, JTextField direccionField, JTextField correoField, JTextField montoField) {
        String nombre = nombreField.getText();
        String direccion = direccionField.getText();
        String correo = correoField.getText();
        int id = Integer.parseInt(idField.getText());
        Double monto = Double.parseDouble(montoField.getText());
        Egreso cliente = new Egreso(nombre, direccion, correo, monto);
        cliente.setId(id);
        this.egresos.borrarEgreso(cliente);
    }

    public void buscarDatosPorId(Integer ID, JTextField id, JTextField nombre, JTextField direccion, JTextField correo) {
        Egreso cliente = egresos.buscarEgresoPorID(ID);
        id.setText(String.valueOf(cliente.getId()));
        nombre.setText(cliente.getConcepto());
        direccion.setText(cliente.getTipoEgreso());
        correo.setText(cliente.getProveedor());

    }

    public void buscarDatosPorNombre(String name, JTextField id, JTextField nombre, JTextField direccion, JTextField correo) {
        Egreso cliente = egresos.buscarEgresoPorConcepto(name);
        id.setText(String.valueOf(cliente.getId()));
        nombre.setText(cliente.getConcepto());
        direccion.setText(cliente.getTipoEgreso());
        correo.setText(cliente.getProveedor());
    }

    private Egreso parseEgreso(JTable tabla, int row) {
        TableModel tableModel = tabla.getModel();
        Egreso egreso = new Egreso();
        int idEgreso = (Integer) tableModel.getValueAt(row, 0);
//      egreso.setId(idEgreso);
        egreso.setConcepto((String) tableModel.getValueAt(row, 1));
        egreso.setTipoEgreso((String) tableModel.getValueAt(row, 2));
        egreso.setProveedor((String) tableModel.getValueAt(row, 3));
        egreso.setMonto(((Double) tableModel.getValueAt(row, 4)));

        return egreso;
    }

}
