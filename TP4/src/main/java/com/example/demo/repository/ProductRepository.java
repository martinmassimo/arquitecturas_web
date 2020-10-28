package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query("SELECT p FROM Product p WHERE p.name= :name")
	public List<Product> findAllByName(String name);
	
	@Query("SELECT p FROM Product p WHERE p.idProduct= :id")
	public Product getById(int id);

//	@Query("DELETE FROM Product p WHERE p.idProduct = :id")
//	public Product getById(int id);
}
