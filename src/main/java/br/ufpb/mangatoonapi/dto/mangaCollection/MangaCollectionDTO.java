package br.ufpb.mangatoonapi.dto.mangaCollection;

import jakarta.validation.constraints.NotBlank;

import java.util.Collection;

public record MangaCollectionDTO(Long id,
                                 @NotBlank String name,
                                 Collection<Long> mangaIds,
                                 Collection<Long> userIds){
}
