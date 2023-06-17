package br.dev.masiero.fluxocaixa.core.exception;

public class RegistroNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = -5121100962633460099L;

	public RegistroNaoEncontradoException() {
		super();
	}

	public RegistroNaoEncontradoException(String message) {
		super(message);
	}

}
