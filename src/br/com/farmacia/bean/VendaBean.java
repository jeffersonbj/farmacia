package br.com.farmacia.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.farmacia.dao.DAO;
import br.com.farmacia.modelo.Cliente;
import br.com.farmacia.modelo.FormaPagamento;
import br.com.farmacia.modelo.Produto;
import br.com.farmacia.modelo.Venda;
import br.com.farmacia.modelo.VendaProduto;
import br.com.farmacia.util.JSFUtil;
import br.com.farmacia.util.SessionUtil;

@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Venda venda = new Venda();
	private List<Venda> vendas = null;

	public Venda getVenda() {
		return venda;
	}

	public String gravar() {

		if (!ValidarQuantidadeItens()) {
			return "";
		}

		Set<VendaProduto> produtosVendidos = new HashSet<VendaProduto>();

		System.out.println("Quantidade de produtos: " + this.getProdutosSelecionados().size());

		System.out.println("CLIENTE: " + venda.getCliente().getNome());
		System.out.println("FORMA PAGAMENTO: " + this.formaPagamentoId);

		System.out.println("FORMA PAGAMENTO: " + this.getFormaPagamento().getNome());

		// this.venda.setProdutos(this.produtosSelecionados);
		// this.venda.setCliente(this.clienteSelecionado);
		this.venda.setFormasPagamento(this.formaPagamento);
		this.venda.setFuncionario(SessionUtil.getFuncionarioLogado());

		for (Produto prod : getProdutosSelecionados()) {
			System.out.println("Produto: " + prod.getNome() + " - " + prod.getQuantidadeVenda() + " unidades");

			produtosVendidos.add(new VendaProduto(this.venda, prod, prod.getQuantidadeVenda(), prod.getValorDoProduto()));
		}
		
		float subtotal = 0;
		for (VendaProduto prod : produtosVendidos) {
			subtotal = subtotal + (prod.getQuantidade() * prod.getPreco() );
		}

		this.venda.setSubtotal(subtotal);

		this.venda.setVendaProduto(produtosVendidos);

		if (this.venda.getId() > 0) {
			new DAO<Venda>(Venda.class).atualiza(this.venda);
		} else {
			this.venda.setDataVenda(new Date());

			new DAO<Venda>(Venda.class).adiciona(this.venda);

			System.out.println("VENDA ID: " + this.venda.getId());

			for (VendaProduto vp : produtosVendidos) {
				new DAO<VendaProduto>(VendaProduto.class).adiciona(vp);
			}
		}

		this.vendas = null;
		this.venda = new Venda();

		return "listaVenda?faces-redirect=true";

	}

	public boolean ValidarQuantidadeItens() {
		for (Produto prod : getProdutosSelecionados()) {
			if (prod.getQuantidadeVenda() <= 0) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor, digite quantidade maior que 0 para o(s) produto(s) selecionado(s)."));

				return false;
			}
		}
		return true;

	}

	public List<Venda> getVendas() {
		if (this.vendas == null) {
			this.vendas = new DAO<Venda>(Venda.class).listaTodos();
		}

		return this.vendas;
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

	// Dialogo de Busca
	public void clienteSelecionado(SelectEvent event) {
		Cliente cliente = (Cliente) event.getObject();
		venda.setCliente(cliente);
	}

	@NotBlank
	public String getNomeCliente() {
		return venda.getCliente() == null ? null : venda.getCliente().getNome();
	}

	public void setNomeCliente(String nome) {

	}

	// Forma Pagamento
	private FormaPagamento formaPagamento = new FormaPagamento();
	private List<FormaPagamento> formasPagamento = null;
	private Integer formaPagamentoId = 0;

	public Integer getFormaPagamentoId() {
		return formaPagamentoId;
	}

	public void setFormaPagamentoId(Integer formaPagamentoId) {
		this.formaPagamentoId = formaPagamentoId;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public List<FormaPagamento> getFormasPagamento() {
		if (this.formasPagamento == null) {
			this.formasPagamento = new DAO<FormaPagamento>(FormaPagamento.class).listaTodos();
			this.formasPagamento.add(0, new FormaPagamento(0, "", ""));
		}
		return this.formasPagamento;
	}

	public void onFormaChange() {
		if (formaPagamentoId > 0) {
			// formaPagamento = getFormasPagamento().stream().filter(o ->
			// o.getId() == formaPagamentoId).findFirst().get();
			for (FormaPagamento forma : getFormasPagamento()) {
				if (forma.getId() == formaPagamentoId) {
					formaPagamento = forma;
				}
			}
		}
	}

	// Produtos - Filtro
	private List<Produto> produtos;
	private List<Produto> produtosFiltrado;
	private List<Produto> produtosSelecionados;

	public List<Produto> getProdutosFiltrado() {
		return produtosFiltrado;
	}

	public void setProdutosFiltrado(List<Produto> produtosFiltrado) {
		this.produtosFiltrado = produtosFiltrado;
	}

	public List<Produto> getProdutos() {
		if (this.produtos == null) {
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

	// Custom Filter
	public boolean filterByPrice(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		if (value == null) {
			return false;
		}

		return ((Comparable) value).compareTo(Float.valueOf(filterText)) > 0;
	}

	public float onCalculaTotalItem(Produto produto) {
		if (produto.getQuantidadeVenda() > 0 && produto.getValorDoProduto() > 0) {
			System.out.println("total: " + (produto.getQuantidadeVenda() * produto.getValorDoProduto()));

			return produto.getQuantidade() * produto.getValorDoProduto();
		}		
		return 0;
	}
}
