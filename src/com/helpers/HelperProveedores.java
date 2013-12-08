/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.helpers;


import com.clinica.modelo.Proveedor;
import com.controlador.proveedores.ProveedorMgrImpl;
import com.vistas.VistaProveedores;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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
    
    public void cargarTabla(JTable tabla) {
        ArrayList<Proveedor> proveedorMgrs = (ArrayList<Proveedor>) proveedorMgr.cargarTodosLosProveedores();
        Vector v = VectorToArrayList(proveedorMgrs);
        DefaultTableModel dtm = new DefaultTableModel(v, nombresCol);
        tabla.setModel(dtm);
        
    }
        
        public void actualizar(int id, String txt_nombre, String txt_direccion, String txt_telefono, String txt_rfc) {
        Proveedor proveedor = new Proveedor(txt_nombre, txt_direccion, txt_telefono, txt_rfc);
        proveedor.setId(id);
        this.proveedorMgr.actualizarProveedor(proveedor);

    }

    public void borrar(int id, String nombre, String direccion, String telefono,  String rfc) {
        Proveedor proveedor = new Proveedor(nombre, direccion, telefono, rfc);
        proveedor.setId(id);
        this.proveedorMgr.borrarProveedor(proveedor);
    }
    
    public void añadirProveedor(JTextField name, JTextField address, JTextField telephone, JTextField rfc){
        String nombre = name.getText();
        String direccion = address.getText();
        String telefono = telephone.getText();
        String Rfc = rfc.getText();
        Proveedor proveedor = new Proveedor(nombre, direccion, telefono, Rfc);
        this.proveedorMgr.guardarProveedorNuevo(proveedor);
    }
    
    public void borrarCliente(JTextField idField, JTextField nombreField, JTextField direccionField, JTextField celField, JTextField rfc){
        String nombre = nombreField.getText();
        String direccion  = direccionField.getText();
        String telefono = celField.getText();
        String Rfc = rfc.getText();
        int id = Integer.parseInt(idField.getText());
        Proveedor proveedor = new Proveedor(nombre, direccion, telefono, Rfc);
        proveedor.setId(id);
        this.proveedorMgr.borrarProveedor(proveedor);
    }
    
    public void buscarDatosPorId(Integer ID, JTextField id, JTextField nombre, JTextField direccion, JTextField telefono, JTextField Rfc){
        Proveedor proveedor = proveedorMgr.buscarProveedorPorID(ID);
        id.setText(String.valueOf(proveedor.getId()));
        nombre.setText(proveedor.getNombre());
        direccion.setText(proveedor.getDireccion());
        telefono.setText(proveedor.getTelefono());
        Rfc.setText(proveedor.getRfc());
    }
    
    public void buscarDatosPorNombre(String name, JTextField id, JTextField nombre, JTextField direccion, JTextField telefono, JTextField Rfc){
        Proveedor proveedor = proveedorMgr.buscarProveedorPorNombre(name);
        id.setText(String.valueOf(proveedor.getId()));
        nombre.setText(proveedor.getNombre());
        direccion.setText(proveedor.getDireccion());
        telefono.setText(proveedor.getTelefono());
        Rfc.setText(proveedor.getRfc());
        
    }
    
    
    
       
        
}
