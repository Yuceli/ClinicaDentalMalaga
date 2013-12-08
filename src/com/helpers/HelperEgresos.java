/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;


import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.clinica.modelo.Egreso;
import com.dao.manager.EgresoMgrImpl;
import com.vistas.VistaEgresos;

/**
 *
 * @author Yuceli
 */
public class HelperEgresos {

    private EgresoMgrImpl egresos;
    private VistaEgresos vistaEgresos;
    Vector nombresColumnas;

    public HelperEgresos() {
        egresos = new EgresoMgrImpl();
        nombresColumnas = new Vector();
        nombresColumnas.add("ID");
        nombresColumnas.add("Nombre");
        nombresColumnas.add("Direccion");
        nombresColumnas.add("Correo");

    }

    private Vector VectorToArrayList(ArrayList<Egreso> a) {
        Vector vectorTabla = new Vector();

        for (int i = 0; i < a.size(); i++) {
            Egreso c = a.get(i);
            Vector v = new Vector();
            v.add(c.getId());
            v.add(c.getConcepto());
            v.add(c.getTipoEgreso());
            v.add(c.getProveedor());

            vectorTabla.add(v);
        }
        return vectorTabla;

    }

    public void cargarTabla(JTable tabla) {
        ArrayList<Egreso> egreso = (ArrayList<Egreso>) egresos.cargarEgresos();
        Vector v = VectorToArrayList(egreso);
        DefaultTableModel dtm = new DefaultTableModel(v, nombresColumnas);
        tabla.setModel(dtm);
    }

    public void actualizar(int id, String txt_nombre, String txt_direccion, String txt_correo) {
        Egreso cliente = new Egreso(txt_nombre, txt_direccion, txt_correo);
        cliente.setId(id);
        this.egresos.actualizarDatosEgreso(cliente);

    }

    public void borrar(int id, String nombre, String direccion, String correo) {
        Egreso cliente = new Egreso(nombre, direccion, correo);
        cliente.setId(id);
        this.egresos.borrarEgreso(cliente);
    }
    
    public void añadirCliente(JTextField name, JTextField address, JTextField email){
        String nombre = name.getText();
        String direccion = address.getText();
        String correo = email.getText();
        Egreso cliente = new Egreso(nombre, direccion, correo);
        this.egresos.guardarEgresoNuevo(cliente);
    }
    
    public void borrarCliente(JTextField idField, JTextField nombreField, JTextField direccionField, JTextField correoField){
        String nombre = nombreField.getText();
        String direccion  = direccionField.getText();
        String correo = correoField.getText();
        int id = Integer.parseInt(idField.getText());
        Egreso cliente = new Egreso(nombre, direccion, correo);
        cliente.setId(id);
        this.egresos.borrarEgreso(cliente);
    }
    
    public void buscarDatosPorId(Integer ID, JTextField id, JTextField nombre, JTextField direccion, JTextField correo){
        Egreso cliente = egresos.buscarEgresoPorID(ID);
        id.setText(String.valueOf(cliente.getId()));
        nombre.setText(cliente.getConcepto());
        direccion.setText(cliente.getTipoEgreso());
        correo.setText(cliente.getProveedor());
        
    }
    
    public void buscarDatosPorNombre(String name, JTextField id, JTextField nombre, JTextField direccion, JTextField correo){
        Egreso cliente = egresos.buscarEgresoPorConcepto(name);
        id.setText(String.valueOf(cliente.getId()));
        nombre.setText(cliente.getConcepto());
        direccion.setText(cliente.getTipoEgreso());
        correo.setText(cliente.getProveedor());
    }
    
    
   
     


}
