package br.com.farmacia.modelo;

import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Cliente extends Pessoa {

	private Date dataDeNascimento;
	private Integer numResidencia;
	private String pontoDeReferencia;
	private String cep;

	public Cliente(){
		super();
	}
	
	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public Integer getNumResidencia() {
		return numResidencia;
	}

	public void setNumResidencia(Integer numResidencia) {
		this.numResidencia = numResidencia;
	}

	public String getPontoDeReferencia() {
		return pontoDeReferencia;
	}

	public void setPontoDeReferencia(String pontoDeReferencia) {
		this.pontoDeReferencia = pontoDeReferencia;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;

	}
}
