package com.valdirsillva.backend.dtos;

import java.util.UUID;

public record ClientRequestDTO(
        UUID id,
        String name,
        String address,
        String phoneNumber,
        String email) {

}
