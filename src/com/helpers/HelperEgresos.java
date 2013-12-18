/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import com.clinica.modelo.Egreso;
import com.dao.manager.EgresoMgr;
import com.dao.manager.EgresoMgrImpl;
import com.toedter.calendar.JDateChooser;
import com.vistas.VistaEgresos;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Yuceli
 */
public class HelperEgresos {

    public void limpiarCampos(JTextField idField, JTextField conceptoField, JTextField tipoEgresoField, JTextField proveedorField, JTextField montoField, JDateChooser fechaCompra) {
        idField.setText("");
        conceptoField.setText("");
        tipoEgresoField.setText("");
        proveedorField.setText("");
        montoField.setText("");
        fechaCompra.setDate(null);

    }

}
