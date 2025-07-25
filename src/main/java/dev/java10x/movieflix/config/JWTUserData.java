package dev.java10x.movieflix.config;

import lombok.Builder;


@Builder
public record JWTUserData(Long id, String name, String email) {
}
