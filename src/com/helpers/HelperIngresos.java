/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.helpers;


import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.clinica.modelo.Ingreso;
import com.dao.manejador.ingresos.IngresoMgrImpl;
import com.vistas.VistaIngresos;

/**
 *
 * @author Yuceli
 */
public class HelperIngresos {
    
    private IngresoMgrImpl ingresoMgr;
    private VistaIngresos ventana;
    Vector conceptosCol;

    public HelperIngresos() {
        ingresoMgr = new IngresoMgrImpl();
        conceptosCol = new Vector();
        conceptosCol.add("ID");
        conceptosCol.add("Concepto");
        conceptosCol.add("TipoIngreso");
//        conceptosCol.add("Monto");

    }

    private Vector VectorToArrayList(ArrayList<Ingreso> a) {
        Vector vectorTabla = new Vector();

        for (int i = 0; i < a.size(); i++) {
            Ingreso c = a.get(i);
            Vector v = new Vector();
            v.add(c.getId());
            v.add(c.getConcepto());
            v.add(c.getTipoDeIngreso());
            

            vectorTabla.add(v);
        }
        return vectorTabla;

    }

    public void cargarTabla(JTable tabla) {
        ArrayList<Ingreso> ingresoMgrs = (ArrayList<Ingreso>) ingresoMgr.cargarTodosLosIngresos();
        Vector v = VectorToArrayList(ingresoMgrs);
        DefaultTableModel dtm = new DefaultTableModel(v, conceptosCol);
        tabla.setModel(dtm);
    }

    public void actualizar(int id, String txt_concepto, String txt_tipoIngreso) {
        Ingreso ingreso = new Ingreso(txt_concepto, txt_tipoIngreso);
        ingreso.setId(id);
        this.ingresoMgr.actualizarIngreso(ingreso);

    }

    public void borrar(int id, String concepto, String direccion) {
        Ingreso ingreso = new Ingreso(concepto, direccion);
        ingreso.setId(id);
        this.ingresoMgr.borrarIngreso(ingreso);
    }
    
    public void añadirCliente(JTextField concept, JTextField type){
        String concepto = concept.getText();
        String tipo = type.getText();
        Ingreso ingreso = new Ingreso(concepto, tipo);
        this.ingresoMgr.guardarIngresoNuevo(ingreso);
    }
    
    public void borrarCliente(JTextField idField, JTextField conceptoField, JTextField typeField){
        String concepto = conceptoField.getText();
        String tipo = typeField.getText();
        int id = Integer.parseInt(idField.getText());
        Ingreso ingreso = new Ingreso(concepto, tipo);
        ingreso.setId(id);
        this.ingresoMgr.borrarIngreso(ingreso);
    }
    
    public void buscarDatosPorId(Integer ID, JTextField id, JTextField concepto, JTextField tipo){
        Ingreso ingreso = ingresoMgr.buscarIngresoPorID(ID);
        id.setText(String.valueOf(ingreso.getId()));
        concepto.setText(ingreso.getConcepto());
        tipo.setText(ingreso.getTipoDeIngreso());
        
        
    }
    
    public void buscarDatosPorNombre(String concept, JTextField id, JTextField concepto, JTextField tipo){
        Ingreso ingreso = ingresoMgr.buscarIngresoPorNombre(concept);
        id.setText(String.valueOf(ingreso.getId()));
        concepto.setText(ingreso.getConcepto());
        tipo.setText(ingreso.getTipoDeIngreso());
        
    }
    
    
}
