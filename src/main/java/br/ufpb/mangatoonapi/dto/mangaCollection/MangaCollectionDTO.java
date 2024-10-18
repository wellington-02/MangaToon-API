package br.ufpb.mangatoonapi.dto.mangaCollection;

import java.util.Collection;

public record MangaCollectionDTO(Long id, String name, Collection<Long> mangaIds, Collection<Long> userIds){
}
