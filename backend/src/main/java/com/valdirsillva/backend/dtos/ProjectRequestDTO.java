package com.valdirsillva.backend.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.valdirsillva.backend.models.Activity;

public record ProjectRequestDTO(
        UUID id,
        String name,
        String description,
        String status,
        LocalDateTime dataInitial,
        LocalDateTime dataEnd,
        UUID clientId,
        UUID teamId,
        List<Activity> activities

) {
}
