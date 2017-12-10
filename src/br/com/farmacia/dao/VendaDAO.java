package br.com.farmacia.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

import br.com.farmacia.Exception.ProdutoEsgotadoException;
import br.com.farmacia.modelo.Produto;
import br.com.farmacia.modelo.Venda;
import br.com.farmacia.modelo.VendaProduto;

@SuppressWarnings("serial")
public class VendaDAO implements Serializable {

	public static void adicionar(Venda venda, List<VendaProduto> vendaProdutos) throws ProdutoEsgotadoException {
		EntityManager em = new JPAUtil().getEntityManager();

		try {
			em.getTransaction().begin();

			em.persist(venda);

			for (VendaProduto item : vendaProdutos) {
				item.setVenda(venda);

				em.persist(item);

				Produto produto = em.find(Produto.class, item.getProduto().getId());
				if (produto.getQuantidade() > 0) {
					int quantidade = produto.getQuantidade() - item.getQuantidade();

					if (quantidade > 0)
						produto.setQuantidade(quantidade);
					else
						throw new ProdutoEsgotadoException("Quantidade do produto em estoque \'" + produto.getNome() + "\' inferior ao pedido.");

					em.merge(produto);

				} else {
					throw new ProdutoEsgotadoException("Produto \'" + produto.getNome() + "\' esgotado.");
				}

			}

			em.getTransaction().commit();

		} catch (Exception ex) {
			if (em.getTransaction() != null)
				em.getTransaction().rollback();

			throw ex;
		} finally {
			em.close();
		}

	}

}
