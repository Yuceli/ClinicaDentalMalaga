

package com.controlador.egresos;

import java.util.List;
import com.clinica.modelo.Egreso;

public interface EgresoMgr {
   public Egreso buscarClientePorNombre(String nombre);
 
    public List<Egreso> cargarTodosLosClientes();
 
    public void guardarClienteNuevo(Egreso cliente);
    
    public void actualizarCliente(Egreso cliente);
 
    public Egreso buscarClientePorID(Integer id);
 
    public void borrarCliente(Egreso cliente);   
}
