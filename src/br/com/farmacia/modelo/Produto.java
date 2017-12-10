package br.com.farmacia.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	private String nome;
	private String codigoDoProduto;
	private String descricaoDoProduto;
	private int quantidade;
	private Date dataDaEntrada;
	private float valorDoProduto;
	
	@Transient
	private int quantidadeVenda;
	
	@Transient
	private float totalPorItem;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoDoProduto() {
		return codigoDoProduto;
	}

	public void setCodigoDoProduto(String codigoDoProduto) {
		this.codigoDoProduto = codigoDoProduto;
	}

	public String getDescricaoDoProduto() {
		return descricaoDoProduto;
	}

	public void setDescricaoDoProduto(String descricaoDoProduto) {
		this.descricaoDoProduto = descricaoDoProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Date getDataDaEntrada() {
		return dataDaEntrada;
	}

	public void setDataDaEntrada(Date dataDaEntrada) {
		this.dataDaEntrada = dataDaEntrada;
	}

	public float getValorDoProduto() {
		return valorDoProduto;
	}

	public void setValorDoProduto(float valorDoProduto) {
		this.valorDoProduto = valorDoProduto;
	}	

	public int getQuantidadeVenda() {
		return quantidadeVenda;
	}

	public void setQuantidadeVenda(int quantidadeVenda) {
		this.quantidadeVenda = quantidadeVenda;
	}

	public float getTotalPorItem() {
		return totalPorItem;
	}

	public void setTotalPorItem(float totalPorItem) {
		this.totalPorItem = totalPorItem;
	}

}
