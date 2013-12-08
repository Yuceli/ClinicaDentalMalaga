

package com.controlador.egresos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NonUniqueResultException;
import com.dao.interfaces.EgresoDAO;
import com.dao.implementaciones.EgresoDAOImpl;
import com.clinica.modelo.Egreso;
import com.persistence.hibernate.HibernateUtil;
import org.hibernate.HibernateException;

public class EgresoMgrImpl  implements EgresoMgr{
 
    private EgresoDAO clienteDAO = new EgresoDAOImpl();
 
    public Egreso buscarClientePorNombre(String nombre) {
        Egreso cliente = null;
        try {
            HibernateUtil.beginTransaction();
            cliente = clienteDAO.buscarPorNombre(nombre);
            HibernateUtil.commitTransaction();
        } catch (NonUniqueResultException ex) {
            System.out.println("Handle your error here");
            System.out.println("Query returned more than one results.");
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return cliente;
    }
 
    public List<Egreso> cargarTodosLosClientes() {
        List<Egreso> clientes = new ArrayList<Egreso>();
        try {
            HibernateUtil.beginTransaction();
            clientes = clienteDAO.devolverTodos(Egreso.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return clientes;
    }
 
    public void guardarClienteNuevo(Egreso cliente) {
        try {
            HibernateUtil.beginTransaction();
            clienteDAO.nuevo(cliente);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }
 
    public void actualizarCliente(Egreso cliente){
        try {
            HibernateUtil.beginTransaction();
            clienteDAO.actualizar(cliente);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }        
    }
    
    public Egreso buscarClientePorID(Integer id) {
        Egreso cliente = null;
        try {
            HibernateUtil.beginTransaction();
            cliente = (Egreso) clienteDAO.buscarPorID(Egreso.class, id);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return cliente;
    }
 
    public void borrarCliente(Egreso cliente) {
        try {
            HibernateUtil.beginTransaction();
            clienteDAO.borrar(cliente);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }
}

