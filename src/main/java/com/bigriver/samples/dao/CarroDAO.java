package com.bigriver.samples.dao;

import java.util.Collection;

import javax.persistence.EntityManager;

import com.bigriver.samples.BancoDeDados;
import com.bigriver.samples.model.Carro;

public class CarroDAO implements DAO<Carro> {

	/**
	 * Salvar uma ocorrência de Pessoa no Banco de Dados
	 * @param objeto A ocorrência que deverá ser salva
	 * @return A pessoa que foi salva
	 */
	@Override
	public Carro salvar(Carro objeto) {
		//Abre um gerente de entidades
		EntityManager gerenteEntidades = BancoDeDados.abreEntityManager();
		//Abre uma transação com o BD
		gerenteEntidades.getTransaction().begin();
		//Guarda o Objeto no BD
		gerenteEntidades.persist(objeto);
		//Conclui a transação com o BD, garantindo a execução da operação
		gerenteEntidades.getTransaction().commit();
		//Fecha o Gerente de Entidades, concluindo o ciclo de vida de operações
		gerenteEntidades.close();
		
		return objeto;
	}
	/**
	 * Buscar uma ocorrência de Carro no Banco de Dados
	 * @param placa O identificador único do Carro que deverá ser buscada
	 * @return O carro encontrado
	 */
	@Override
	public Carro buscar(int id){		
		return null;
	}
	/**
	 * Coletar todas as ocorrências de Pessoas do Banco de Dados
	 * @return Uma coleção com todas as ocorrências de Pessoa
	 */
	@Override
	public Collection<Carro> todos() {
		
		EntityManager gerenteEntidades = BancoDeDados.abreEntityManager();
		gerenteEntidades.getTransaction().begin();
		//Cria uma QUERY que buscará TODOS os Carros no BD
		@SuppressWarnings("unchecked")
		Collection<Carro> todos = gerenteEntidades.createQuery("from Carro")
				.getResultList();
		//Garante a conclusão da operação
		gerenteEntidades.getTransaction().commit();
		gerenteEntidades.close();
		
		return todos;
	}
	
	public Collection<Carro> todosNaoVendidos(){
		EntityManager gerenteEntidades = BancoDeDados.abreEntityManager();
		gerenteEntidades.getTransaction().begin();
		//Cria uma QUERY que buscará TODOS os Carros no BD
		@SuppressWarnings("unchecked")
		Collection<Carro> todos = gerenteEntidades.createQuery("SELECT c from Carro c WHERE c.vendido != TRUE")
				.getResultList();
		//Garante a conclusão da operação
		gerenteEntidades.getTransaction().commit();
		gerenteEntidades.close();
		
		return todos;
	}
}
