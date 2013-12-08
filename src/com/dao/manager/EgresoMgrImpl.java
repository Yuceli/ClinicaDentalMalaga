package com.dao.manager;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NonUniqueResultException;
import com.dao.interfaces.EgresoDAO;
import com.dao.implementaciones.EgresoDAOImpl;
import com.clinica.modelo.Egreso;
import com.persistence.hibernate.HibernateUtil;
import org.hibernate.HibernateException;

public class EgresoMgrImpl implements EgresoMgr {

    private EgresoDAO egresoDAO = new EgresoDAOImpl();

    public Egreso buscarEgresoPorConcepto(String nombre) {
        Egreso cliente = null;
        try {
            HibernateUtil.beginTransaction();
            cliente = egresoDAO.buscarPorNombre(nombre);
            HibernateUtil.commitTransaction();
        } catch (NonUniqueResultException ex) {
            System.out.println("A ocurrido un error inesperado");
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return cliente;
    }

    public List<Egreso> cargarEgresos() {
        List<Egreso> clientes = new ArrayList<Egreso>();
        try {
            HibernateUtil.beginTransaction();
            clientes = egresoDAO.devolverTodos(Egreso.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return clientes;
    }

    public void guardarEgresoNuevo(Egreso cliente) {
        try {
            HibernateUtil.beginTransaction();
            egresoDAO.nuevo(cliente);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }

    public void actualizarDatosEgreso(Egreso cliente) {
        try {
            HibernateUtil.beginTransaction();
            egresoDAO.actualizar(cliente);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }

    public Egreso buscarEgresoPorID(Integer id) {
        Egreso cliente = null;
        try {
            HibernateUtil.beginTransaction();
            cliente = (Egreso) egresoDAO.buscarPorID(Egreso.class, id);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return cliente;
    }

    public void borrarEgreso(Egreso cliente) {
        try {
            HibernateUtil.beginTransaction();
            egresoDAO.borrar(cliente);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }
}
