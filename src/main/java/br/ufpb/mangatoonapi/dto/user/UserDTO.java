package br.ufpb.mangatoonapi.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;

public record UserDTO(Long id,
                      @NotBlank String name,
                      @NotBlank @Email String email,
                      @NotNull Integer type,
                      Collection<Long> favoriteMangasId,
                      Collection<Long> mangaCollectionsId) {
}

