package com.example.demo.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.example.demo.model.Bill;
import com.example.demo.model.BillDetails;
import com.example.demo.model.Client;
import com.example.demo.model.Product;
import com.example.demo.repository.BillDetailsRepository;
import com.example.demo.repository.BillRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.hibernate.annotations.common.util.impl.Log;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

//    private static final String log = null;
//Carga de datos
	@Bean
    CommandLineRunner initProducts(@Qualifier("productRepository") ProductRepository productRepository) {
        return args -> {
        	try {
        		CSVParser productsCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/products.csv"));
        		for(CSVRecord row: productsCSV) {
        			System.out.println("Preloading " + productRepository.save(new Product(Integer.parseInt(row.get("idProduct")),row.get("name"),Integer.parseInt(row.get("price")),row.get("details"))));
        		}
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        };
    }
	@Bean
    CommandLineRunner initClients(@Qualifier("clientRepository") ClientRepository clientRepository) {
        return args -> {
        	try {
        		CSVParser clientsCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/clients.csv"));
        		for(CSVRecord row: clientsCSV) {
        			System.out.println("Preloading " + clientRepository.save(new Client(Integer.parseInt(row.get("idClient")),Long.parseLong(row.get("dni")),row.get("name"),row.get("surname"))));
        		}
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        };
	}
	@Bean
    CommandLineRunner initBills(@Qualifier("billRepository") BillRepository billRepository, @Qualifier("clientRepository") ClientRepository clientRepository) {
        return args -> {
        	try {
        		CSVParser billsCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/bills.csv"));
        		for(CSVRecord row: billsCSV) {
        			Client client = clientRepository.getById(Integer.parseInt(row.get("idClient")));
        			System.out.println("Preloading " + billRepository.save(new Bill(Integer.parseInt(row.get("idBill")),new Date(new SimpleDateFormat("yyyy-mm-dd").parse(row.get("date")).getTime()),client)));
        		}
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        };
	}
	@Bean
    CommandLineRunner initBillDetails(@Qualifier("billDetailsRepository") BillDetailsRepository billDetailsRepository, @Qualifier("billRepository") BillRepository billRepository, @Qualifier("productRepository") ProductRepository productRepository) {
        return args -> {
        	try {
        		CSVParser billDetailsCSV = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/billDetails.csv"));
        		for(CSVRecord row: billDetailsCSV) {
        			Product product = productRepository.getById(Integer.parseInt(row.get("idProduct")));
        			Bill bill = billRepository.getById(Integer.parseInt(row.get("idBill")));
        			System.out.println("Preloading " + billDetailsRepository.save(new BillDetails(Integer.parseInt(row.get("idBillDetails")),product,Integer.parseInt(row.get("cantidad")),bill)));
        		}
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        };
	}
}