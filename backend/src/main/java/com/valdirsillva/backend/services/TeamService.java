package com.valdirsillva.backend.services;

import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.valdirsillva.backend.models.Team;
import com.valdirsillva.backend.repositories.TeamRepository;
import com.valdirsillva.backend.dtos.TeamRequestDTO;

@Service
@RequiredArgsConstructor
public class TeamService {
    private TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAll() {
        return this.teamRepository.findAll();
    }

    public Team add(TeamRequestDTO dto) {
        Team team = new Team();
        team.setName(dto.name());
        return this.teamRepository.save(team);
    }
}