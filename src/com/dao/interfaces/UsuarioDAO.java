/*
 * 
 * 
 * 
 */

package com.dao.interfaces;

import com.clinica.modelo.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario, Integer>{
    public Usuario buscarPorNombre(String nombre);
}
