package com.bigriver.samples.view;

import com.bigriver.samples.dao.PessoaDAO;
import com.bigriver.samples.model.Pessoa;
import com.bigriver.samples.service.ServicoVenda;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class TelaVendas<T> extends GridPane{
	
	private ServicoVenda<T> vendas;
	
	private PessoaDAO pessoaDAO;
		
	private ListView<T> listViewProduto;
	
	private ListView<Pessoa> listViewPessoas;
	
	/**
	 * Cria uma tela de venda de produtos
	 * @param titulo O titulo para a tela
	 * @param vendas O serviço de vendas de produto
	 */
	public TelaVendas(String titulo, ServicoVenda<T> vendas){
		
		this.setId(titulo);
		
		this.vendas = vendas;
		
		this.setPadding(new Insets(5));
		
		pessoaDAO = new PessoaDAO();
		
		getColumnConstraints().add(new ColumnConstraints());
		getColumnConstraints().add(new ColumnConstraints());
		getColumnConstraints().add(new ColumnConstraints());
		
		getColumnConstraints().get(0).setFillWidth(true);
		getColumnConstraints().get(0).setHgrow(Priority.ALWAYS);
		
		getColumnConstraints().get(1).setMaxWidth(300);
		getColumnConstraints().get(2).setFillWidth(true);
		getColumnConstraints().get(2).setHgrow(Priority.ALWAYS);
		
		getRowConstraints().add(new RowConstraints());
		getRowConstraints().add(new RowConstraints());
		getRowConstraints().add(new RowConstraints());
		
		getRowConstraints().get(0).setPrefHeight(100);
		getRowConstraints().get(1).setVgrow(Priority.ALWAYS);
		getRowConstraints().get(1).setFillHeight(true);
		getRowConstraints().get(2).setPrefHeight(100);
		
		listViewPessoas = new ListView<>();
		listViewProduto = new ListView<>();
		
		fillPane();
		

		getStyleClass().add("root");
		
		getStylesheets().add(getClass().getClassLoader().getResource("style.css").toString());
		
	}
	
	private void fillData(){
		listViewPessoas.setItems(FXCollections.observableArrayList(pessoaDAO.todos()));
		
		if (vendas != null){
			ObservableList<T> naoVendidos = FXCollections
					.observableArrayList(vendas.todosNaoVendidos());
			listViewProduto.setItems(naoVendidos);
		}
	}
	
	private void fillPane(){
		
		Label lbTitulo = new Label(getId());
		lbTitulo.setWrapText(true);
		
		Button buttonMostrar = new Button("Mostrar");
		buttonMostrar.getStyleClass().add("ButtonMostrar");
		
		buttonMostrar.setOnAction(evt -> fillData());
		
		add(lbTitulo, 1, 0);
		add(buttonMostrar, 0, 0);
		
		add(listViewPessoas, 2, 1);
		add(listViewProduto, 0, 1);
		
		add(new Label(getId() + " -> Cliente"), 1, 1);
		
		Button buttonVender = new Button("Vender");
		buttonVender.getStyleClass().add("ButtonVender");
		buttonVender.setOnAction(evt -> {
			
			T produto = listViewProduto.getSelectionModel().getSelectedItem();
			
			if (vendas != null)
				vendas.vender(produto, listViewPessoas.getSelectionModel().getSelectedItem()); 
			
		});
		
		add(buttonVender, 2, 2);
	}
	
}
