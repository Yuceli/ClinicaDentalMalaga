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
    
    public void limpiarCampos(JTextField id ,JTextField concepto, JTextField tipoEgreso, JTextField proveedor, JTextField monto, JDateChooser fecha) {
        concepto.setText("");
        tipoEgreso.setText("");
        proveedor.setText("");
        monto.setText("");
        id.setText("");
        fecha.setDate(null);
        
    }
    
}
