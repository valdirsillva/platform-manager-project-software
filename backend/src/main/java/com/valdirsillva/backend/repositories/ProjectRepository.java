package com.valdirsillva.backend.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.valdirsillva.backend.models.Project;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

}
