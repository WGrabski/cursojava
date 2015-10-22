package com.bigriver.samples.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Venda {
	private int idVenda;
	
	private Carro carroVend;
	private Pessoa client;
	private String dataVenda;
	
	@Id
	@GeneratedValue
	public int getIdVenda() {
		return idVenda;
	}

	public  void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

//	@OneToOne (mappedBy = "carroVend")
	public Carro getCarroVend() {
		return carroVend;
	}
	
	public void setCarroVend(Carro carroVend) {
		this.carroVend = carroVend;
	}
	
//	@OneToOne (mappedBy = "client")
	
	public Pessoa getClient() {
		return client;
	}
	
	public void setClient(Pessoa client) {
		this.client = client;
	}
	
	public String getDataVenda() {
		return dataVenda;
	}
	
	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}
	
	public double desconto(double percentual){
		return carroVend.getValor() * (percentual / 100);
	}
	
}
