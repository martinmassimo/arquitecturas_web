package com.example.demo.repository;


import java.sql.Date;

import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Bill;
import com.example.demo.model.BillDetails;
import com.example.demo.model.Product;

public interface BillDetailsRepository  extends JpaRepository<BillDetails, Integer>{

//	@Query("SELECT b FROM Bill b WHERE b.date = :date AND b.client.idClient = :idClient")	
//	public Iterable<Bill> findAllByDateAndIdClient(Date date, Integer idClient);
	
	@Query("SELECT bd FROM Bill b JOIN BillDetails bd ON(b.idBill=bd.bill.idBill) WHERE b.date =:date AND b.client.idClient =:idClient GROUP BY bd.bill.idBill ")
	public Iterable<BillDetails>  getTotalByDateIdClient(Date date, Integer idClient);
	
	@Query("SELECT bd FROM BillDetails bd where bd.idBillDetails=:id")
	public BillDetails getById(int id);
//	ventas por dia
	@Query("SELECT bd.bill.date, SUM(bd.product.price*bd.cantidad) FROM BillDetails bd GROUP BY bd.bill.date")
	public Iterable<Object> getSales();
//	producto mas vendido (mayor cantidad)
@Query("SELECT bd.product, SUM(bd.cantidad) as total FROM BillDetails bd GROUP BY bd.product.idProduct ORDER BY total DESC")
	public Iterable<Object> getBestSeller();
	
}
