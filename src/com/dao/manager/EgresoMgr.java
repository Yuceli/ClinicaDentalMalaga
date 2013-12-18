package com.dao.manager;

import java.util.List;
import com.clinica.modelo.Egreso;

public interface EgresoMgr {
   
    public void guardarEgresoNuevo(Egreso egreso);

    public void borrarEgreso(Egreso egreso);

    public Egreso buscarEgresoPorConcepto(String nombre);

    public Egreso buscarEgresoPorID(Integer id);

    public void actualizarDatosEgreso(Egreso egreso);

    public List<Egreso> cargarEgresos();

}
