package br.dev.masiero.fluxocaixa.core.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Lancamento {

	private long id;
	
	@NotNull(message = "O campo descrição é obrigatório")
	@Size(max = 50, message = "O campo descrição aceito no máximo 50 caracteres")
	private String descricao;

	@NotNull(message = "O campo data é obrigatório")
	private LocalDate data;

	@NotNull(message = "O campo valor é obrigatório")
	private BigDecimal valor;
	
}
