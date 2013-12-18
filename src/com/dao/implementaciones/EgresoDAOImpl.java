

package com.dao.implementaciones;

import com.dao.interfaces.EgresoDAO;
import com.clinica.modelo.Egreso;
import com.persistence.hibernate.HibernateUtil;
import org.hibernate.Query;

public class EgresoDAOImpl extends GenericDAOImpl<Egreso, Integer> implements EgresoDAO{
    public Egreso buscarPorConcepto(String concepto) {
        Egreso egreso = null;
        String sql = BuscarEgresoPorConcepto+concepto;
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("concepto", concepto);
        egreso = buscarUno(query);
        return egreso;
    }
    
    private static final String BuscarEgresoPorConcepto = "SELECT egreso FROM Egreso egreso WHERE egreso.concepto = :concepto";
}
