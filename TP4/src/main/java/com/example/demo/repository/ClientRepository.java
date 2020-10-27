package com.example.demo.repository;

import com.example.demo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
//SELECT
	@Query("SELECT t FROM Client t where t.surname = :surname")
	public List<Client> findAllBySurname(String surname);

	@Query("SELECT t FROM Client t where t.name = :name")
	public List<Client> findAllByName(String name);
	
	@Query("SELECT t FROM Client t where t.idClient= :id")
	public Client getById(int id);
	
	

}
