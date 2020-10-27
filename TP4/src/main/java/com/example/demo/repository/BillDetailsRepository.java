package com.example.demo.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.BillDetails;

public interface BillDetailsRepository  extends JpaRepository<BillDetails, Integer>{

//	@Query("SELECT b FROM Bill b WHERE b.date = :date AND b.client.idClient = :idClient")	
//	public Iterable<Bill> findAllByDateAndIdClient(Date date, Integer idClient);
}
