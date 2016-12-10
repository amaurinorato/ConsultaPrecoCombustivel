package br.com.fiap.combustivel.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.fiap.combustivel.model.Cadastro;
import br.com.fiap.combustivel.util.HibernateUtil;

public class CadastroDAO {
	
	public void salvar(Cadastro cadastro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		  
        session.beginTransaction();
        session.saveOrUpdate(cadastro);
        session.getTransaction().commit();
	}
	
	public void deletar(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		  
		Cadastro cadastro = this.findById(id);
        session.beginTransaction();
        session.delete(cadastro);
        session.getTransaction().commit();
	}
	
	public List<Cadastro> listar () {
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.query.Query<Cadastro> q = session.createQuery("From Cadastro ", Cadastro.class);
        
        List<Cadastro> resultList = q.getResultList();
        return resultList;
	}
	
	public Cadastro findById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.query.Query<Cadastro> q = session.createQuery("From Cadastro where id = :id", Cadastro.class);
		q.setParameter("id", id);
		return q.getSingleResult();
	}

}
