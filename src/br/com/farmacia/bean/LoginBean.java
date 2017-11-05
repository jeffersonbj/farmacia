package br.com.farmacia.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import br.com.farmacia.dao.FuncionarioDAO;
import br.com.farmacia.modelo.Funcionario;
import br.com.farmacia.modelo.Usuario;
import br.com.farmacia.util.SessionUtil;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;

	private String senha;
	private String msg;
	private String login;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	// Validar Login
	public String validarLogin() {
		Funcionario funcionario = FuncionarioDAO.validate(login, senha);
		
		if (funcionario != null) {			
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("usuarioLogado", funcionario);
		
			return "principal?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Usuário ou senha incorretos", "Por favor, digite os dados corretos."));
			return "login";
		}
	}

	// Invalidar Sessão
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();
		return "login?faces-redirect=true";
	}
}