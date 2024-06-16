package com.valdirsillva.backend.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdirsillva.backend.dtos.ProjectRequestDTO;
import com.valdirsillva.backend.models.Client;
import com.valdirsillva.backend.models.Project;
import com.valdirsillva.backend.models.Team;
import com.valdirsillva.backend.repositories.ClientRepository;
import com.valdirsillva.backend.repositories.ProjectRepository;
import com.valdirsillva.backend.repositories.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private ProjectRepository projectRepository;
    private ClientRepository clientRepository;
    private TeamRepository teamRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ClientRepository clientRepository,
            TeamRepository teamRepository) {
        this.projectRepository = projectRepository;
        this.clientRepository = clientRepository;
        this.teamRepository = teamRepository;
    }

    public List<Project> getAll() {
        return this.projectRepository.findAll();
    }

    public Project add(ProjectRequestDTO dto) {
        Project project = new Project();

        project.setId(dto.id());
        project.setName(dto.name());
        project.setDescription(dto.description());
        project.setStatus(dto.status());

        UUID teamId = dto.teamId();
        Team team = this.teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found with ID: " + teamId));

        UUID clientId = dto.clientId();
        Client client = this.clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with ID: " + clientId));
        project.setTeam(team);
        project.setClient(client);
        return this.projectRepository.save(project);
    }
}
