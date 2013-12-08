/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.interfaces;

import com.dao.interfaces.GenericDAO;
import java.util.Date;
import com.clinica.modelo.Ingreso;

/**
 *
 * @author Yuceli
 */
public interface IngresoDAO extends GenericDAO<Ingreso, Integer> {

    public Ingreso buscarPorNombre(String nombre);

    public double obtenerIngresoDiario(Date dia);

    public double obtenerIngresoSemanal();

    public double obtenerIngresoMensual();

    public double obtenerIngresoAnual();

}
