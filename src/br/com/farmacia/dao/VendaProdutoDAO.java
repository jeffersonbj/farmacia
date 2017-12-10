package br.com.farmacia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.farmacia.modelo.VendaProduto;

public class VendaProdutoDAO {
	
	public static List<VendaProduto> buscarPorVenda(int vendaId){
		EntityManager em = new JPAUtil().getEntityManager();
		
		Query q = em.createNativeQuery("select vp.* from vendaProduto vp where venda_id = :vendaId", VendaProduto.class);
		q.setParameter("vendaId", vendaId);
		
		@SuppressWarnings("unchecked")
		List<VendaProduto> lista = q.getResultList();
		
		return lista;
	}
}
