package com.example.webapp.dto.request;

import com.example.webapp.entities.Candidatura;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RespostaCandidaturaRequest {

    @NotNull
    private Candidatura.StatusCandidatura statusCandidatura;
}

