

package com.dao.implementaciones;

import com.dao.interfaces.GenericDAO;
import java.io.Serializable;
import java.util.List;
import com.persistence.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {
 
    protected Session getSession() {
        return HibernateUtil.getSession();
    }
 
    public void nuevo(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.saveOrUpdate(entity);
    }
 
    public void actualizar(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.merge(entity);
    }
 
    public void borrar(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.delete(entity);
    }
 
    public List<T> buscarPredeterminado(Query query) {
        List<T> t;
        t = (List<T>) query.list();
        return t;
    }
 
    public T buscarUno(Query query) {
        T t;
        t = (T) query.uniqueResult();
        return t;
    }
 
    public T buscarPorID(Class clazz, Integer id) {
        Session hibernateSession = this.getSession();
        T t = null;
        t = (T) hibernateSession.get(clazz, id);
        return t;
    }
 
    public List devolverTodos(Class clazz) {
        Session hibernateSession = this.getSession();
        List T = null;
        Query query = hibernateSession.createQuery("from " + clazz.getName());
        T = query.list();
        return T;
    }
}
