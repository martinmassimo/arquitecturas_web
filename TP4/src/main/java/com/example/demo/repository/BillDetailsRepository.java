package com.example.demo.repository;


import java.sql.Date;

import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Bill;
import com.example.demo.model.BillDetails;
import com.example.demo.model.Product;

public interface BillDetailsRepository  extends JpaRepository<BillDetails, Integer>{

//	No pudimos hacer que ande el date, ya que lo trae en formato diferente
	@Query("SELECT bd FROM BillDetails bd WHERE  bd.bill.date= :date AND bd.bill.client.idClient = :idClient ")
	public Iterable<BillDetails>  getTotalByDateIdClient(Date date, Integer idClient);
	
	@Query("SELECT bd FROM BillDetails bd WHERE bd.idBillDetails= :id")
	public BillDetails getById(int id);
//	ventas por dia
	@Query("SELECT bd.bill.date, SUM(bd.product.price*bd.cantidad) FROM BillDetails bd GROUP BY bd.bill.date")
	public Iterable<Object> getSales();
//	producto mas vendido (mayor cantidad)
@Query("SELECT bd.product, SUM(bd.cantidad) as total FROM BillDetails bd GROUP BY bd.product.idProduct ORDER BY total DESC")
	public Iterable<Object> getBestSeller();
//
@Query("SELECT bd.bill.client.idClient,bd.bill.client.dni,bd.bill.client.name, bd.bill.client.surname,SUM(bd.product.price*bd.cantidad) as total FROM BillDetails bd GROUP BY bd.bill.client.idClient ")
public Iterable<Object> getComprasClientMount();
	
}
