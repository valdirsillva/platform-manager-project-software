package com.valdirsillva.backend.repositories;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import jakarta.persistence.EntityManager;

import com.valdirsillva.backend.models.Client;
import com.valdirsillva.backend.models.Project;
import com.valdirsillva.backend.dtos.ProjectRequestDTO;
import com.valdirsillva.backend.repositories.ClientRepository;
import com.valdirsillva.backend.repositories.ProjectRepository;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class ProjectRepositoryTest {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should created Project successfully from DB")
    void findProjectBySucccessCase1() {

    }

    private Project saveProject(ProjectRequestDTO data) {
        Project newProject = new Project();

        newProject.setId(data.id());
        newProject.setName(data.name());
        newProject.setDescription(data.description());
        newProject.setStatus(data.status());

        UUID clientId = data.clientId();
        Client client = this.clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with ID: " + clientId));

        newProject.setClient(client);
        this.entityManager.persist(newProject);
        return newProject;

    }
}
