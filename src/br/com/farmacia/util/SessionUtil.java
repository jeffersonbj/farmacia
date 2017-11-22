package br.com.farmacia.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.farmacia.modelo.Funcionario;

public class SessionUtil {

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public static Funcionario getFuncionarioLogado() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return (Funcionario)session.getAttribute("usuarioLogado");
	}

	public static Integer getUserId() {
		HttpSession session = getSession();
		if (session != null){
			Funcionario funcionario = (Funcionario)session.getAttribute("usuarioLogado");
			return funcionario.getId();
		}
		else
			return null;
	}
}
