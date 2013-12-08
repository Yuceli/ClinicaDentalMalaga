/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao.implementaciones;

import java.util.Date;
import com.clinica.modelo.Ingreso;
import com.dao.interfaces.IngresoDAO;
import com.persistence.hibernate.HibernateUtil;
import org.hibernate.Query;

/**
 *
 * @author Yuceli
 */
public class IngresoDAOImpl extends GenericDAOImpl<Ingreso, Integer> implements IngresoDAO{

    @Override
    public Ingreso buscarPorNombre(String nombre) {
        Ingreso ingresos = null;
        String sql = BuscarIngresoPorNombre+nombre;
        Query query = HibernateUtil.getSession().createQuery(sql).
                setParameter("nombre", nombre);
        ingresos = buscarUno(query);
        return ingresos;
    }

    @Override
    public double obtenerIngresoDiario(Date dia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double obtenerIngresoSemanal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double obtenerIngresoMensual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double obtenerIngresoAnual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static final String BuscarIngresoPorNombre = "SELECT c FROM Ingreso c WHERE c.nombre = :nombre";
}