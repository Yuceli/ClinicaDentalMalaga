package com.dao.implementaciones;

import com.dao.interfaces.EgresoDAO;
import com.clinica.modelo.Egreso;
import com.persistence.hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NonUniqueResultException;
import org.hibernate.HibernateException;
import org.hibernate.Query;

public class EgresoDAOImpl extends GenericDAOImpl<Egreso, Integer> implements EgresoDAO {

    @Override
    public void guardarEgresoNuevo(Egreso egreso) {
        try {
            HibernateUtil.beginTransaction();
            nuevo(egreso);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }

    @Override
    public void borrarEgreso(Egreso egreso) {
        try {
            HibernateUtil.beginTransaction();
            borrar(egreso);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }

    @Override
    public Egreso buscarEgresoPorConcepto(String concepto) {
        Egreso egreso = null;
        try {
            HibernateUtil.beginTransaction();
            egreso = buscarPorConcepto(concepto);
            HibernateUtil.commitTransaction();
        } catch (NonUniqueResultException ex) {
            System.out.println("A ocurrido un error inesperado");
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return egreso;
    }

    @Override
    public Egreso buscarEgresoPorID(Integer id) {
        Egreso egreso = null;
        try {
            HibernateUtil.beginTransaction();
            egreso = (Egreso)buscarPorID(Egreso.class, id);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return egreso;
    }

    @Override
    public void actualizarDatosEgreso(Egreso egreso) {
        try {
            HibernateUtil.beginTransaction();
            actualizar(egreso);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }

    @Override
    public List<Egreso> cargarEgresos() {
        List<Egreso> clientes = new ArrayList<Egreso>();
        try {
            HibernateUtil.beginTransaction();
            clientes = devolverTodos(Egreso.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return clientes;
    }

    @Override
    public Egreso buscarPorConcepto(String concepto) {
        Egreso egreso = null;
        String sql = BuscarEgresoPorConcepto;
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("concepto", concepto);
        egreso = buscarUno(query);
        return egreso;
    }

    private static final String BuscarEgresoPorConcepto = "SELECT egreso FROM Egreso egreso WHERE egreso.concepto = :concepto";
    

}
