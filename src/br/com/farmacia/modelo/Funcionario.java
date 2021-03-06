package br.com.farmacia.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import br.com.farmacia.util.StringUtil;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Funcionario {

	@Id
	@GeneratedValue
	private int id;
	private String nome;
	private Integer telefone;
	private String endereco;
	private String bairro;
	private String cidade;
	private String login;
	private String senha;

	@OneToOne
	@JoinColumn(name = "cargoId")
	private Cargo cargo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		new StringUtil();
		senha = StringUtil.getEncryptedPassword(senha);

		this.senha = senha;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public boolean ehGerente() {
		return this.getCargo().getNome().toUpperCase().equals("gerente".toUpperCase());
	}

}
