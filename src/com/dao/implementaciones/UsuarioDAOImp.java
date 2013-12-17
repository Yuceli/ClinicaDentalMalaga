/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao.implementaciones;

import com.clinica.modelo.Usuario;
import com.dao.interfaces.UsuarioDAO;
import com.persistence.hibernate.HibernateUtil;
import org.hibernate.Query;

/**
 *
 * @author Yuko
 */
public class UsuarioDAOImp extends GenericDAOImpl<Usuario, Integer> implements UsuarioDAO{

    @Override
    public Usuario buscarPorNombre(String nombre) {
        Usuario usuario = null;
        String sql = "SELECT c FROM Usuario c WHERE c.nombre = :nombre";
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("nombre", nombre);
        usuario = buscarUno(query);
        return usuario;
    }
    
}
