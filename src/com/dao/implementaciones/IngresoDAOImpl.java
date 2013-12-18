
package com.dao.implementaciones;
/**
 *
 * @author Arian Castillo
 */
import com.clinica.modelo.Ingreso;
import com.dao.interfaces.IngresoDAO;
import com.persistence.hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;

public class IngresoDAOImpl extends GenericDAOImpl<Ingreso, Integer> implements IngresoDAO{

    @Override
    public void guardarIngresoNuevo(Ingreso ingreso) {
        try {
            HibernateUtil.beginTransaction();
            nuevo(ingreso);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }

    @Override
    public void borrarIngreso(Ingreso ingreso) {
        try {
            HibernateUtil.beginTransaction();
            borrar(ingreso);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }

    @Override
    public void actualizarIngreso(Ingreso ingreso) {
        try {
            HibernateUtil.beginTransaction();
            actualizar(ingreso);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }

    @Override
    public Ingreso buscarIngresoPorID(Integer id) {
        Ingreso ingreso = null;
        try {
            HibernateUtil.beginTransaction();
            ingreso = (Ingreso) buscarPorID(Ingreso.class, id);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return ingreso;
    }
    
    @Override
    public List<Ingreso> cargarTodosLosIngresos() {
        List<Ingreso> ingresos = new ArrayList<Ingreso>();
        try {
            HibernateUtil.beginTransaction();
            ingresos = devolverTodos(Ingreso.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return ingresos;
    }
}