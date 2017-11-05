package br.com.farmacia.modelo;

import java.sql.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Vendas {

	private int codigoProduto;
	private int quantidade;
	private Date dataVenda;
	
	
	@ManyToOne
	@JoinColumn(name = "clienteId")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "funcionarioId")
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name = "cargoId")
	private Cargo cargo;

	public int getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(int codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	
	
	

}
