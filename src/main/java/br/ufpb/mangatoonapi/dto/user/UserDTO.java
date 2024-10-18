package br.ufpb.mangatoonapi.dto.user;

import br.ufpb.mangatoonapi.model.enums.UserType;

import java.util.Collection;

public record UserDTO(Long id, String name, String email, Integer type, String username,
                      Collection<Long> favoriteMangasId, Collection<Long> mangaCollectionsId) {
}

