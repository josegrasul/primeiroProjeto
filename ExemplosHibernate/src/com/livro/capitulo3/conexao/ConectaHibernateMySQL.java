package com.livro.capitulo3.conexao;

import org.hibernate.Session;

public class ConectaHibernateMySQL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Session session=null;
		try {
			session=HibernateUtil.getSessionFactory().openSession();
			System.out.println("conectou");
			
		
		}finally{

	System.out.println();
	}
	}
}