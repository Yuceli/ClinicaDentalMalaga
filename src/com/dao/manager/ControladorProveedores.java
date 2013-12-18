/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.manager;

import com.clinica.modelo.Proveedor;
import com.dao.implementaciones.ProveedorDAOImpl;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author 
 */
public class ControladorProveedores {

    private ProveedorDAOImpl proveedorDAO;

    public ControladorProveedores() {
        proveedorDAO = new ProveedorDAOImpl();
    }

    public List<Proveedor> cargarTodosLosProveedores() {
        List<Proveedor> proveedores = new ArrayList<Proveedor>();
        proveedores = this.proveedorDAO.cargarTodosLosProveedores();
        return proveedores;
    }

    public void actualizarProveedor(JTextField txtID, JTextField txtNombre, JTextField txtDireccion, JTextField txtTelefono, JTextField txtRFC) {
        String id = txtID.getText();
        int numID = Integer.parseInt(id);
        Proveedor proveedor = proveedorDAO.buscarProveedorPorID(numID);
        //Actualizar registro
        String nombre = txtNombre.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String rfc = txtRFC.getText().trim().toUpperCase();
        proveedor.setNombre(nombre);
        proveedor.setDireccion(direccion);
        proveedor.setTelefono(telefono);
        proveedor.setRfc(rfc);
        this.proveedorDAO.actualizarProveedor(proveedor);

    }

    public void añadirProveedor(JTextField txtNombre, JTextField txtDireccion, JTextField txtTelefono, JTextField txtRFC) {
        String nombre = txtNombre.getText().trim();
        String primeraLetraMayuscula = nombre.substring(0, 1).toUpperCase();
        String continuacionPalabra = nombre.substring(1, nombre.length());
        nombre = primeraLetraMayuscula + continuacionPalabra;
        String direccion = txtDireccion.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String rfc = txtRFC.getText().trim().toUpperCase();
        Proveedor proveedor = new Proveedor(nombre, direccion, telefono, rfc);
        this.proveedorDAO.guardarProveedorNuevo(proveedor);
    }

    public void borrarProveedor(JTable tablaProveedores) {
        int columnaID =0;
        int filaSeleccionada = tablaProveedores.getSelectedRow();
        Object id = (Object) tablaProveedores.getValueAt(filaSeleccionada, columnaID);
        String ID = String.valueOf(id);
        int numID = Integer.parseInt(ID);
        Proveedor proveedor = proveedorDAO.buscarProveedorPorID(numID);
        this.proveedorDAO.borrarProveedor(proveedor);
    }
}