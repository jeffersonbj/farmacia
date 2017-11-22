package br.com.farmacia.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.catalina.manager.util.SessionUtils;

import br.com.farmacia.dao.FuncionarioDAO;
import br.com.farmacia.modelo.Funcionario;
import br.com.farmacia.util.SessionUtil;

@ManagedBean
@SessionScoped
public class PrincipalBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public String getNomeUserLogado() {

		if (SessionUtil.getSession() != null) {
			return SessionUtil.getFuncionarioLogado().getNome();
		}
		return "";
	}

}