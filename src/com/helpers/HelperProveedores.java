/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.helpers;


import com.clinica.modelo.Proveedor;
import com.dao.manejador.proveedores.ProveedorMgrImpl;
import com.vistas.VistaProveedores;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Yuceli
 */
public class HelperProveedores {
    private ProveedorMgrImpl proveedorMgr;
    private VistaProveedores ventana;
    Vector nombresCol;
    
    public HelperProveedores() {
        proveedorMgr = new ProveedorMgrImpl();
        nombresCol = new Vector();
        nombresCol.add("ID");
        nombresCol.add("Nombre");
        nombresCol.add("Direccion");
        nombresCol.add("Telefono");
        nombresCol.add("RFC");

    }
    
    private Vector VectorToArrayList(ArrayList<Proveedor> a) {
        Vector vectorTabla = new Vector();

        for (int i = 0; i < a.size(); i++) {
            Proveedor c = a.get(i);
            Vector v = new Vector();
            v.add(c.getId());
            v.add(c.getNombre());
            v.add(c.getDireccion());
            v.add(c.getTelefono());
            v.add(c.getRfc());

            vectorTabla.add(v);
        }
        return vectorTabla;
    }
    
    public void cargarTablaProveedores(JTable tabla) {
        ArrayList<Proveedor> proveedorMgrs = (ArrayList<Proveedor>) proveedorMgr.cargarTodosLosProveedores();
        Vector v = VectorToArrayList(proveedorMgrs);
        DefaultTableModel dtm = new DefaultTableModel(v, nombresCol);
        tabla.setModel(dtm);
        
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
        if (nombre.equals("") || direccion.equals("")) {
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

    public void bloquearBotones(JButton btnModificar, JButton btnBorrar) {
        btnModificar.setEnabled(false);
        btnBorrar.setEnabled(false);
    }

    public void desbloquearBotones(JButton btnModificar, JButton btnBorrar) {
        btnModificar.setEnabled(true);
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
    ArrayList arregloProveedor = new ArrayList();

    public ArrayList obtenerDatosFilaSeleccionada(JTable tablaProveedores) {
        int columnaID = 0;
        int columnaNombre = 1;
        int columnaDireccion = 2;
        int columnaTelefono = 3;
        int columnaRFC = 4;
        arregloProveedor.clear();
        int filaSeleccionada = tablaProveedores.getSelectedRow();
        Object id = (Object) tablaProveedores.getValueAt(filaSeleccionada, columnaID);
        String nombre = (String) tablaProveedores.getValueAt(filaSeleccionada, columnaNombre);
        String direccion = (String) tablaProveedores.getValueAt(filaSeleccionada, columnaDireccion);
        String telefono = (String) tablaProveedores.getValueAt(filaSeleccionada, columnaTelefono);
        String rfc = (String) tablaProveedores.getValueAt(filaSeleccionada, columnaRFC);
        arregloProveedor.add(id);
        arregloProveedor.add(nombre);
        arregloProveedor.add(direccion);
        arregloProveedor.add(telefono);
        arregloProveedor.add(rfc);
        return arregloProveedor;
    }

    public void ponerDatosSeleccionados(JTextField txtID, JTextField txtNombre, JTextField txtDireccion, JTextField txtTelefono, JTextField txtRFC) {
        ArrayList arregloDatosProveedor = (ArrayList) arregloProveedor.clone();
        Object id = arregloDatosProveedor.get(0);
        String nombre = (String) arregloDatosProveedor.get(1);
        String direccion = (String) arregloDatosProveedor.get(2);
        String telefono = (String) arregloDatosProveedor.get(3);
        String rfc = (String) arregloDatosProveedor.get(4);
        txtNombre.setText(nombre);
        txtDireccion.setText(direccion);
        txtTelefono.setText(telefono);
        txtID.setText(String.valueOf(id));
        txtRFC.setText(rfc);
    }  

     public void buscarProveedor(ButtonGroup grupo,JRadioButton opcionID, JRadioButton opcionNombre, JTextField txtBusqueda, JTable tablaProveedores) {
        RowFilter<TableModel, Object> rowFilter = null;
        int columnaBuscar = 2;
        
        if(opcionID.isSelected()){
            columnaBuscar=0;
        }else if(opcionNombre.isSelected()){
            columnaBuscar=1;
        }else{
            columnaBuscar=2;
        }
        
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
        TableRowSorter sorter = new TableRowSorter<TableModel>(tablaProveedores.getModel());
        tablaProveedores.setRowSorter(sorter);
        tablaProveedores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sorter.setRowFilter(rowFilter);
        
        

    }
     
       
        
}
