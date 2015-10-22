package com.bigriver.samples.service;

import java.util.Collection;

import com.bigriver.samples.dao.CarroDAO;
import com.bigriver.samples.model.Carro;
import com.bigriver.samples.model.Pessoa;

/**
 * Classe de Exemplo para a criação de vendas de produtos
 * @author Rodney
 *
 */
public class VendaCarro implements ServicoVenda<Carro> {
	
	//Um atributo primário para busca de pessoas
	private CarroDAO dao;

	public VendaCarro() {
		this.dao = new CarroDAO();
	}
	
	@Override
	public Collection<Carro> todosNaoVendidos() {
		//Consultar todas as pessoas do banco de dados.
		//Alterar para uma busca de produtos/itens não vendidos.
		return dao.todos();
	}

	@Override
	public void vender(Carro produto, Pessoa cliente) {
		//Implementar uma lógica para a venda de um produto
		System.out.format("Vendeu a pessoa %s, para %s", produto, cliente);
	}

}
