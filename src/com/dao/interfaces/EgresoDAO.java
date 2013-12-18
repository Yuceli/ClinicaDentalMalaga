

package com.dao.interfaces;

import com.dao.interfaces.GenericDAO;
import com.clinica.modelo.Egreso;


public interface EgresoDAO extends GenericDAO<Egreso, Integer>{
    public Egreso buscarPorConcepto(String concepto);
}
