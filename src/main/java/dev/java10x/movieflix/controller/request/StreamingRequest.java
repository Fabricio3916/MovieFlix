package dev.java10x.movieflix.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record StreamingRequest(@NotEmpty(message = "Nome do serviço de streaming é obrigatório")String name) {
}
