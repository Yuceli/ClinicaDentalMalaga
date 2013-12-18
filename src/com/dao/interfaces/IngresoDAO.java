
package com.dao.interfaces;
/**
 *
 * @author Arian Castillo
 */
import com.clinica.modelo.Ingreso;
import java.util.List;

public interface IngresoDAO extends GenericDAO<Ingreso, Integer> {

    public void guardarIngresoNuevo(Ingreso ingreso);
    public void borrarIngreso(Ingreso ingreso);
    public void actualizarIngreso(Ingreso ingreso);
    
    public Ingreso buscarIngresoPorID(Integer id);
 
    public List<Ingreso> cargarTodosLosIngresos();
}
