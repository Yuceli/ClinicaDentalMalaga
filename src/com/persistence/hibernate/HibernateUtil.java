
package com.persistence.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class HibernateUtil {
 
	private static final SessionFactory sessionFactory = buildSessionFactory();
 
	private static SessionFactory buildSessionFactory() {
		try {
			SessionFactory sessionF = new Configuration().configure(
					"/com/configHibernate/hibernate.cfg.xml")
					.buildSessionFactory();
 
			return sessionF;
 
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
 
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
        
        
        public static Session beginTransaction() {
            Session hibernateSession = HibernateUtil.getSession();
            hibernateSession.beginTransaction();
            return hibernateSession;
        }
 
        public static void commitTransaction() {
            HibernateUtil.getSession().getTransaction().commit();
        }
 
        public static void rollbackTransaction() {
            HibernateUtil.getSession().getTransaction().rollback();
        }
 
        public static void closeSession() {
            HibernateUtil.getSession().close();
        }
 
        public static Session getSession() {
            Session hibernateSession = sessionFactory.getCurrentSession();
            return hibernateSession;
        }        
}