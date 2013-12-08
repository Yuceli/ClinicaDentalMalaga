/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao.interfaces;

import com.dao.interfaces.GenericDAO;
import com.clinica.modelo.Egreso;
import com.clinica.modelo.Proveedor;

/**
 *
 * @author Yuceli
 */
public interface ProveedorDAO extends GenericDAO<Proveedor, Integer> {
    public Proveedor buscarPorNombre(String nombre);
    
}
