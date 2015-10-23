package com.bigriver.samples.model;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bigriver.samples.listener.VendaListener;

@Entity
@EntityListeners(VendaListener.class)
public class Venda {
	private int idVenda;
	
	private Carro carroVend;
	private Pessoa client;
	private Date dataVenda;
	
	@Id
	@GeneratedValue
	public int getIdVenda() {
		return idVenda;
	}

	public  void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	@OneToOne
	public Carro getCarroVend() {
		return carroVend;
	}
	
	public void setCarroVend(Carro carroVend) {
		this.carroVend = carroVend;
	}
	
	@OneToOne
	public Pessoa getClient() {
		return client;
	}
	
	public void setClient(Pessoa client) {
		this.client = client;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataVenda() {
		return dataVenda;
	}
	
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
	
	public double desconto(double percentual){
		return carroVend.getValor() * (percentual / 100);
	}
	
	@Override
	public String toString() {
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return "Venda " + idVenda + " em " + formatador.format(formatador.format(dataVenda)).toString();
	}
	
}
