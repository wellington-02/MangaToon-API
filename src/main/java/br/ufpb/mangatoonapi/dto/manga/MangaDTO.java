package br.ufpb.mangatoonapi.dto.manga;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;

public record MangaDTO(Long id,
                       @NotBlank String name,
                       String description,
                       @NotNull Float rating,
                       String urlImage,
                       Collection<Long> chapterIds,
                       Collection<Long> usersWhoFavoritedIds,
                       Collection<Long> mangaCollectionsIds){
}
