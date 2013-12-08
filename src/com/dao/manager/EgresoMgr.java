package com.dao.manager;

import java.util.List;
import com.clinica.modelo.Egreso;

public interface EgresoMgr {

    public Egreso buscarEgresoPorConcepto(String nombre);

    public List<Egreso> cargarEgresos();

    public void guardarEgresoNuevo(Egreso egreso);

    public void actualizarDatosEgreso(Egreso egreso);

    public Egreso buscarEgresoPorID(Integer id);

    public void borrarEgreso(Egreso egreso);
}
