package br.dev.masiero.fluxocaixa.core.exception;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import br.dev.masiero.fluxocaixa.core.entity.Lancamento;

public class ValidacaoException extends RuntimeException {

	private static final long serialVersionUID = 1685734849355096343L;
	
	private final List<String> violacoes;

	public ValidacaoException(Set<ConstraintViolation<Lancamento>> violations) {
		super();
		this.violacoes = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
	}

	public List<String> getViolacoes() {
		return violacoes;
	}

}
