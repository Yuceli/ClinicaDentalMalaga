
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
import javax.persistence.NonUniqueResultException;
import org.hibernate.HibernateException;
import org.hibernate.Query;

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
    public Ingreso buscarIngresoPorNombre(String nombre) {
        Ingreso ingreso = null;
        try {
            HibernateUtil.beginTransaction();
            ingreso = buscarPorNombre(nombre);
            HibernateUtil.commitTransaction();
        } catch (NonUniqueResultException ex) {
            System.out.println("Handle your error here");
            System.out.println("Query returned more than one results.");
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return ingreso;
    }

    @Override
    public Ingreso buscarPorNombre(String nombre) {
        Ingreso ingreso = null;
        String sql = BuscarIngresoPorNombre;
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("nombre", nombre);
        ingreso = buscarUno(query);
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
    
    private static final String BuscarIngresoPorNombre = "SELECT c FROM Ingreso c WHERE c.nombre = :nombre";
}