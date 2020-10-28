package com.example.demo.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Bill {
//	implementar serializable

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // unica
	private int idBill;
	@Column
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd", timezone = "UTC")
//	   @Temporal(TemporalType.DATE )
	private Date date;
	@ManyToOne
	private Client client;
	
	public Integer getIdBill() {
		return idBill;
	}

	public void setIdBill(Integer idBill) {
		this.idBill = idBill;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Bill() {
		super();
	}

	public Bill(Date fecha, Client client) {
		super();
		this.date = fecha;
		this.client = client;
	}
	public Bill(int idBill, Date date, Client client) {
		super();
		this.idBill = idBill;
		this.date = date;
		this.client = client;
	}

}
