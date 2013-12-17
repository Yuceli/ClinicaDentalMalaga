/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.implementaciones;

import com.persistence.hibernate.HibernateUtil;
import com.clinica.modelo.Proveedor;
import com.dao.interfaces.ProveedorDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NonUniqueResultException;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author
 */
public class ProveedorDAOImpl extends GenericDAOImpl<Proveedor, Integer> implements ProveedorDAO {

    public Proveedor buscarPorNombre(String nombre) {
        Proveedor proveedor = null;
        String sql = "SELECT c FROM Cliente c WHERE c.nombre = :nombre";
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("nombre", nombre);
        proveedor = buscarUno(query);
        return proveedor;
    }

    public Proveedor buscarProveedorPorNombre(String nombre) {
        Proveedor proveedor = null;
        try {
            HibernateUtil.beginTransaction();
            proveedor = buscarPorNombre(nombre);
            HibernateUtil.commitTransaction();
        } catch (NonUniqueResultException ex) {
            System.out.println("Handle your error here");
            System.out.println("Query returned more than one results.");
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return proveedor;
    }

    public List<Proveedor> cargarTodosLosProveedores() {
        List<Proveedor> proveedores = new ArrayList<Proveedor>();
        try {
            HibernateUtil.beginTransaction();
            proveedores = devolverTodos(Proveedor.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return proveedores;
    }

    public void guardarProveedorNuevo(Proveedor proveedor) {
        try {
            HibernateUtil.beginTransaction();
            nuevo(proveedor);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }

    public void actualizarProveedor(Proveedor proveedor) {
        try {
            HibernateUtil.beginTransaction();
            actualizar(proveedor);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }

    public Proveedor buscarProveedorPorID(Integer id) {
        Proveedor proveedor = null;
        try {
            HibernateUtil.beginTransaction();
            proveedor = (Proveedor) buscarPorID(Proveedor.class, id);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return proveedor;
    }

    public void borrarProveedor(Proveedor proveedor) {
        try {
            HibernateUtil.beginTransaction();
            borrar(proveedor);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }
}
