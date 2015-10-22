package com.bigriver.samples.view;

import java.util.ArrayList;
import java.util.Collection;

import com.bigriver.samples.dao.DAO;
import com.dooapp.fxform.FXForm;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Tela de Cadastro Básica
 * Declaração e Uso:
 * (Criar uma tela de Cadastro de Pessoas)
 * TelaCadastro telaCadastro = new TelaCadastro(pessoa, pessoaDao);
 * @author Rodney
 *
 * @param <T> Entidade / Model / que mapeia a Tabela do Banco de Dados
 */
public class TelaCadastroPessoa<T> extends VBox {
	
	private VBox formulariosBox;
	
	private T objetoPrincipal;
	
	private Collection<Object> objetosSecundarios;
	
	private DAO<T> dao;
	
	private Button buttonCadastro;
	
	private Collection<FXForm<?>> formularios;
	
	private String titulo;
	/**
	 * Cria uma tela com um formulário de cadastro
	 * @param titulo O titulo da tela
	 * @param objetoPrincipal O objeto principal para construção do formulário
	 * @param dao A classe para controle de interações com o BD
	 */
	public TelaCadastroPessoa(String titulo, T objetoPrincipal, DAO<T> dao){
		super();
		this.titulo = titulo;
		
		this.setPadding(new Insets(5));
		this.objetoPrincipal = objetoPrincipal;
		this.dao = dao;
		this.formularios = new ArrayList<>();
		
		montarComponentesTela(titulo);
		
		montarFormularioPrimario();
		
		getStyleClass().add("root");
		getStylesheets().add(getClass().getClassLoader().getResource("style.css").toString());
		
	}
	/**
	 * Cria uma tela com um formulário de cadastro
	 * @param titulo O titulo da tela
	 * @param objetoPrincipal O objeto principal para construção do formulário
	 * @param dao A classe para controle de interações com o BD
	 * @param objetosSecundarios Objetos secundários que irão preencher o formulário, ex: Telefone
	 */
	public TelaCadastroPessoa(String titulo, T objetoPrincipal, DAO<T> dao, Object... objetosSecundarios){
		this(titulo, objetoPrincipal, dao);
		
		this.objetosSecundarios = new ArrayList<>();
		
		for (Object object : objetosSecundarios) {
			this.objetosSecundarios.add(object);
		}
		
		montarFormulariosSecundarios();
		
	}
	
	private void montarComponentesTela(String titulo){
		
		Label labelTitulo = new Label(titulo);
		labelTitulo.getStyleClass().add("LabelTitle");
		
		this.formulariosBox = new VBox(10, labelTitulo);
		
		this.buttonCadastro = new Button("Cadastrar");
		
		this.buttonCadastro.setOnAction(evt -> {
			this.dao.salvar(this.objetoPrincipal);
		});
		
		this.buttonCadastro.getStyleClass().add("ButtonSave");

		this.getChildren().add(formulariosBox);
		this.getChildren().add(buttonCadastro);
		
	}
	
	private void montarFormularioPrimario(){	
		FXForm<T> formularioPrincipal = new FXForm<>(objetoPrincipal);
		formulariosBox.getChildren().add(formularioPrincipal);
		this.formularios.add(formularioPrincipal);
	}
	
	
	private void montarFormulariosSecundarios(){
		
		for (Object objeto : objetosSecundarios) {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			FXForm formularioSecundario = new FXForm(objeto);
			formulariosBox.getChildren().add(formularioSecundario);
			this.formularios.add(formularioSecundario);
		}
		
	}
	
	public String getTitulo() {
		return titulo;
	}
	
}
