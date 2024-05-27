package com.valdirsillva.backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valdirsillva.backend.dtos.ActivityRequestDTO;
import com.valdirsillva.backend.models.Activity;
import com.valdirsillva.backend.services.ActivityService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping("/atividades")
    public ResponseEntity<List<Activity>> list() {
        List<Activity> activitiesList = this.activityService.getAll();
        return ResponseEntity.ok().body(activitiesList);
    }

    @PostMapping("/projetos/{projectId}/atividades")
    public ResponseEntity<Object> add(@PathVariable UUID projectId, @RequestBody ActivityRequestDTO activityDto) {
        try {
            var result = this.activityService.create(projectId, activityDto);
            return ResponseEntity.status(201).body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
