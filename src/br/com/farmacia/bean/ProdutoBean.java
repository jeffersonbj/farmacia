package br.com.farmacia.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.farmacia.dao.DAO;
import br.com.farmacia.modelo.Produto;
import br.com.farmacia.util.JSFUtil;

@ManagedBean
public class ProdutoBean {

	private Produto produto = new Produto();
	private List<Produto> produtos = null;

	public Produto getProduto() {
		return produto;
	}

	public List<Produto> getProdutos() {
		if (this.produtos == null) {
			this.produtos = new DAO<Produto>(Produto.class).listaTodos();
		}
		return this.produtos;
	}

	public String gravar() {		
		if (this.produto.getId() > 0) {
			new DAO<Produto>(Produto.class).atualiza(this.produto);
		} else {
			this.produto.setDataDaEntrada(new Date());

			new DAO<Produto>(Produto.class).adiciona(this.produto);
		}
		this.produto = new Produto();
		return "listaProduto.xhtml";

	}
	
	public String acaoAbrirInclusao() {
		this.produto = new Produto();

		return "produto?faces-redirect=true";
	}
	
	public String acaoAbrirAlteracao() {

		int id = JSFUtil.getParametroInteger("itemId");
		this.produto = new DAO<Produto>(Produto.class).buscaPorId(id);

		return "produto";
	}

	public String acaoAbrirListagem() {
		this.produto = new Produto();

		return "listaProduto?faces-redirect=true";
	}
	
	public String acaoExcluir() {
		System.out.println("exclusão");

		int id = JSFUtil.getParametroInteger("itemId");

		System.out.println("ID: " + id);
		Produto objeto = new DAO<Produto>(Produto.class).buscaPorId(id);
		new DAO<Produto>(Produto.class).remove(objeto);

		this.produto = new Produto();
		this.produtos = null;

		return "listaProduto";
	}

}
