package br.com.farmacia.bean;

import javax.faces.bean.ManagedBean;

import br.com.farmacia.dao.DAO;
import br.com.farmacia.modelo.Pessoa;

@ManagedBean
public class PessoaBean {

	private Pessoa pessoa = new Pessoa();
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void gravar() {
		System.out.println("Gravando pessoa " + this.pessoa.getNome());

		new DAO<Pessoa>(Pessoa.class).adiciona(this.pessoa);
		
		this.pessoa = new Pessoa();
}
}
