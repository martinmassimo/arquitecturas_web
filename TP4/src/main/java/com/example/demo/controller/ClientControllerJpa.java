package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("client")
public class ClientControllerJpa {

	@Qualifier("clientRepository")
	@Autowired
	private final ClientRepository repository;

//defino en repositorio que quiero utilizar
	public ClientControllerJpa(@Qualifier("clientRepository") ClientRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/")
	public Iterable<Client> getClients() {
		return repository.findAll();
	}

	@GetMapping("/BySurname/{surname}")
	public Iterable<Client> getClientsBySurname(@PathVariable String surname) {
		return repository.findAllBySurname(surname);
	}

	@GetMapping("/ByName/{name}")
	public Iterable<Client> getClientsByName(@PathVariable String name) {
		return repository.findAllByName(name);
	}

	@PostMapping("/")
	public Client newClient(@RequestBody Client c) {
		return repository.save(c);
	}

	@GetMapping("/{id}")
	Optional<Client> one(@PathVariable Integer id) {

		return repository.findById(id);
	}

	@PutMapping("/")
	public ResponseEntity<Client> updateClient(@RequestBody Client c) { 
		if (repository.existsById(c.getIdClient())) {
			return new ResponseEntity<>(repository.save(c), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public  ResponseEntity<String> deleteClient(@PathVariable Integer id) { 
		try {
			repository.deleteById(id);
			return new ResponseEntity<>("El cliente ha sido eliminado",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("El cliente no existe o no se pudo eliminar",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
