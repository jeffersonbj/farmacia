package br.com.farmacia.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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