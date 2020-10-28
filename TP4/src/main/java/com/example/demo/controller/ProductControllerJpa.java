package com.example.demo.controller;

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
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@RestController
@RequestMapping("product")
public class ProductControllerJpa {
	@Qualifier("productRepository")
	@Autowired
	private final ProductRepository repository;

	public ProductControllerJpa(@Qualifier("productRepository") ProductRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/")
	@CrossOrigin
	public Iterable<Product> getProducts() {
		return repository.findAll();
	}
	
	@PostMapping("/")
	@CrossOrigin
	public ResponseEntity<Product> newProduct( Product p) {
		try {
			if(p.getName() == null || p.getDetails() == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>(repository.save(p), HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	@CrossOrigin
	public  ResponseEntity<String> deleteProduct(@PathVariable Integer id) { 
		try {
			repository.deleteById(id);
			return new ResponseEntity<>("El producto ha sido eliminado",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("El producto no existe o no se pudo eliminar",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/")
	@CrossOrigin
	public ResponseEntity<Product> updateProducto(@RequestBody Product p) { 
		if (repository.existsById(p.getIdProduct())) {
			return new ResponseEntity<>(repository.save(p), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}