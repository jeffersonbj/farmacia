package br.com.farmacia.modelo;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Venda {
	
	@Id
	@GeneratedValue
	private int id;
	private int quantidade;
	private Date dataVenda;
	private Date dataEntrega;
	
	@OneToOne
	@JoinColumn(name = "formaPagamentoId")
	private FormaPagamento formasPagamento;
	
	@OneToOne
	@JoinColumn(name = "clienteId")
	private Cliente cliente;
	
	@OneToOne
	@JoinColumn(name = "funcionarioId")
	private Funcionario funcionario;
	
	@ManyToMany
	@JoinTable(name="venda_tem_produtos", joinColumns={@JoinColumn(name="vendaId")}, inverseJoinColumns={@JoinColumn(name="produtoId")})
	private List<Produto> Produtos;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<Produto> getProdutos() {
		return Produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		Produtos = produtos;
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

	
	

}
