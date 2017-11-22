package br.com.farmacia.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

import br.com.farmacia.modelo.Funcionario;

public class Autorizador implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent evento) {

		FacesContext context = evento.getFacesContext();
		String nomePagina = context.getViewRoot().getViewId();
		HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		System.out.println("Página: " + nomePagina);

		if ("/login.xhtml".equals(nomePagina)) {
			return;
		}

		Funcionario usuarioLogado = (Funcionario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		NavigationHandler handler = context.getApplication().getNavigationHandler();
		
		if (usuarioLogado != null) {
			
			if(!usuarioLogado.ehGerente()){
				System.out.println("FUNCIONARIO");
				if(!(origRequest.getRequestURI().indexOf("/public/") >= 0) && !"/principal.xhtml".equals(nomePagina) ){
					System.out.println("SEM AUTORIZAÇÃO!");
					
					handler.handleNavigation(context, null, "/principal?faces-redirect=true");
					context.renderResponse();
				}
			}
			
			return;
		}

		// redirecionamento para login
		
		handler.handleNavigation(context, null, "/login?faces-redirect=true");
		context.renderResponse();
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}