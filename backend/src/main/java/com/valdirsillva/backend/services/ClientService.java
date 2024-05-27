package com.valdirsillva.backend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdirsillva.backend.dtos.ClientRequestDTO;
import com.valdirsillva.backend.models.Client;
import com.valdirsillva.backend.repositories.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return this.clientRepository.findAll();
    }

    public Client create(ClientRequestDTO clientDto) {
        Client client = new Client();
        client.setId(clientDto.id());
        client.setName(clientDto.name());
        client.setPhoneNumber(clientDto.phoneNumber());
        client.setEmail(clientDto.email());
        return this.clientRepository.save(client);
    }
}
