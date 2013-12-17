/*
 * 
 * 
 * 
 */

package com.dao.manager;

import com.clinica.modelo.Usuario;
import com.dao.implementaciones.UsuarioDAOImp;
import com.dao.interfaces.UsuarioDAO;
import com.persistence.hibernate.HibernateUtil;
import javax.persistence.NonUniqueResultException;
import org.hibernate.HibernateException;


public class UsuarioMgrImp implements UsuarioMgr{

    @Override
    public Usuario buscarUsuarioPorNombre(String nombre) {
       Usuario usuario = null;
        try {
            HibernateUtil.beginTransaction();
            usuario = usuarioDAO.buscarPorNombre(nombre);
            HibernateUtil.commitTransaction();
        } catch (NonUniqueResultException ex) {
            System.out.println("A ocurrido un error inesperado");
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return usuario; 
    
    }
    
    /*
    Atributos privados
    */
    private UsuarioDAO usuarioDAO = new UsuarioDAOImp();
    
}
