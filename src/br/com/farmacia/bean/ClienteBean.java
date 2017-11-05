package br.com.farmacia.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.farmacia.dao.DAO;
import br.com.farmacia.modelo.Cliente;

@ManagedBean
@RequestScoped
public class ClienteBean {

	private Cliente cliente = new Cliente();
	private List<Cliente> clientes = null;
	
	public Cliente getCliente() {
		return cliente;
	}

	public String gravar() {
		System.out.println("Gravando cliente " + this.cliente.getNome());
		System.out.println("Gravando cliente - Data Nascimento: " + this.cliente.getDataDeNascimento());

		new DAO<Cliente>(Cliente.class).adiciona(this.cliente);

		this.cliente = new Cliente();
		return "listaCliente.xhtml";

	}

	public List<Cliente> getClientes() {
		if(this.clientes == null){
			this.clientes =  new DAO<Cliente>(Cliente.class).listaTodos();
		}
		return this.clientes;
	}

	public String convertStringToDate(Date indate)
	{
	   String dateString = null;
	   SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
	   
	   /*you can also use DateFormat reference instead of SimpleDateFormat 
	    * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
	    */
	   try{
		dateString = sdfr.format( indate );
	   }catch (Exception ex ){
		System.out.println(ex);
	   }
	   return dateString;
	}
	
	
	public String acaoAbrirAlteracao()
	{
		/*;
		Long id = JSFUtil.getParametroLong("itemId");
		Usuario objetoDoBanco = this.dao.lerPorId(id);
		this.setUsuario(objetoDoBanco);
*/
		return "cliente";
	}
	
	public String acaoExcluir()
	{
		System.out.println("exclus�o");
		
		return "listaCliente";
	}

	/*
	 * 
	 *   <h:form id="formprincipal">
                      
   </h:form>
  <h:form id="dlg">
                        <p:confirmDialog  message="Deseja realmente excluir este registo?" hideEffect="explode" header="Aviso" severity="alert" widgetVar="confirmation">
                            <p:commandButton id="btnSim" value="Sim" oncomplete="confirmation.hide()" action="#{transportadorBean.excluir}" process="@form" update="formprincipal "/>
                            <p:commandButton id="btnNao" value="N�o" onclick="confirmation.hide()" type="button"/>
                        </p:confirmDialog>
   </h:form>
	 * */
}