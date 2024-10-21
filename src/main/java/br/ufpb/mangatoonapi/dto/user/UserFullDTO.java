package br.ufpb.mangatoonapi.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Collection;

public record UserFullDTO(Long id,
                      @NotBlank String name,
                      @NotBlank @Email String email,
                      @NotNull Integer type,
                      @NotBlank String username,
                      @Size(min = 8, max = 40) @NotBlank String password,
                      Collection<Long> favoriteMangasId,
                      Collection<Long> mangaCollectionsId) {
}

