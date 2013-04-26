package com.livro.capitulo3.crudannotations;

import java.sql.Date;
import java.util.List;
import org.hibernate.*;

import com.capitulo3.crudxml.Contato;
import com.capitulo3.crudxml.ContatoCrudXML;
import com.livro.capitulo3.conexao.HibernateUtil;
public class ContatoCrudAnnotations {

	public void salvar(ContatoAnnotations contato){
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
				System.out.println("erro ao fechar opera�aoi de incer�ao mensagem :"+e.getMessage());
			}
		}
		
	}
	
	public void atualizar(ContatoAnnotations contato){
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
				System.out.println("erro ao fechar a  atualiza�ao  mensagem :"+e.getMessage());
			}
		}
	}

	public void excluir(ContatoAnnotations contato){
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
	
	public List<ContatoAnnotations> listar(){
		
		Session sessao=null;
		Transaction transacao=null;
		Query cosulta=null;
		List<ContatoAnnotations> resultado=null;
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
				System.out.println("erro a o fechar opera�ao consulta. mensagem : " +e.getMessage());
			}
		}
		
	}
	
	public ContatoAnnotations buscaContato(int valor){
		ContatoAnnotations contato=null;
		Session sessao=null;
		Transaction transacao=null;
		Query consulta=null;
		try {
			sessao=HibernateUtil.getSessionFactory().openSession();
			transacao=sessao.beginTransaction();
			consulta=sessao.createQuery("from Contato where codigo=:parametro");
			consulta.setInteger("parametro", valor);
			contato=(ContatoAnnotations) consulta.uniqueResult();
			transacao.commit();
			return contato;
		} catch (HibernateException e) {
			System.out.println("nao foi possivel buscar contato erro ; "+e.getMessage());
		}finally{
			try {
				sessao.close();
				
			} catch (Throwable e) {
				System.out.println("erro a o fechar opera�ao de busca. mensagem : " +e.getMessage());
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
