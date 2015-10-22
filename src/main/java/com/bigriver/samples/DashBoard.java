package com.bigriver.samples;

import com.bigriver.samples.dao.CarroDAO;
import com.bigriver.samples.dao.PessoaDAO;
import com.bigriver.samples.model.Carro;
import com.bigriver.samples.model.Endereco;
import com.bigriver.samples.model.Pessoa;
import com.bigriver.samples.service.VendaPessoa;
import com.bigriver.samples.view.TelaCadastroPessoa;
import com.bigriver.samples.view.TelaConsultaPessoa;
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
		
		Carro carro = new Carro();
		CarroDAO cDao = new CarroDAO();
		
		TelaCadastro<Carro> telaCadastroCarro = new TelaCadastro<>();
		
		//Carrega um DAO de Pessoas
		PessoaDAO dao = new PessoaDAO();
		
		//Cria uma tela de cadastro de Pessoas
		TelaCadastroPessoa<Pessoa> cadastro = new TelaCadastroPessoa<>("Cadastro Pessoa", pessoa, dao, endereco);
	
		//Cria um DAO para as Pessoas
		PessoaDAO pessoaDao = new PessoaDAO();
		
		//Cria uma Tela de Consulta de Pessoas
		TelaConsultaPessoa<Pessoa> consulta = new TelaConsultaPessoa<>("Consulta Pessoas", pessoaDao);
		
		//Cria uma Venda Ilegal de Pessoas
		VendaPessoa vendaPessoas = new VendaPessoa();
		
		//Cria uma Tela de Vendas
		TelaVendas<Pessoa> telaVendas = new TelaVendas<>("Venda Ilegal Pessoas", vendaPessoas);
		
		//Cria uma tela de DashBoard com as telas de Consulta e Cadastro de Pessoas
		TelaDashboard telaDashboard = new TelaDashboard(consulta, cadastro, telaVendas);
		
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
