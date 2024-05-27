package com.valdirsillva.backend.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record ActivityRequestDTO(
        UUID id,
        String name,
        String status,
        String description,
        LocalDateTime data_initial,
        LocalDateTime data_end) {

}
