package br.com.farmacia.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import br.com.farmacia.modelo.Funcionario;

public class FuncionarioDAO {

	public static Funcionario validate(String login, String senha) {

		try {
			EntityManager em = new JPAUtil().getEntityManager();

			Funcionario resultado;
			Query consulta = em.createQuery("from Funcionario u where u.login = :login and u.senha = :senha");
			consulta.setParameter("login", login);
			consulta.setParameter("senha", senha);
			
			try {
				resultado = (Funcionario) consulta.getSingleResult();
			} catch (NoResultException e) {
				resultado = null;
			}
			
			return resultado;

		} catch (Exception ex) {
			System.out.println("Erro no login -->" + ex.getMessage());
			return null;
		}
	}
}
