/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controlador.proveedores;

import com.clinica.modelo.Proveedor;
import java.util.List;

/**
 *
 * @author Yuceli
 */
public interface ProveedorMgr {
    public Proveedor buscarProveedorPorNombre(String nombre);
 
    public List<Proveedor> cargarTodosLosProveedores();
 
    public void guardarProveedorNuevo(Proveedor proveedor);
    
    public void actualizarProveedor(Proveedor proveedor);
 
    public Proveedor buscarProveedorPorID(Integer id);
 
    public void borrarProveedor(Proveedor proveedor);  
    
}
