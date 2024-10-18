package br.ufpb.mangatoonapi.dto.manga;

import java.util.Collection;

public record MangaDTO(Long id, String name, String description, Float rating, String urlImage,
                       Collection<Long> chapterIds, Collection<Long> usersWhoFavoritedIds, Collection<Long> mangaCollectionsIds){
}
