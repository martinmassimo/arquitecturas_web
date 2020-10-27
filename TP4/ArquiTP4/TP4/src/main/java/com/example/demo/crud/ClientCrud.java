package com.example.demo.crud;

import com.example.demo.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientCrud extends CrudRepository<Client, Long> {

	List<Client> findBySurname(String surname);
}
