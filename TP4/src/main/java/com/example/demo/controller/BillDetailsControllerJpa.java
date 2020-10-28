package com.example.demo.controller;

import java.sql.Date;
import java.util.Iterator;

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

import com.example.demo.model.BillDetails;

import com.example.demo.repository.BillDetailsRepository;

@RestController
@RequestMapping("billDetails")
public class BillDetailsControllerJpa {

	@Qualifier("billDetailsRepository")
	@Autowired
	private final BillDetailsRepository repository;

	public BillDetailsControllerJpa(@Qualifier("billDetailsRepository") BillDetailsRepository repository) {
		this.repository = null;
	}

	@GetMapping("/")
	@CrossOrigin
	public Iterable<BillDetails> getBillsDetails() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	@CrossOrigin
	public BillDetails getBillsDetailsById(@PathVariable Integer id) {
		return repository.getById(id);
	}

	@GetMapping("/sales")
	@CrossOrigin
	public Iterable<Object> getSales() {
		return repository.getSales();
	}

	@GetMapping("/bestSeller")
	@CrossOrigin
	public Object getBestSeller() {
		return repository.getBestSeller().iterator().next();
	}

	@GetMapping("/comprasClients")
	@CrossOrigin
	public Iterable<Object> getComprasClientMount() {
		return repository.getComprasClientMount();
	}

	@PostMapping("/")
	@CrossOrigin
	public ResponseEntity<BillDetails> newBillDetails(@RequestBody BillDetails b) {

//		try {
		if (b.getCantidad() == null || b.getCantidad() > 3) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} else {

			BillDetails b1 = b;
			Date date = b1.getBill().getDate();
			Integer idClient = b1.getBill().getClient().getIdClient();
			Iterable<BillDetails> list = repository.getTotalByDateIdClient(date, idClient);
			System.out.println(list);
			Integer cantPrevia = 0;
			Iterator<BillDetails> it = list.iterator();
			while (it.hasNext()) {
				cantPrevia += it.next().getCantidad();
				System.out.println(cantPrevia);
			}
			if (cantPrevia + b1.getCantidad() > 3) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(repository.save(b), HttpStatus.CREATED);
		}
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	}

	@DeleteMapping("/{id}")
	@CrossOrigin
	public ResponseEntity<String> deleteBillDetails(@PathVariable Integer id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>("El detalle de la factura ha sido eliminada", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("El detalle de la factura no existe o no se pudo eliminar",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/")
	@CrossOrigin
	public ResponseEntity<BillDetails> updateBillDetails(@RequestBody BillDetails b) {
		if (repository.existsById(b.getIdBillDetails())) {
			return new ResponseEntity<>(repository.save(b), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
//	 Obtener por dia y id usuario
//	 @GetMapping("/{date}/{idClient}")
//	    public Iterable<Bill> getBillsDetailsByDateAndIdClient(@PathVariable Date date, @PathVariable Integer idClient) {
//		 
//	        return repository.findAllByDateAndIdClient(date, idClient);
//	 }

}