package br.ufpb.mangatoonapi.util;

import br.ufpb.mangatoonapi.dto.user.UserDTO;
import br.ufpb.mangatoonapi.dto.user.UserFullDTO;
import br.ufpb.mangatoonapi.model.Manga;
import br.ufpb.mangatoonapi.model.MangaCollection;
import br.ufpb.mangatoonapi.model.User;
import br.ufpb.mangatoonapi.model.enums.UserType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class UserCreator {

    private static Long userId = 1L;
    private static final String name = "Name test";
    private static final String email = "test@test.com";
    private static final UserType type = UserType.WRITER;
    private static final String username = "username test";
    private static final String password = "password test";
    private static final Collection<Manga> favoriteMangas = new HashSet<>();
    private static final Collection<MangaCollection> mangaCollections = new HashSet<>();

    public static User defaultUser(){
        return new User(userId, name, email, type, username, password, favoriteMangas, mangaCollections);
    }

    public static UserFullDTO defaultUserFullDTO(){
        int type = 1;
        List<Long> favoriteMangasIds = new ArrayList<>();
        List<Long> mangaCollectionsIds = new ArrayList<>();

        return new UserFullDTO(userId, name, email, type, username, password, favoriteMangasIds, mangaCollectionsIds);
    }

    public static UserDTO defaultUserDTO(){
        int type = 1;
        List<Long> favoriteMangasIds = new ArrayList<>();
        List<Long> mangaCollectionsIds = new ArrayList<>();

        return new UserDTO(userId, name, email, type,favoriteMangasIds, mangaCollectionsIds);
    }
}
