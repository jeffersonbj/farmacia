package br.com.farmacia.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Produto {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	private String nome;
	private Integer codigoDoProduto;
	private String descricaoDoProduto;
	private int quantidade;
	private Date dataDaEntrada;
	private float valorDoProduto;

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

	public Integer getCodigoDoProduto() {
		return codigoDoProduto;
	}

	public void setCodigoDoProduto(Integer codigoDoProduto) {
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

}
