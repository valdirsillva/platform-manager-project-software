package com.valdirsillva.backend.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.valdirsillva.backend.models.Activity;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {

}
