package com.livro.capitulo3.conexao;

import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;





public class HibernateUtil {
	
	private static final SessionFactory sessionFactory=buildSessionfactory() ;
	
	
	private static SessionFactory buildSessionfactory(){
		try {
			AnnotationConfiguration cfg=new AnnotationConfiguration();
		
			cfg.configure("hibernate.cfg.xml");
			return cfg.buildSessionFactory();
		} catch (Throwable e) {
			System.out.println("Criaçao inicial do objeto sesion falhou.erro: "+e);
			throw new ExceptionInInitializerError(e);
		}
		
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}

}
