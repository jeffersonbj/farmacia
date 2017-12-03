package br.com.farmacia.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.farmacia.modelo.Cliente;

public class ClienteDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static List<Cliente> buscarPorNome(String nome){
		EntityManager em = new JPAUtil().getEntityManager();
		
		return em.createQuery("from Cliente where nome like :nome", Cliente.class)
				.setParameter("nome", "%"+nome+"%")
				.getResultList();
	}
	
}
