package com.bigriver.samples.service;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;

import com.bigriver.samples.dao.CarroDAO;
import com.bigriver.samples.dao.VendaDAO;
import com.bigriver.samples.model.Carro;
import com.bigriver.samples.model.Pessoa;
import com.bigriver.samples.model.Venda;

/**
 * Classe de Exemplo para a criação de vendas de produtos
 * @author Rodney
 *
 */
public class VendaCarro implements ServicoVenda<Carro> {
	
	//Um atributo primário para busca de carros
	private CarroDAO daoCarro;
	private VendaDAO daoVenda;

	public VendaCarro() {
		this.daoCarro = new CarroDAO();
		this.daoVenda = new VendaDAO();
	}
	
	@Override
	public Collection<Carro> todosNaoVendidos() {
		//Consultar todas os carros do banco de dados.
		//Alterar para uma busca de produtos/itens não vendidos.
		return daoCarro.todosNaoVendidos();
	}

	@Override
	public void vender(Carro produto, Pessoa cliente) {
		//Implementar uma lógica para a venda de um produto
		Venda venda = new Venda();
		venda.setCarroVend(produto);
		venda.setClient(cliente);
		venda.setDataVenda(Date.from(Instant.now()));
		produto.setVendido(true);
		daoVenda.salvar(venda);
		System.out.format("Vendeu o carro %s, para %s", produto, cliente);
	}

}
