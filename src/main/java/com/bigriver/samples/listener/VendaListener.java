package com.bigriver.samples.listener;

import java.time.Instant;
import java.util.Date;

import javax.persistence.PrePersist;

import com.bigriver.samples.model.Venda;

/**
 * Classe que escutará os eventos dos objetos Venda
 * @author Rodney
 *
 */
public class VendaListener {
	
	/**
	 * Antes de Salvar uma Pessoa, garantir que a "timestamps" tenha a data / hora atual.
	 * @param pessoa Pessoa que está sendo salva
	 */
	@PrePersist
	public void salvandoVenda(Venda venda){
		venda.setDataVenda(Date.from(Instant.now()));		
	}
	
}