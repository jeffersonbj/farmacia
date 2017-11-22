package br.com.farmacia.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.farmacia.dao.DAO;
import br.com.farmacia.modelo.Cargo;
import br.com.farmacia.modelo.FormaPagamento;
import br.com.farmacia.modelo.Produto;
import br.com.farmacia.modelo.Venda;
import br.com.farmacia.util.JSFUtil;

@ManagedBean
@RequestScoped
public class VendaBean {

	private Venda venda = new Venda();
	private List<Venda> vendas = null;
	private List<FormaPagamento> formasPagamento = null;
	
	
	public Venda getVenda() {
		return venda;
	}

	public String gravar() {
		System.out.println("Atualizando Vendas: " + this.venda.getId());

		if (this.venda.getId() > 0) {
			new DAO<Venda>(Venda.class).atualiza(this.venda);
		} else {
			new DAO<Venda>(Venda.class).adiciona(this.venda);
		}

		this.vendas = null;
		this.venda = new Venda();

		return "listaVenda?faces-redirect=true";

	}

	public List<Venda> getVendas() {
		if (this.vendas == null) {
			this.vendas = new DAO<Venda>(Venda.class).listaTodos();
		}
		return this.vendas;
	}

	public String convertStringToDate(Date indate) {
		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");

		/*
		 * you can also use DateFormat reference instead of SimpleDateFormat
		 * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
		 */
		try {
			dateString = sdfr.format(indate);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return dateString;
	}

	public String acaoAbrirAlteracao() {

		int id = JSFUtil.getParametroInteger("itemId");
		this.venda = new DAO<Venda>(Venda.class).buscaPorId(id);

		return "venda";
	}

	public String acaoAbrirInclusao() {
		this.venda = new Venda();

		return "venda?faces-redirect=true";
	}
	
	public String acaoAbrirListagem() {
		this.venda = new Venda();

		return "listaVenda?faces-redirect=true";
	}
	
	public String acaoExcluir() {
		System.out.println("exclusão");

		int id = JSFUtil.getParametroInteger("itemId");

		System.out.println("ID: " + id);
		Venda objeto = new DAO<Venda>(Venda.class).buscaPorId(id);
		new DAO<Venda>(Venda.class).remove(objeto);

		this.venda = new Venda();
		this.vendas = null;

		return "listaVenda";
	}

	public List<FormaPagamento> getCargos() {
		if (this.formasPagamento == null) {
			this.formasPagamento = new DAO<FormaPagamento>(FormaPagamento.class).listaTodos();
		}
		return this.formasPagamento;
	}
	
	//Produtos - Filtro
	private List<Produto> produtos;
	private List<Produto> produtosFiltrado;
	private List<Produto> produtosSelecionados;
	
	public List<Produto> getProdutosFiltrado() {
		return produtosFiltrado;
	}

	public void setProdutosFiltrado(List<Produto> produtosFiltrado) {
		this.produtosFiltrado = produtosFiltrado;
	}

	public List<Produto> getProdutos(){
		if(this.produtos == null){
			this.produtos = new DAO<Produto>(Produto.class).listaTodos();
		}
		
		return this.produtos;
	}

	public List<Produto> getProdutosSelecionados() {
		return produtosSelecionados;
	}

	public void setProdutosSelecionados(List<Produto> produtosSelecionados) {
		this.produtosSelecionados = produtosSelecionados;
	}
}
