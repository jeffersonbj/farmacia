package br.com.farmacia.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.farmacia.dao.ClienteDAO;
import br.com.farmacia.modelo.Cliente;

@ViewScoped
@ManagedBean
public class SelecaoClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente clientes;

	private String nome;

	private List<Cliente> clientesFiltrados;

	public void pesquisar() {
		new ClienteDAO();
		clientesFiltrados = ClienteDAO.buscarPorNome(nome);
	}

	public void abrirDialogo() {
		Map<String, Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", true);
		opcoes.put("resizable", false);
		opcoes.put("contentHeight", 470);
			
		RequestContext.getCurrentInstance().openDialog("selecaoCliente", opcoes, null);
	}

	public void selecionar(Cliente cliente) {
		RequestContext.getCurrentInstance().closeDialog(cliente);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

}