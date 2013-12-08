

package com.dao.implementaciones;

import com.dao.interfaces.EgresoDAO;
import com.clinica.modelo.Egreso;
import com.persistence.hibernate.HibernateUtil;
import org.hibernate.Query;

public class EgresoDAOImpl extends GenericDAOImpl<Egreso, Integer> implements EgresoDAO{
    public Egreso buscarPorNombre(String nombre) {
        Egreso egreso = null;
        String sql = "SELECT c FROM Cliente c WHERE c.nombre = :nombre";
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("nombre", nombre);
        egreso = buscarUno(query);
        return egreso;
    }
}
