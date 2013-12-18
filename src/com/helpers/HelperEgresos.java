/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.helpers;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;

/**
 *
 * @author Yuceli
 */
public class HelperEgresos {
    
    public void limpiarCampos(JTextField idField , JTextField conceptoField, JTextField tipoEgresoField, JTextField proveedorField, JTextField montoField, JDateChooser fechaCompra) {
        idField.setText("");
        conceptoField.setText("");
        tipoEgresoField.setText("");
        proveedorField.setText("");
        montoField.setText("");
        fechaCompra.setDate(null);
        
        
    }
    
}
