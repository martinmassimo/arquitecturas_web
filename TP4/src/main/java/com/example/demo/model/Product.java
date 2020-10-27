package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProduct;

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private int price;

	@Column
	private String details;

	@ManyToMany
	@JsonIgnore
	private List<Bill> bills; // facturas
	public Product() {
		super();
	}
	public Product(String name, int price, String details) {
		super();
		this.name = name;
		this.price = price;
		this.details = details;
	}
	public Product(int idProduct,String name, int price, String details) {
		super();
		this.idProduct = idProduct;
		this.name = name;
		this.price = price;
		this.details = details;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public List<Bill> getBills() {
		return bills;
	}
	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

}
