package br.com.farmacia.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.dao.DAO;
import br.com.farmacia.modelo.Cliente;
import br.com.farmacia.util.JSFUtil;

@ManagedBean
@ViewScoped
public class VendaBean2 implements Serializable{

	private Cliente cliente = new Cliente();
	private List<Cliente> clientes = null;

	public Cliente getCliente() {
		return cliente;
	}

	public void gravar() {
		// System.out.println("Atualizando Clientes: " + this.cliente.getId());

		// this.cliente.setTelefone(this.cliente.getTelefone().replaceAll("\\D+",
		// ""));
		System.out.println("CLIENTE ID: " + this.getClienteId());

		Cliente cliente = this.getClienteSelecionado();
		System.out.println("CLIENTE: " + cliente.getId());

		// return "listaCliente?faces-redirect=true";

	}

	public String acaoAbrirAlteracao() {

		int id = JSFUtil.getParametroInteger("itemId");
		this.cliente = new DAO<Cliente>(Cliente.class).buscaPorId(id);

		return "cliente";
	}

	public String acaoAbrirInclusao() {
		this.cliente = new Cliente();

		return "cliente?faces-redirect=true";
	}

	public String acaoAbrirListagem() {
		this.cliente = new Cliente();

		return "listaCliente?faces-redirect=true";
	}

	public String acaoExcluir() {
		System.out.println("exclusão");

		int id = JSFUtil.getParametroInteger("itemId");

		System.out.println("ID: " + id);
		Cliente objeto = new DAO<Cliente>(Cliente.class).buscaPorId(id);
		new DAO<Cliente>(Cliente.class).remove(objeto);

		this.cliente = new Cliente();
		this.clientes = null;

		return "listaCliente";
	}

	private Cliente clienteSelecionado = new Cliente();
	private Integer clienteId = 0;

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public List<Cliente> getClientes() {
		if (this.clientes == null) {
			this.clientes = new DAO<Cliente>(Cliente.class).listaTodos();
		}
		return this.clientes;
	}

	public List<Cliente> completeCliente(String query) {
		List<Cliente> allClientes = getClientes();
		List<Cliente> filteredClientes = new ArrayList<Cliente>();

		for (int i = 0; i < allClientes.size(); i++) {
			Cliente cliente = allClientes.get(i);
			if (cliente.getNome().toLowerCase().startsWith(query.toLowerCase())
					|| cliente.getTelefone().contains(query)) {
				filteredClientes.add(cliente);
			}
		}

		return filteredClientes;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
}
