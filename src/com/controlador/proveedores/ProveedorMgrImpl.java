/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controlador.proveedores;

import com.persistence.hibernate.HibernateUtil;
import com.dao.implementaciones.ProveedorDAOImpl;
import com.clinica.modelo.Proveedor;
import com.dao.interfaces.ProveedorDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NonUniqueResultException;
import org.hibernate.HibernateException;

/**
 *
 * @author Yuceli
 */
public class ProveedorMgrImpl implements ProveedorMgr  {
    
    private ProveedorDAO proveedorDAO = new ProveedorDAOImpl();
 
    public Proveedor buscarProveedorPorNombre(String nombre) {
        Proveedor proveedor = null;
        try {
            HibernateUtil.beginTransaction();
            proveedor = proveedorDAO.buscarPorNombre(nombre);
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
        List<Proveedor> clientes = new ArrayList<Proveedor>();
        try {
            HibernateUtil.beginTransaction();
            clientes = proveedorDAO.devolverTodos(Proveedor.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return clientes;
    }
 
    public void guardarProveedorNuevo(Proveedor proveedor) {
        try {
            HibernateUtil.beginTransaction();
            proveedorDAO.nuevo(proveedor);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }
 
    public void actualizarProveedor(Proveedor proveedor){
        try {
            HibernateUtil.beginTransaction();
            proveedorDAO.actualizar(proveedor);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }        
    }
    
    public Proveedor buscarProveedorPorID(Integer id) {
        Proveedor cliente = null;
        try {
            HibernateUtil.beginTransaction();
            cliente = (Proveedor) proveedorDAO.buscarPorID(Proveedor.class, id);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return cliente;
    }
 
    public void borrarProveedor(Proveedor proveedor) {
        try {
            HibernateUtil.beginTransaction();
            proveedorDAO.borrar(proveedor);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }

    
}
