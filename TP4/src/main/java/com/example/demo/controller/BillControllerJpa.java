package com.example.demo.controller;


import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Bill;
import com.example.demo.model.BillDetails;
import com.example.demo.model.Client;
import com.example.demo.model.Product;
import com.example.demo.repository.BillRepository;

@RestController
@RequestMapping("bill")
public class BillControllerJpa {

	@Qualifier("billRepository")
	@Autowired
	private final BillRepository repository;
	public BillControllerJpa(@Qualifier("billRepository") BillRepository repository) {
		this.repository = null;
	}
	@GetMapping("/")
	@CrossOrigin
	public Iterable<Bill> getBills() {
		return repository.findAll();
	}
	
	@PostMapping("/")
	@CrossOrigin
	public ResponseEntity<Bill> newBill(@RequestBody Bill b) {
		try {
			//verificar que no tenga mas de 3 
			
			if(b.getClient() == null || b.getDate() == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>(repository.save(b), HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	@CrossOrigin
	public  ResponseEntity<String> deleteBill(@PathVariable Integer id) { 
		try {
			repository.deleteById(id);
			return new ResponseEntity<>("La factura ha sido eliminada",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("La factura no existe o no se pudo eliminar",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/")
	@CrossOrigin
	public ResponseEntity<Bill> updateBill(@RequestBody Bill b) { 
		if (repository.existsById(b.getIdBill())) {
			return new ResponseEntity<>(repository.save(b), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
//	 Obtener por dia y id usuario
	 @GetMapping("/{date}/{idClient}")
	 @CrossOrigin
	    public Iterable<Bill> getBillsByDateAndIdClient(@PathVariable Date date, @PathVariable Integer idClient) {
		 
	        return repository.findAllByDateAndIdClient(date, idClient);
	 }
}