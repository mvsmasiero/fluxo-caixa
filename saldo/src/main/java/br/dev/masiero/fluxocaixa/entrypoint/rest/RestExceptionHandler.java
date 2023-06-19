package br.dev.masiero.fluxocaixa.entrypoint.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.dev.masiero.fluxocaixa.core.exception.RegistroNaoEncontradoException;
import br.dev.masiero.fluxocaixa.core.exception.ValidacaoException;

@ControllerAdvice
public class RestExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler({ RegistroNaoEncontradoException.class })
	public ResponseEntity<String> registroNaoEncontradoException(Exception ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler({ ValidacaoException.class, BindException.class })
	public ResponseEntity<String> validacaoException(Exception ex) {
		LOGGER.error("Erro na validacao dos dados", ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<String> genericException(Exception ex) {
		LOGGER.error("Erro interno", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno");
	}
}
