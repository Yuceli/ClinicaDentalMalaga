
package com.dao.manager;

import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import com.clinica.modelo.Ingreso;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;

public interface IngresoMgr {

    public void guardarIngresoNuevo(JTextField txtConcepto, JTextField txtTipoIngreso, JTextField txtMontoIngreso, JDateChooser dcFecha);

    public void borrarIngreso(JTable tablaIngresos);

    public void actualizarIngreso(JTextField txtID, JTextField txtConcepto, JTextField txtTipoIngreso, JTextField txtMontoIngreso, JDateChooser dcFecha);

    public double calcularIngresoDiario(Date dia);

    public double calcularIngresoSemanal();

    public double calcularIngresoMensual();

    public double calcularIngresoAnual();

    public Ingreso buscarIngresoPorID(Integer id);

    public Ingreso buscarIngresoPorNombre(String nombre);

    public List<Ingreso> cargarTodosLosIngresos();

    public void cargarTablaIngreso(JTable tablaIngresos);

}
