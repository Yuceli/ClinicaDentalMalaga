

package com.dao.interfaces;

import java.io.*;
import java.util.*;
import org.hibernate.Query;

public interface GenericDAO<T, ID extends Serializable> {
 
    public void nuevo(T entity);
 
    public void actualizar(T entity);
 
    public void borrar(T entity);
 
    public List<T> buscarPredeterminado(Query query);
 
    public T buscarUno(Query query);
 
    public List devolverTodos(Class clazz);
 
    public T buscarPorID(Class clazz, Integer id);
}
