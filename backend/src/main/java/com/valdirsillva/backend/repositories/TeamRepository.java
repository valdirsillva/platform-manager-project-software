package com.valdirsillva.backend.repositories;

import java.util.UUID;

import com.valdirsillva.backend.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {

}
