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

import com.valdirsillva.backend.dtos.ProjectRequestDTO;
import com.valdirsillva.backend.models.Project;
import com.valdirsillva.backend.services.ProjectService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projetos")
    public ResponseEntity<?> findAll() {
        List<Project> projectsList = this.projectService.getAll();

        return ResponseEntity.ok().body(projectsList);
    }

    @PostMapping("/projetos")
    public ResponseEntity<?> create(@RequestBody ProjectRequestDTO dto) {
        try {
            var newProject = this.projectService.add(dto);

            return ResponseEntity.status(201).body(newProject);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
