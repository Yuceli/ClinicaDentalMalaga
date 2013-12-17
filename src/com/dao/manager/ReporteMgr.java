/*
 * 
 * 
 * 
 */
package com.dao.manager;

import com.clinica.modelo.Reporte;

public interface ReporteMgr {
    public void modificarRutaDeGuardado(String ruta);
    
    public void generarReporte();
    
    public void agregarFechas();
    
    public void agregarEncabezado();
    
    public void agregarSeccionIngresos();
    
    public void agregarSeccionEgresos();
    
}
