package com.valdirsillva.backend.repositories;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import com.valdirsillva.backend.models.Client;
import com.valdirsillva.backend.dtos.ClientRequestDTO;
import jakarta.persistence.EntityManager;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class ClientRepositoryTest {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should created Client successfully from DB")
    void findClientBySucccessCase1() {
        String id = "8f391221-6bd9-4610-b282-998ec0f2823d";
        UUID uuidFromString = UUID.fromString(id);

        ClientRequestDTO data = new ClientRequestDTO(
                uuidFromString, "SpaceX", "22 9999999", "spacex@bol.com");

        var newClient = this.createClient(data);
        Optional<Client> result = this.clientRepository.findById(newClient.getId());
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get Clients from DB when client not exists")
    void findDepartmentBySuccessCase2() {
        String id = "8f391221-6bd9-4610-b282-998ec0f2823d";
        UUID uuidFromString = UUID.fromString(id);
        Optional<Client> result = this.clientRepository.findById(uuidFromString);
        assertThat(result.isEmpty()).isTrue();
    }

    private Client createClient(ClientRequestDTO data) {
        Client newClient = new Client();
        newClient.setName(data.name());
        newClient.setPhoneNumber(data.phoneNumber());
        newClient.setEmail(data.email());
        this.entityManager.persist(newClient);
        return newClient;
    }

}
