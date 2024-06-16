package com.valdirsillva.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valdirsillva.backend.dtos.ClientRequestDTO;
import com.valdirsillva.backend.models.Client;
import com.valdirsillva.backend.services.ClientService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Client>> list() {
        List<Client> clientsList = this.clientService.getAll();
        return ResponseEntity.ok().body(clientsList);
    }

    @PostMapping("/clientes")
    public ResponseEntity<Object> add(@RequestBody ClientRequestDTO clienDto) {
        try {
            var result = this.clientService.create(clienDto);
            return ResponseEntity.status(201).body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
