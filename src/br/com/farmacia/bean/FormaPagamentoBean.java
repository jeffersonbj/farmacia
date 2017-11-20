package br.com.farmacia.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import br.com.farmacia.dao.DAO;
import br.com.farmacia.modelo.FormaPagamento;
import br.com.farmacia.util.JSFUtil;

@ManagedBean
public class FormaPagamentoBean {

	private FormaPagamento forma = new FormaPagamento();
	private List<FormaPagamento> formas = null;

	public FormaPagamento getForma() {
		return forma;
	}

	public String gravar() {
		System.out.println("Gravando Forma de Pagamento: " + this.forma.getId());

		if (this.forma.getId() > 0) {
			new DAO<FormaPagamento>(FormaPagamento.class).atualiza(this.forma);
		} else {
			new DAO<FormaPagamento>(FormaPagamento.class).adiciona(this.forma);
		}

		this.formas = null;
		this.forma = new FormaPagamento();

		return "listaFormaPagamento";
	}

	public List<FormaPagamento> getFormas() {
		if (this.formas == null) {
			this.formas = new DAO<FormaPagamento>(FormaPagamento.class).listaTodos();
		}
		return this.formas;
	}

	public String acaoAbrirInclusao() {
		this.forma = new FormaPagamento();

		return "formaPagamento?faces-redirect=true";
	}
	
	public String acaoAbrirListagem() {
		this.forma = new FormaPagamento();

		return "listaFormaPagamento?faces-redirect=true";
	}

	public String acaoAbrirAlteracao() {

		int id = JSFUtil.getParametroInteger("itemId");
		this.forma = new DAO<FormaPagamento>(FormaPagamento.class).buscaPorId(id);

		return "formaPagamento";
	}

	public String acaoExcluir() {
		System.out.println("exclusão");

		int id = JSFUtil.getParametroInteger("itemId");

		System.out.println("ID: " + id);
		FormaPagamento objeto = new DAO<FormaPagamento>(FormaPagamento.class).buscaPorId(id);
		new DAO<FormaPagamento>(FormaPagamento.class).remove(objeto);

		this.forma = new FormaPagamento();
		this.formas = null;

		return "listaFormaPagamento";
	}

}