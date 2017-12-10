package br.com.farmacia.Exception;

@SuppressWarnings("serial")
public class ProdutoEsgotadoException extends Exception{
	public ProdutoEsgotadoException(){
		
	}
	
	public ProdutoEsgotadoException(String message){
		super(message);
	}
}

