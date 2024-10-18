package br.ufpb.mangatoonapi.dto.mangaCollection;

import br.ufpb.mangatoonapi.model.Manga;
import br.ufpb.mangatoonapi.model.MangaCollection;
import br.ufpb.mangatoonapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MangaCollectionMapper {

    @Mapping(target = "mangaIds", source = "mangas")
    @Mapping(target = "userIds", source = "users")
    MangaCollectionDTO toDTO(MangaCollection mangaCollection);

    @Mapping(target = "mangas", ignore = true)
    @Mapping(target = "users", ignore = true)
    MangaCollection toEntity(MangaCollectionDTO mangaCollectionDTO);

    default Collection<Long> mapMangasToIds(Collection<Manga> mangas) {
        return mangas.stream().map(Manga::getId).collect(Collectors.toList());
    }

    default Collection<Long> mapUsersToIds(Collection<User> users) {
        return users.stream().map(User::getId).collect(Collectors.toList());
    }
}