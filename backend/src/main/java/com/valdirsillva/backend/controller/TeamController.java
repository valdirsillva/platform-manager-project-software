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

import com.valdirsillva.backend.dtos.TeamRequestDTO;
import com.valdirsillva.backend.models.Team;
import com.valdirsillva.backend.services.TeamService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class TeamController {

    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/equipes")
    public ResponseEntity<List<Team>> list() {
        List<Team> teamList = this.teamService.getAll();
        return ResponseEntity.ok().body(teamList);
    }

    @PostMapping("/equipes")
    public ResponseEntity<Object> add(@RequestBody TeamRequestDTO teamDto) {
        try {
            var result = this.teamService.add(teamDto);
            return ResponseEntity.status(201).body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
