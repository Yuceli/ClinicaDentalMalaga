package com.dao.interfaces;

import com.dao.interfaces.GenericDAO;
import com.clinica.modelo.Egreso;
import java.util.List;

public interface EgresoDAO extends GenericDAO<Egreso, Integer> {

    public Egreso buscarPorConcepto(String concepto);

    public void guardarEgresoNuevo(Egreso egreso);

    public void borrarEgreso(Egreso egreso);

    public Egreso buscarEgresoPorConcepto(String nombre);

    public Egreso buscarEgresoPorID(Integer id);

    public void actualizarDatosEgreso(Egreso egreso);

    public List<Egreso> cargarEgresos();
}
