package br.ufpb.mangatoonapi.dto.manga;

import br.ufpb.mangatoonapi.model.Chapter;
import br.ufpb.mangatoonapi.model.Manga;
import br.ufpb.mangatoonapi.model.MangaCollection;
import br.ufpb.mangatoonapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MangaMapper {

    @Mapping(target = "chapterIds", source = "chapters")
    @Mapping(target = "usersWhoFavoritedIds", source = "usersWhoFavorited")
    @Mapping(target = "mangaCollectionsIds", source = "mangaCollections")
    MangaDTO toDTO(Manga manga);

    @Mapping(target = "chapters", ignore = true)
    @Mapping(target = "usersWhoFavorited", ignore = true)
    @Mapping(target = "mangaCollections", ignore = true)
    Manga toEntity(MangaDTO mangaDTO);

    default Collection<Long> mapChaptersToIds(Collection<Chapter> chapters) {
        return chapters != null ? chapters.stream().map(Chapter::getId).collect(Collectors.toList()) : new ArrayList<>();
    }

    default Collection<Long> mapUsersToIds(Collection<User> users) {
        return users != null ? users.stream().map(User::getId).collect(Collectors.toList()) : new ArrayList<>();
    }

    default Collection<Long> mapMangaCollectionsToIds(Collection<MangaCollection> mangaCollections) {
        return mangaCollections != null ? mangaCollections.stream().map(MangaCollection::getId).collect(Collectors.toList()) : new ArrayList<>();
    }
}
