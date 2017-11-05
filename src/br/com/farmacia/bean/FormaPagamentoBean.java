package br.com.farmacia.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.farmacia.dao.DAO;
import br.com.farmacia.modelo.FormaPagamento;

@ManagedBean
public class FormaPagamentoBean {

	private FormaPagamento formapagamento = new FormaPagamento();

	public FormaPagamento getFormaPagamento() {
		return formapagamento;
	}

	public String gravar() {
		System.out.println("Gravando nome " + this.formapagamento.getNome());

		new DAO<FormaPagamento>(FormaPagamento.class).adiciona(this.formapagamento);

		this.formapagamento = new FormaPagamento();
		return "listaProduto.xhtml";
	}

	public List<FormaPagamento> getFormaPagamentos() {
		return new DAO<FormaPagamento>(FormaPagamento.class).listaTodos();
	}

}
