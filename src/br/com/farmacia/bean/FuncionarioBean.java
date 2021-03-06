package br.com.farmacia.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.farmacia.dao.DAO;
import br.com.farmacia.modelo.Cargo;
import br.com.farmacia.modelo.Funcionario;
import br.com.farmacia.util.JSFUtil;

@ManagedBean
@RequestScoped
public class FuncionarioBean {

	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> funcionarios = null;
	private List<Cargo> cargos = null;
	private int cargoId = 0;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setCargoId(int cargoId) {
		this.cargoId = cargoId;
	}

	public int getCargoId() {
		if (this.cargoId < 1 && this.funcionario.getCargo() != null) {
			this.cargoId = this.funcionario.getCargo().getId();
		}

		return this.cargoId;
	}

	public String gravar() {

		if (this.cargoId > 0) {
			this.funcionario.setCargo(new DAO<Cargo>(Cargo.class).buscaPorId(cargoId));
		}

		if (this.funcionario.getId() > 0) {
			new DAO<Funcionario>(Funcionario.class).atualiza(this.funcionario);
		} else {
			new DAO<Funcionario>(Funcionario.class).adiciona(this.funcionario);
		}

		this.funcionarios = null;
		this.funcionario = new Funcionario();

		return "listaFuncionario?faces-redirect=true";

	}

	public List<Funcionario> getFuncionarios() {
		if (this.funcionarios == null) {
			this.funcionarios = new DAO<Funcionario>(Funcionario.class).listaTodos();
		}
		return this.funcionarios;
	}

	public List<Cargo> getCargos() {
		if (this.cargos == null) {
			this.cargos = new DAO<Cargo>(Cargo.class).listaTodos();
		}
		return this.cargos;
	}

	public String acaoAbrirAlteracao() {

		int id = JSFUtil.getParametroInteger("itemId");
		this.funcionario = new DAO<Funcionario>(Funcionario.class).buscaPorId(id);

		return "funcionario";
	}

	public String acaoAbrirInclusao() {
		this.funcionario = new Funcionario();

		return "funcionario?faces-redirect=true";
	}

	public String acaoAbrirListagem() {
		this.funcionario = new Funcionario();

		return "listaFuncionario?faces-redirect=true";
	}

	public String acaoExcluir() {
		System.out.println("exclus�o");

		int id = JSFUtil.getParametroInteger("itemId");

		System.out.println("ID: " + id);
		Funcionario objeto = new DAO<Funcionario>(Funcionario.class).buscaPorId(id);
		new DAO<Funcionario>(Funcionario.class).remove(objeto);

		this.funcionario = new Funcionario();
		this.funcionarios = null;

		return "listaFuncionario";
	}
}
