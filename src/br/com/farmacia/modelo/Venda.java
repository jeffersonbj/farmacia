package br.com.farmacia.modelo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Venda {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false)
	private Date dataVenda;
	
	private Date dataEntrega;
	
	private Float subtotal;

	@Column(nullable = false)
	private float precoTotal;

	@OneToOne
	@JoinColumn(name = "formaPagamentoId", nullable = false)
	private FormaPagamento formasPagamento;

	@ManyToOne
	@JoinColumn(name = "clienteId", nullable = false)
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "funcionarioId", nullable = false)
	private Funcionario funcionario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date date) {
		this.dataVenda = date;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
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

	public FormaPagamento getFormasPagamento() {
		return formasPagamento;
	}

	public void setFormasPagamento(FormaPagamento formasPagamento) {
		this.formasPagamento = formasPagamento;
	}

	public Float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
	}

	public float getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(float precoTotal) {
		this.precoTotal = precoTotal;
	}

}