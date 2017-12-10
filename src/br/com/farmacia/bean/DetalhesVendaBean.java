package br.com.farmacia.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.farmacia.dao.VendaProdutoDAO;
import br.com.farmacia.modelo.Venda;
import br.com.farmacia.modelo.VendaProduto;
import br.com.farmacia.util.JSFUtil;

@ManagedBean
@RequestScoped
public class DetalhesVendaBean {

	private Venda venda;
	private List<VendaProduto> vendaProdutos = null;
	private float totalVenda = 0;

	@PostConstruct
	public void init() {
		venda = new Venda();
		// vendaProdutos = new ArrayList<VendaProduto>();
	}

	public float getTotalVenda() {
		return totalVenda;
	}

	public Venda getVenda() {
		return venda;
	}

	public List<VendaProduto> getVendaProdutos() {
		return vendaProdutos;
	}

	public String acaoAbrirDetalhes() {
		int id = JSFUtil.getParametroInteger("itemId");
		System.out.println("ITEM DETALHES: " + id);

		new VendaProdutoDAO();
		this.vendaProdutos = VendaProdutoDAO.buscarPorVenda(id);

		for (VendaProduto vendaProduto : vendaProdutos) {
			System.out.println("PRODUTO : " + vendaProduto.getProduto().getNome());
		}
		
		calculaTotal();

		return "detalhesVenda";
	}

	public void calculaTotal() {
		if (this.vendaProdutos.size() == 0) {
			return;
		}
		
		this.totalVenda = new Float("0.00");
		
		for (VendaProduto item : vendaProdutos) {
			totalVenda += item.getPreco();
		}
	}

	public String acaoAbrirListagem() {
		this.vendaProdutos = new ArrayList<VendaProduto>();

		return "listaVenda?faces-redirect=true";
	}

}
