package com.capitulo3.crudxml;


import java.sql.Date;
import java.util.List;
import org.hibernate.*;
import com.livro.capitulo3.conexao.HibernateUtil;

public class ContatoCrudXML {
	
	public void salvar(Contato contato){
		Session sessao=null;
		Transaction transacao=null;
		try {
			
			sessao=HibernateUtil.getSessionFactory().openSession();
			transacao=sessao.beginTransaction();
			sessao.save(contato);
			transacao.commit();
		} catch (HibernateException e) {
			System.out.println("nao foi possivel inserir contato. erro :"+e.getMessage());
		}finally{
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("erro ao fechar operaçaoi de incerçao mensagem :"+e.getMessage());
			}
		}
		
	}
	
	public void atualizar(Contato contato){
		Session sessao=null;
		Transaction transacao=null;
		try {
			sessao=HibernateUtil.getSessionFactory().openSession();
			transacao=sessao.beginTransaction();
			sessao.update(contato);
			transacao.commit();
		} catch (HibernateException e) {
			System.out.println("erro ao alterar o contatoo mensagem :"+e.getMessage());
		}finally{
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("erro ao fechar a  atualizaçao  mensagem :"+e.getMessage());
			}
		}
	}

	public void excluir(Contato contato){
		Session sessao=null;
		Transaction transacao=null;
		try {
			sessao=HibernateUtil.getSessionFactory().openSession();
			transacao=sessao.beginTransaction();
			sessao.delete(contato);
			transacao.commit();
		} catch (HibernateException e) {
			System.out.println("erro ao deletar o contatoo mensagem :"+e.getMessage());
		}finally{
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("erro ao fechar o delete do contato  mensagem :"+e.getMessage());
			}
		}
	}
	
	public List<Contato> listar(){
		
		Session sessao=null;
		Transaction transacao=null;
		Query cosulta=null;
		List<Contato> resultado=null;
		try {
			sessao=HibernateUtil.getSessionFactory().openSession();
			transacao=sessao.beginTransaction();
			cosulta=sessao.createQuery("from Contato");
			resultado=cosulta.list();
			transacao.commit();
			return resultado;
		} catch (HibernateException e) {
			System.out.println("nao foi possivel selecionar contato erro ; "+e.getMessage());
			throw new HibernateException(e);
		}finally{
			try {
				sessao.close();
				
			} catch (Throwable e) {
				System.out.println("erro a o fechar operaçao consulta. mensagem : " +e.getMessage());
			}
		}
		
	}
	
	public Contato buscaContato(int valor){
		Contato contato=null;
		Session sessao=null;
		Transaction transacao=null;
		Query consulta=null;
		try {
			sessao=HibernateUtil.getSessionFactory().openSession();
			transacao=sessao.beginTransaction();
			consulta=sessao.createQuery("from Contato where codigo=:parametro");
			consulta.setInteger("parametro", valor);
			contato=(Contato) consulta.uniqueResult();
			transacao.commit();
			return contato;
		} catch (HibernateException e) {
			System.out.println("nao foi possivel buscar contato erro ; "+e.getMessage());
		}finally{
			try {
				sessao.close();
				
			} catch (Throwable e) {
				System.out.println("erro a o fechar operaçao de busca. mensagem : " +e.getMessage());
			}
		}
		return contato;
				
	}
	public static void main(String[] args) {
		
		ContatoCrudXML contatoCrudXML=new ContatoCrudXML();
		String[] nomes={"fulano","jose","guta"};
		String[] telefones={"5565565","63566","55656"};
		String[] emails={"sbdsh","dnfdkfdfkd","dkdjskdj"};
		String[] obsvs={"judyudhd","shdhwudhsu","wkwiejwejhw"};
		Contato contato=null;
		
		for (int i = 0; i < nomes.length; i++) {
			contato=new Contato();
			contato.setNome(nomes[i]);
			contato.setTelefone(telefones[i]);
			contato.setEmail(emails[i]);
			contato.setDatacadastro(new Date(System.currentTimeMillis()));
			contato.setObservacao(obsvs[i]);
			contatoCrudXML.salvar(contato);
			
		} 

		System.out.println("total cadastrados : "+contatoCrudXML.listar().size());
	}

}
