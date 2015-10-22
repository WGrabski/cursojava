package com.bigriver.samples;

import com.bigriver.samples.dao.CarroDAO;
import com.bigriver.samples.dao.PessoaDAO;
import com.bigriver.samples.dao.VendaDAO;
import com.bigriver.samples.model.Carro;
import com.bigriver.samples.model.Endereco;
import com.bigriver.samples.model.Pessoa;
import com.bigriver.samples.model.Venda;
import com.bigriver.samples.service.VendaCarro;
import com.bigriver.samples.view.TelaCadastro;
import com.bigriver.samples.view.TelaConsulta;
import com.bigriver.samples.view.TelaDashboard;
import com.bigriver.samples.view.TelaVendas;
import com.guigarage.flatterfx.FlatterFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashBoard extends Application {
	//Titulo da Janela
	static final String TITULO = "Venda de Carros";
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Carrega um objeto Pessoa e Endereço
		Pessoa pessoa = new Pessoa();
		Endereco endereco = new Endereco();
		pessoa.setEndereco(endereco);
		
		//Carrega um DAO de Pessoas
		PessoaDAO pessoaDao = new PessoaDAO();
		
		//Cria uma tela de cadastro de Pessoas
		TelaCadastro<Pessoa> cadastro = new TelaCadastro<>("Cadastro Pessoa", pessoa, pessoaDao, endereco);
		
		//Cria uma Tela de Consulta de Pessoas
		TelaConsulta<Pessoa> consulta = new TelaConsulta<>("Consulta Pessoas", pessoaDao);
		
		//Carrega um objeto Carro
		Carro carro = new Carro();
		
		//Carrega um DAO de Carro
		CarroDAO carroDao = new CarroDAO();
		
		//Cria uma tela de cadastro de Carros
		TelaCadastro<Carro> telaCadastroCarro = new TelaCadastro<>("Cadastro Carros", carro, carroDao);
		
		//Cria uma Tela de Consulta de Pessoas
		TelaConsulta<Carro> consultaCarro = new TelaConsulta<>("Consulta Carros", carroDao);
		
		//Cria uma Venda Ilegal de Pessoas
		VendaCarro vendaCarro = new VendaCarro();
		
		//Cria uma Tela de Vendas
		TelaVendas<Carro> telaVendas = new TelaVendas<>("Venda de Carros", vendaCarro);
		
		//Cria uma tela de DashBoard com as telas de Consulta e Cadastro de Pessoas
		TelaDashboard telaDashboard = new TelaDashboard(consulta, cadastro, consultaCarro, telaCadastroCarro, telaVendas);
		
		//Cria uma Scene (JavaFX) com a tela de consulta
		Scene scene = new Scene(telaDashboard);
				
		//Cria uma Janela de Consulta
		primaryStage.setScene(scene);
		
		//Titulo da Aplicação
		primaryStage.setTitle(TITULO);
				
		//Abre a Janela
		primaryStage.show();
		
		//Quando fechar a aplicação, garante que
		primaryStage.setOnCloseRequest(evt -> {
			//Fecha todas as conexões com a base de dados
			BancoDeDados.closeFactory();
		});
		
		
		//Tema Especial para a Dashboard
		FlatterFX.style();
	}

}
