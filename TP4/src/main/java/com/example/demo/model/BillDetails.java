package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class BillDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // unica
	private int idBillDetails;
	@ManyToOne
	private Product product;
	@Column
	private Integer cantidad;
	@ManyToOne
	private Bill bill;
	
	public BillDetails(Product product, Integer cantidad, Bill bill) {
		super();
		this.product = product;
		this.cantidad = cantidad;
		this.bill = bill;
	}
	
	public BillDetails(int idBillDetails,Product product, Integer cantidad, Bill bill) {
		super();
		this.idBillDetails = idBillDetails;
		this.product = product;
		this.cantidad = cantidad;
		this.bill = bill;
	}

	public BillDetails() {
		super();
	}

	public int getIdBillDetails() {
		return idBillDetails;
	}

	public void setIdBillDetails(int idBillDetails) {
		this.idBillDetails = idBillDetails;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}
}
