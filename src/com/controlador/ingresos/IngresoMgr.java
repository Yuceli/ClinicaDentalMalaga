/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlador.ingresos;

import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import com.clinica.modelo.Ingreso;

/**
 *
 * @author Yuceli
 */
public interface IngresoMgr {

    public void guardarIngresoNuevo(Ingreso ingreso);

    public void borrarIngreso(Ingreso ingreso);

    public void actualizarIngreso(Ingreso ingreso);

    public double calcularIngresoDiario(Date dia);

    public double calcularIngresoSemanal();

    public double calcularIngresoMensual();

    public double calcularIngresoAnual();

    public Ingreso buscarIngresoPorID(Integer id);

    public Ingreso buscarIngresoPorNombre(String nombre);

    public List<Ingreso> cargarTodosLosIngresos();

    public void cargarTablaIngreso(JTable tablaIngresos);

}
