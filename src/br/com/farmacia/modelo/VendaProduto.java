package br.com.farmacia.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vendaProduto")
public class VendaProduto implements Serializable {

	private Venda venda;
	private Produto produto;
	private Integer quantidade;
	private Float preco;

	public VendaProduto() {
	}

	public VendaProduto(Venda venda, Produto produto, Integer quantidade, Float preco) {
		super();
		this.venda = venda;
		this.produto = produto;
		this.quantidade = quantidade;
		this.preco= preco;
	}

	@Id
	@ManyToOne
	@JoinColumn(name = "vendaId")
	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	@Id
	@ManyToOne
	@JoinColumn(name = "produtoId")
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}
	
	

}
