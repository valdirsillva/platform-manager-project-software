package com.valdirsillva.backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.valdirsillva.backend.dtos.ActivityRequestDTO;
import com.valdirsillva.backend.models.Activity;
import com.valdirsillva.backend.models.Project;
import com.valdirsillva.backend.repositories.ActivityRepository;
import com.valdirsillva.backend.repositories.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<Activity> getAll() {
        return this.activityRepository.findAll();
    }

    public Activity create(UUID projectId, ActivityRequestDTO activityDto) {
        Project project = this.projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + projectId));

        Activity activity = new Activity();

        LocalDateTime dataInitial = LocalDateTime.now();

        activity.setName(activityDto.name());
        activity.setDescription(activityDto.description());
        activity.setDataInitial(dataInitial);
        activity.setDataEnd(activityDto.data_end());

        activity.setProject(project);

        return this.activityRepository.save(activity);
    }
}
