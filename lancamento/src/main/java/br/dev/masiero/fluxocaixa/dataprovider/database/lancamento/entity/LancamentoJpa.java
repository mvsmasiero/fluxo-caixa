package br.dev.masiero.fluxocaixa.dataprovider.database.lancamento.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LANCAMENTO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

