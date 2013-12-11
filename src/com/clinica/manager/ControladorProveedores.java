/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clinica.manager;

import com.clinica.modelo.Proveedor;
import com.dao.manejador.proveedores.ProveedorMgrImpl;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;

/**
 *
 * @author Yuceli
 */
public class ControladorProveedores {

    private ProveedorMgrImpl ProveedorMgr;

    public ControladorProveedores() {
        ProveedorMgr = new ProveedorMgrImpl();
    }

    public void actualizarProveedor(JTextField txtID, JTextField txtNombre, JTextField txtDireccion, JTextField txtTelefono, JTextField txtRFC) {
        String id = txtID.getText();
        int numID = Integer.parseInt(id);
        Proveedor proveedor = ProveedorMgr.buscarProveedorPorID(numID);
        //Actualizar registro
        String nombre = txtNombre.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String rfc = txtRFC.getText().trim().toUpperCase();
        proveedor.setNombre(nombre);
        proveedor.setDireccion(direccion);
        proveedor.setTelefono(telefono);
        proveedor.setRfc(rfc);
        this.ProveedorMgr.actualizarProveedor(proveedor);

    }

    public void añadirProveedor(JTextField txtNombre, JTextField txtDireccion, JTextField txtTelefono, JTextField txtRFC) {
        String nombre = txtNombre.getText().trim();
        nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1, nombre.length());
        String direccion = txtDireccion.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String rfc = txtRFC.getText().trim().toUpperCase();
        Proveedor proveedor = new Proveedor(nombre, direccion, telefono, rfc);
        this.ProveedorMgr.guardarProveedorNuevo(proveedor);
    }

    public void borrarProveedor(JTextField txtID) {
        String id = txtID.getText();
        int numID = Integer.parseInt(id);
        Proveedor proveedor = ProveedorMgr.buscarProveedorPorID(numID);
        this.ProveedorMgr.borrarProveedor(proveedor);
    }

    public Boolean verificarRfcProveedor(JTextField txtRFC) {
        String rfc = txtRFC.getText().trim().toUpperCase();
        String automataRfc = "^[A-Z]{3,4}[ \\-]?[0-9]{2}((0{1}[1-9]{1})|(1{1}[0-2]{1}))((0{1}[1-9]{1})|([1-2]{1}[0-9]{1})|(3{1}[0-1]{1}))[ \\-]?[A-Z0-9]{3}";
        Pattern pattern = Pattern.compile(automataRfc);
        Matcher matcher = pattern.matcher(rfc);
        return matcher.matches();
    }

}
