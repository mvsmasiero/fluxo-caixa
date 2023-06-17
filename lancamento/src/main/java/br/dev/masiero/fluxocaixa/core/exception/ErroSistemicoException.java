package br.dev.masiero.fluxocaixa.core.exception;

public class ErroSistemicoException extends RuntimeException {

	private static final long serialVersionUID = 8185271241146938696L;

	public ErroSistemicoException(String string, Exception e) {
		super(string, e);
	}

}
