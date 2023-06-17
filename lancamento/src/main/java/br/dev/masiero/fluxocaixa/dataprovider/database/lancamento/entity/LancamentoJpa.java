package br.dev.masiero.fluxocaixa.dataprovider.database.lancamento.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Entity(name = "LANCAMENTO")
@Data
@Builder
public class LancamentoJpa {

	@Id
	@GeneratedValue
	@Column(name = "ID", insertable = false, length = 10, unique = true, updatable = false)
	private long id;

	@Column(name = "DESCRICAO", length = 50)	
	private String descricao;

	@Column(name = "DATA")	
	private LocalDate data;

	@Column(name = "VALOR")	
	private BigDecimal valor;
	
}

