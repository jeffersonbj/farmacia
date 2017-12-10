package br.com.farmacia.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import br.com.farmacia.modelo.Venda;
import br.com.farmacia.modelo.VendaProduto;

@SuppressWarnings("serial")
public class VendaDAO implements Serializable {

	public static void adicionar(Venda venda, List<VendaProduto> vendaProdutos) {
		EntityManager em = new JPAUtil().getEntityManager();

		try {
			em.getTransaction().begin();
			
			em.persist(venda);
			
			for (VendaProduto item : vendaProdutos) {				
				item.setVenda(venda);
				
				em.persist(item);
			}
			
			em.getTransaction().commit();
			
		} catch (Exception ex) {
			if (em.getTransaction() != null)
				em.getTransaction().rollback();
			
			throw ex;
		}finally{
			em.close();
		}

	}

}
