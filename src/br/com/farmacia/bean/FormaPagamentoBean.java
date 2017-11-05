package br.com.farmacia.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.farmacia.dao.DAO;
import br.com.farmacia.modelo.Cliente;
import br.com.farmacia.modelo.FormaPagamento;

@ManagedBean
@RequestScoped
public class FormaPagamentoBean {

	private FormaPagamento formapagamento = new FormaPagamento();
	private List<FormaPagamento> formas = null;

	public FormaPagamento getFormaPagamento() {
		return formapagamento;
	}

	public String gravar() {
		System.out.println("Gravando nome " + this.formapagamento.getNome());

		new DAO<FormaPagamento>(FormaPagamento.class).adiciona(this.formapagamento);

		this.formapagamento = new FormaPagamento();
		return "listaProduto.xhtml";
	}

	public List<FormaPagamento> getFormas() {
		if (this.formas == null) {
			this.formas = new DAO<FormaPagamento>(FormaPagamento.class).listaTodos();
		}
		return this.formas;
	}

	public String acaoAbrirAlteracao() {
		/*
		 * ; Long id = JSFUtil.getParametroLong("itemId"); Usuario objetoDoBanco
		 * = this.dao.lerPorId(id); this.setUsuario(objetoDoBanco);
		 */
		return "cliente";
	}

	public String acaoExcluir() {
		System.out.println("exclusão");

		return "listaCliente";
	}

}
