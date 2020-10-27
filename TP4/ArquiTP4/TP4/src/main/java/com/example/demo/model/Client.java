package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idClient;
	@Column(nullable = false)
	private Long dni;
	@Column
	private String name;
	@Column
	private String surname;
	@ManyToOne
	private Address address;

	public Client() {
	}

	public Client(Long dni, String name, String surname, Address address) {
		this.dni = dni;
		this.name = name;
		this.surname = surname;
	}

	public Client(Long dni, String name, String surname) {
		super();
		this.dni = dni;
		this.name = name;
		this.surname = surname;
	}

	public int getIdClient() {
		return idClient;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
