/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import com.clinica.modelo.Proveedor;
import com.dao.manager.ControladorProveedores;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author
 */
public class HelperProveedores {

    static int datoID = 0;
    static int datoNombre = 1;
    static int datoDireccion = 2;
    static int datoTelefono = 3;
    static int datoRFC = 4;
    static int busquedaID = 0;
    static int busquedaNombre = 1;

    public HelperProveedores() {
    }

    public boolean camposSobrepasados(JTextField txtNombre, JTextField txtDireccion, JTextField txtTelefono) {
        String nombre = txtNombre.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String telefono = txtTelefono.getText().trim();
        if (nombre.length() > 100 || direccion.length() > 100 || telefono.length() > 100) {
            return true;
        } else {
            return false;
        }
    }

    public boolean camposVacios(JTextField txtNombre, JTextField txtDireccion) {
        String nombre = txtNombre.getText().trim();
        String direccion = txtDireccion.getText().trim();
        if (nombre.isEmpty() || direccion.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean telefonoEsNumerico(JTextField txtTelefono) {
        String telefono = txtTelefono.getText().trim();
        try {
            Integer.parseInt(telefono);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void bloquearBotones(JButton btnGuardar, JButton btnBorrar, JButton btnModificar) {
        btnGuardar.setEnabled(false);
        btnBorrar.setEnabled(false);
        btnModificar.setEnabled(false);
    }

    public void desbloquearBotones(JButton btnGuardar, JButton btnBorrar) {
        btnGuardar.setEnabled(true);
        btnBorrar.setEnabled(true);
    }

    public void bloquearCamposTexto(JTextField txtNombre, JTextField txtDireccion, JTextField txtTelefono, JTextField txtRFC) {
        txtNombre.setEnabled(false);
        txtDireccion.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtRFC.setEnabled(false);
    }

    public void desbloquearCamposTexto(JTextField txtNombre, JTextField txtDireccion, JTextField txtTelefono, JTextField txtRFC) {
        txtNombre.setEnabled(true);
        txtDireccion.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtRFC.setEnabled(true);
    }

    public void limpiarCamposTexto(JTextField txtID, JTextField txtNombre, JTextField txtDireccion, JTextField txtTelefono, JTextField txtRFC) {
        txtID.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtRFC.setText("");
    }

    public Boolean verificarRfcProveedor(JTextField txtRFC) {
        String rfc = txtRFC.getText().trim().toUpperCase();
        String expresionRegularRFC = "^[A-Z]{3,4}[ \\-]?[0-9]{2}((0{1}[1-9]{1})|(1{1}[0-2]{1}))((0{1}[1-9]{1})|([1-2]{1}[0-9]{1})|(3{1}[0-1]{1}))[ \\-]?[A-Z0-9]{3}";
        Pattern pattern = Pattern.compile(expresionRegularRFC);
        Matcher matcher = pattern.matcher(rfc);
        return matcher.matches();
    }
    ArrayList arregloProveedor = new ArrayList();

    public ArrayList obtenerDatosFilaSeleccionada(JTable tablaProveedores) {
        arregloProveedor.clear();
        int filaSeleccionada = tablaProveedores.getSelectedRow();
        Object id = (Object) tablaProveedores.getValueAt(filaSeleccionada, datoID);
        String nombre = (String) tablaProveedores.getValueAt(filaSeleccionada, datoNombre);
        String direccion = (String) tablaProveedores.getValueAt(filaSeleccionada, datoDireccion);
        String telefono = (String) tablaProveedores.getValueAt(filaSeleccionada, datoTelefono);
        String rfc = (String) tablaProveedores.getValueAt(filaSeleccionada, datoRFC);
        colocarDatosEnArreglo(id, nombre, direccion, telefono, rfc);
        return arregloProveedor;
    }

    public void ponerDatosSeleccionados(JTextField txtID, JTextField txtNombre, JTextField txtDireccion, JTextField txtTelefono, JTextField txtRFC) {
        ArrayList arregloDatosProveedor = (ArrayList) arregloProveedor.clone();
        Object id = arregloDatosProveedor.get(datoID);
        String nombre = (String) arregloDatosProveedor.get(datoNombre);
        String direccion = (String) arregloDatosProveedor.get(datoDireccion);
        String telefono = (String) arregloDatosProveedor.get(datoTelefono);
        String rfc = (String) arregloDatosProveedor.get(datoRFC);
        txtNombre.setText(nombre);
        txtDireccion.setText(direccion);
        txtTelefono.setText(telefono);
        txtID.setText(String.valueOf(id));
        txtRFC.setText(rfc);
    }

    public void buscarProveedor(JRadioButton opcionID, JRadioButton opcionNombre, JTextField txtBusqueda, JTable tablaProveedores) {
        RowFilter<TableModel, Object> filtradorFilas = null;
        int columnaBuscar = 2;

        if (opcionID.isSelected()) {
            columnaBuscar = busquedaID;
        } else if (opcionNombre.isSelected()) {
            columnaBuscar = busquedaNombre;
        } else {
            columnaBuscar = 2;
        }

        try {
            String textoABuscar = txtBusqueda.getText();
            if (textoABuscar.isEmpty()) {
                filtradorFilas = RowFilter.regexFilter(textoABuscar, columnaBuscar);
            } else {
                String textoAEncontrar = txtBusqueda.getText().trim();
                int longitudTextoAEncontrar = textoAEncontrar.length();
                String primeraLetraMayuscula = textoAEncontrar.substring(0, 1).toUpperCase();
                String continuacionPalabra = textoAEncontrar.substring(1, longitudTextoAEncontrar);
                textoAEncontrar = primeraLetraMayuscula + continuacionPalabra;
                filtradorFilas = RowFilter.regexFilter(textoAEncontrar, columnaBuscar);
            }
        } catch (java.util.regex.PatternSyntaxException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar en la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
        TableRowSorter sorter = new TableRowSorter<TableModel>(tablaProveedores.getModel());
        tablaProveedores.setRowSorter(sorter);
        tablaProveedores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sorter.setRowFilter(filtradorFilas);

    }


    public void cargarTablaProveedores(JTable tablaProveedores) {
        try {
            final DefaultTableModel modelosTabla = (DefaultTableModel) tablaProveedores.getModel();
            modelosTabla.setRowCount(0);
            List proveedores = control.cargarTodosLosProveedores();
            Object datosProveedor[] = new Object[5];

            Iterator<Proveedor> iterador = proveedores.iterator();
            while (iterador.hasNext()) {
                Proveedor proveedor = (Proveedor) iterador.next();
                datosProveedor[0] = proveedor.getId();
                datosProveedor[1] = proveedor.getNombre();
                datosProveedor[2] = proveedor.getDireccion();
                datosProveedor[3] = proveedor.getTelefono();
                datosProveedor[4] = proveedor.getRfc();
                modelosTabla.addRow(datosProveedor);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
        private void colocarDatosEnArreglo(Object id, String nombre, String direccion, String telefono, String rfc) {
        arregloProveedor.add(id);
        arregloProveedor.add(nombre);
        arregloProveedor.add(direccion);
        arregloProveedor.add(telefono);
        arregloProveedor.add(rfc);
    }
        
          private ControladorProveedores control = new ControladorProveedores();
}
