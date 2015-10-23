package com.bigriver.samples.service;

import java.util.Collection;

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
public class VendaCarro implements ServicoVenda<Venda> {
	
	//Um atributo primário para busca de carros
	private VendaDAO daoVenda;
	private CarroDAO daoCarro;

	public VendaCarro() {
		this.daoVenda = new VendaDAO();
		this.daoCarro = new CarroDAO();
	}
	
	@Override
	public Collection<Carro> todosNaoVendidos() {
		//Consultar todas os carros do banco de dados.
		//Alterar para uma busca de produtos/itens não vendidos.
		Collection<Carro> var = daoCarro.todos();
		
	}

	@Override
	public void vender(Carro produto, Pessoa cliente) {
		//Implementar uma lógica para a venda de um produto
		Venda venda = new Venda();
		venda.setCarroVend(produto);
		venda.setClient(cliente);
		dao.salvar(venda);
		System.out.format("Vendeu o carro %s, para %s", produto, cliente);
	}

}
