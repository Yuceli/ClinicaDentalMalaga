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
    
    
     public void guardarEgresoNuevo(Egreso egreso) {
        try {
            HibernateUtil.beginTransaction();
            egresoDAO.nuevo(egreso);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }
    
    public void borrarEgreso(Egreso egreso) {
        try {
            HibernateUtil.beginTransaction();
            egresoDAO.borrar(egreso);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }

    public Egreso buscarEgresoPorConcepto(String concepto) {
        Egreso egreso = null;
        try {
            HibernateUtil.beginTransaction();
            egreso = egresoDAO.buscarPorConcepto(concepto);
            HibernateUtil.commitTransaction();
        } catch (NonUniqueResultException ex) {
            System.out.println("A ocurrido un error inesperado");
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return egreso;
    }
    
    public Egreso buscarEgresoPorID(Integer id) {
        Egreso egreso = null;
        try {
            HibernateUtil.beginTransaction();
            egreso = (Egreso) egresoDAO.buscarPorID(Egreso.class, id);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return egreso;
    }

   
    public void actualizarDatosEgreso(Egreso egreso) {
        try {
            HibernateUtil.beginTransaction();
            egresoDAO.actualizar(egreso);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
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
     
      private EgresoDAO egresoDAO = new EgresoDAOImpl();
}
