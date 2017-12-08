package br.com.farmacia.modelo;

import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Venda {
	
	@Id
	@GeneratedValue
	private int id;
	private Date dataVenda;
	private Date dataEntrega;
	private Float subtotal;
	
	@OneToMany(mappedBy = "venda")
	private Set<VendaProduto> vendaProduto;
	
	@OneToOne
	@JoinColumn(name = "formaPagamentoId")
	private FormaPagamento formasPagamento;
	
	@OneToOne
	@JoinColumn(name = "clienteId")
	private Cliente cliente;
	
	@OneToOne
	@JoinColumn(name = "funcionarioId")
	private Funcionario funcionario;
	
	/*@ManyToMany
	@JoinTable(name="venda_tem_produtos", joinColumns={@JoinColumn(name="vendaId")}, inverseJoinColumns={@JoinColumn(name="produtoId")})
	private List<Produto> Produtos;*/
	
	public Set<VendaProduto> getVendaProduto(){
		return this.vendaProduto;
	}
	
	public void setVendaProduto(Set<VendaProduto> vendaProduto){
		this.vendaProduto = vendaProduto;
	}
	
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

	
	

}
