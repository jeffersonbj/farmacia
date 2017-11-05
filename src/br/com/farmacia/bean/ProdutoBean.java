package br.com.farmacia.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.farmacia.dao.DAO;
import br.com.farmacia.modelo.Cliente;
import br.com.farmacia.modelo.Produto;

@ManagedBean
public class ProdutoBean {

	private Produto produto = new Produto();

	public Produto getProduto() {
		return produto;
	}

	public String gravar() {
		System.out.println("Gravando Produto " + this.produto.getNome());
		System.out.println("Gravando Produto  Código do Produto " + this.produto.getCodigoDoProduto());
		System.out.println("Gravando Produto  quantidade " + this.produto.getQuantidade());
		System.out.println("Gravando Produto valor do Produto " + this.produto.getValorDoProduto());
		

		new DAO<Produto>(Produto.class).adiciona(this.produto);

		this.produto = new Produto();
		return "listaProduto.xhtml";

	}

	public List<Produto> getProdutos() {
		return new DAO<Produto>(Produto.class).listaTodos();
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

	}
