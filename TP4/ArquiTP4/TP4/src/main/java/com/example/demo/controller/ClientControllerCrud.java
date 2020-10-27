package com.example.demo.controller;

import com.example.demo.crud.ClientCrud;
import com.example.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public abstract class ClientControllerCrud {

    @Autowired
    private ClientCrud clientCrud;

    @GetMapping("/persons")
    public Iterable<Client> getClient() {
        return clientCrud.findAll();
    }

    @PostMapping("/persons")
    public Client newPerson(@RequestBody Client p) {
        return clientCrud.save(p);
    }

}
