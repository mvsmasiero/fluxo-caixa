package br.dev.masiero.fluxocaixa.entrypoint.rest.saldo.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PaginacaoRest<T> {

	private List<T> registros;
	private int paginaAtual;
	private long quantidadeTotalRegistros;
	
}
