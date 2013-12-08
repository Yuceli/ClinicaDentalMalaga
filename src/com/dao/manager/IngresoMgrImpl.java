/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao.manejador.ingresos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.NonUniqueResultException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.dao.implementaciones.IngresoDAOImpl;
import com.clinica.modelo.Ingreso;
import com.dao.interfaces.IngresoDAO;
import com.persistence.hibernate.HibernateUtil;
import org.hibernate.HibernateException;

/**
 *
 * @author Yuceli
 */
public class IngresoMgrImpl implements IngresoMgr {
    private IngresoDAO ingresoDAO = new IngresoDAOImpl();
    
        public void guardarIngresoNuevo(Ingreso ingreso) {
        try {
            HibernateUtil.beginTransaction();
            ingresoDAO.nuevo(ingreso);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }
    
   
    public void borrarIngreso(Ingreso ingreso) {
        try {
            HibernateUtil.beginTransaction();
            ingresoDAO.borrar(ingreso);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }
    
    
    public void actualizarIngreso(Ingreso ingreso) {
        try {
            HibernateUtil.beginTransaction();
            ingresoDAO.actualizar(ingreso);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }
    
   
    public double calcularIngresoDiario(Date dia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public double calcularIngresoSemanal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public double calcularIngresoMensual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public double calcularIngresoAnual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Ingreso buscarIngresoPorID(Integer id) {
        Ingreso ingreso = null;
        try {
            HibernateUtil.beginTransaction();
            ingreso = (Ingreso) ingresoDAO.buscarPorID(Ingreso.class, id);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return ingreso;
    }

    
    public Ingreso buscarIngresoPorNombre(String nombre) {
        Ingreso ingreso = null;
        try {
            HibernateUtil.beginTransaction();
            ingreso = ingresoDAO.buscarPorNombre(nombre);
            HibernateUtil.commitTransaction();
        } catch (NonUniqueResultException ex) {
            System.out.println("Handle your error here");
            System.out.println("Query returned more than one results.");
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return ingreso;
    }

    
    public List<Ingreso> cargarTodosLosIngresos() {
        List<Ingreso> ingresos = new ArrayList<Ingreso>();
        try {
            HibernateUtil.beginTransaction();
            ingresos = ingresoDAO.devolverTodos(Ingreso.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return ingresos;
    }

    
    public void cargarTablaIngreso(JTable tablaIngresos) {
        try {
            final DefaultTableModel modelo = (DefaultTableModel) tablaIngresos.getModel();
            modelo.setRowCount(0);
            List ingresos = cargarTodosLosIngresos();
            Object datos[] = new Object[5];
     
            Iterator<Ingreso> iterator = ingresos.iterator();
            while (iterator.hasNext()) {
                Ingreso ingreso = (Ingreso) iterator.next();
                datos[0] = ingreso.getId();
                datos[1] = ingreso.getConcepto();
                datos[2] = ingreso.getTipoDeIngreso();
                datos[3] = ingreso.getMonto();
//                datos[4] = ingreso.getFechaDeVenta();
                modelo.addRow(datos);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
