package br.com.farmacia.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.farmacia.dao.DAO;
import br.com.farmacia.modelo.Cliente;
import br.com.farmacia.modelo.FormaPagamento;
import br.com.farmacia.util.JSFUtil;

@ManagedBean
@RequestScoped
public class ClienteBean {

	private Cliente cliente = new Cliente();
	private List<Cliente> clientes = null;

	public Cliente getCliente() {
		return cliente;
	}

	public String gravar() {
		System.out.println("Atualizando Clientes: " + this.cliente.getId());

		if (this.cliente.getId() > 0) {
			new DAO<Cliente>(Cliente.class).atualiza(this.cliente);
		} else {
			new DAO<Cliente>(Cliente.class).adiciona(this.cliente);
		}

		this.clientes = null;
		this.cliente = new Cliente();

		return "listaCliente?faces-redirect=true";

	}

	public List<Cliente> getClientes() {
		if (this.clientes == null) {
			this.clientes = new DAO<Cliente>(Cliente.class).listaTodos();
		}
		return this.clientes;
	}

	public String convertStringToDate(Date indate) {
		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");

		/*
		 * you can also use DateFormat reference instead of SimpleDateFormat
		 * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
		 */
		try {
			dateString = sdfr.format(indate);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return dateString;
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

	/*
	 * 
	 * <h:form id="formprincipal">
	 * 
	 * </h:form> <h:form id="dlg"> <p:confirmDialog
	 * message="Deseja realmente excluir este registo?" hideEffect="explode"
	 * header="Aviso" severity="alert" widgetVar="confirmation">
	 * <p:commandButton id="btnSim" value="Sim" oncomplete="confirmation.hide()"
	 * action="#{transportadorBean.excluir}" process="@form"
	 * update="formprincipal "/> <p:commandButton id="btnNao" value="Não"
	 * onclick="confirmation.hide()" type="button"/> </p:confirmDialog>
	 * </h:form>
	 */
}
