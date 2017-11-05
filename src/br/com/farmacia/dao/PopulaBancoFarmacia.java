package br.com.farmacia.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import br.com.farmacia.modelo.Cliente;

public class PopulaBancoFarmacia {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		Cliente cliente1 = geraCliente();
		em.persist(cliente1);

		em.getTransaction().commit();
		em.close();

	}

	private static Cliente geraCliente() {
		Cliente cliente = new Cliente();
		cliente.setBairro("Bairro");
		cliente.setCep("00000-000");
		cliente.setComplemento("COmplemento");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
		try {
			cliente.setDataDeNascimento(sdf.parse("04/06/1988"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cliente.setEndereco("Endereço");
		cliente.setNome("Cliente 1");
		cliente.setNumResidencia(100);
		cliente.setPontoDeReferencia("Referencia 1");
		cliente.setTelefone("999999999");
		cliente.setTipo("CASA");
		return cliente;
	}

	@SuppressWarnings("unused")
	private static Calendar parseData(String data) {
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
