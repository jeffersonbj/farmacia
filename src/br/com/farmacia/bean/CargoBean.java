package br.com.farmacia.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.farmacia.dao.DAO;
import br.com.farmacia.modelo.Cargo;
import br.com.farmacia.util.JSFUtil;

@ManagedBean
@RequestScoped
public class CargoBean {

	private Cargo cargo = new Cargo();
	private List<Cargo> cargos = null;

	public Cargo getCargo() {
		return cargo;
	}

	public String gravar() {
		System.out.println("Atualizando Cargos: " + this.cargo.getId());

		if (this.cargo.getId() > 0) {
			new DAO<Cargo>(Cargo.class).atualiza(this.cargo);
		} else {
			new DAO<Cargo>(Cargo.class).adiciona(this.cargo);
		}

		this.cargos = null;
		this.cargo = new Cargo();

		return "listaCargo?faces-redirect=true";

	}

	public List<Cargo> getCargos() {
		if (this.cargos == null) {
			this.cargos = new DAO<Cargo>(Cargo.class).listaTodos();
		}
		return this.cargos;
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
		this.cargo = new DAO<Cargo>(Cargo.class).buscaPorId(id);

		return "cargo";
	}

	public String acaoAbrirInclusao() {
		this.cargo = new Cargo();

		return "cargo?faces-redirect=true";
	}
	
	public String acaoAbrirListagem() {
		this.cargo = new Cargo();

		return "listaCargo?faces-redirect=true";
	}
	
	public String acaoExcluir() {
		System.out.println("exclusão");

		int id = JSFUtil.getParametroInteger("itemId");

		System.out.println("ID: " + id);
		Cargo objeto = new DAO<Cargo>(Cargo.class).buscaPorId(id);
		new DAO<Cargo>(Cargo.class).remove(objeto);

		this.cargo = new Cargo();
		this.cargos = null;

		return "listaCargo";
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
