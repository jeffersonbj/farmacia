package br.com.farmacia.util;

import java.util.Map;

import javax.faces.context.FacesContext;

public class JSFUtil {

	/**
	 * M�todo que l� um par�metro de um link ou bot�o passado atrav�s da TAG
	 * <code>&lt;f:param&gt;</code>.
	 * 
	 * @param nomeDoParametro
	 *            valor usado no atributo name da TAG "param"
	 * @return o valor passado no envio da requisi��o como uma String
	 */
	public static String getParametro(String nomeDoParametro) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		String valor = map.get(nomeDoParametro);

		return valor;
	}

	/**
	 * M�todo que l� um par�metro de um link ou bot�o passado atrav�s da TAG
	 * <code>&lt;f:param&gt;</code>, convertendo o valor para Long.
	 * 
	 * @param nomeDoParametro
	 *            valor usado no atributo name da TAG "param"
	 * @return o valor passado no envio da requisi��o como um Long
	 */
	public static Long getParametroLong(String nomeDoParametro) {
		String valor = getParametro(nomeDoParametro);
		System.out.println("getParametroLong VALOR: " + valor);
		Long valorLong;
		try {
			valorLong = new Long(valor);
		} catch (Exception e) {
			valorLong = null;
		}

		return valorLong;
	}

	/**
	 * M�todo que l� um par�metro de um link ou bot�o passado atrav�s da TAG
	 * <code>&lt;f:param&gt;</code>, convertendo o valor para Integer.
	 * 
	 * @param nomeDoParametro
	 *            valor usado no atributo name da TAG "param"
	 * @return o valor passado no envio da requisi��o como um Integer
	 */
	public static Integer getParametroInteger(String nomeDoParametro) {
		String valor = getParametro(nomeDoParametro);
		Integer valorInt;
		try {
			valorInt = new Integer(valor);
		} catch (Exception e) {
			valorInt = null;
		}

		return valorInt;
	}
}
