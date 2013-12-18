/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao.interfaces;

import com.dao.interfaces.GenericDAO;
import com.clinica.modelo.Egreso;
import com.clinica.modelo.Proveedor;
import java.util.List;

/**
 *
 * @author 
 */
public interface ProveedorDAO extends GenericDAO<Proveedor, Integer> {
 
    public List<Proveedor> cargarTodosLosProveedores();
 
    public void guardarProveedorNuevo(Proveedor proveedor);
    
    public void actualizarProveedor(Proveedor proveedor);
 
    public Proveedor buscarProveedorPorID(Integer id);
 
    public void borrarProveedor(Proveedor proveedor);  
    
}
