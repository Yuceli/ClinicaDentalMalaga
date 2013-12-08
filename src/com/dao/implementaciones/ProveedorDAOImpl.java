/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao.implementaciones;

import com.persistence.hibernate.HibernateUtil;
import com.clinica.modelo.Proveedor;
import com.dao.interfaces.ProveedorDAO;
import org.hibernate.Query;

/**
 *
 * @author Yuceli
 */
public class ProveedorDAOImpl  extends GenericDAOImpl<Proveedor, Integer> implements ProveedorDAO{
    public Proveedor buscarPorNombre(String nombre) {
        Proveedor proveedor = null;
        String sql = "SELECT c FROM Cliente c WHERE c.nombre = :nombre";
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("nombre", nombre);
        proveedor = buscarUno(query);
        return proveedor;
    }
}
