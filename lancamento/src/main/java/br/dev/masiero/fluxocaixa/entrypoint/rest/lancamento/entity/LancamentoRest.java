package br.dev.masiero.fluxocaixa.entrypoint.rest.lancamento.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LancamentoRest {

	private long id;
	private String descricao;
	private LocalDate data;
	private BigDecimal valor;
	
}
