package br.ufpb.mangatoonapi.dto.user;

import br.ufpb.mangatoonapi.model.Manga;
import br.ufpb.mangatoonapi.model.MangaCollection;
import br.ufpb.mangatoonapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "favoriteMangasId", source = "favoriteMangas")
    @Mapping(target = "mangaCollectionsId", source = "mangaCollections")
    UserDTO toDTO(User user);

    @Mapping(target = "favoriteMangas", ignore = true)
    @Mapping(target = "mangaCollections", ignore = true)
    User toEntity(UserDTO userDTO);

    UserFullDTO userFullToDTO(User user);

    User userFullToEntity(UserFullDTO userFullDTO);

    default Collection<Long> mapMangasToIds(Collection<Manga> mangas) {
        return mangas.stream().map(Manga::getId).collect(Collectors.toList());
    }

    default Collection<Long> mapCollectionsToIds(Collection<MangaCollection> mangaCollections) {
        return mangaCollections.stream().map(MangaCollection::getId).collect(Collectors.toList());
    }
}


