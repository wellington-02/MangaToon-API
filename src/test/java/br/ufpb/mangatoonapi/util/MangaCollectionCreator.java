package br.ufpb.mangatoonapi.util;

import br.ufpb.mangatoonapi.dto.mangaCollection.MangaCollectionDTO;
import br.ufpb.mangatoonapi.model.Manga;
import br.ufpb.mangatoonapi.model.MangaCollection;
import br.ufpb.mangatoonapi.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class MangaCollectionCreator {
    private static final Long mangaCollectionId = 1L;
    private static final String name = "Name test";
    private static final Collection<Manga> mangas = new HashSet<>();
    private static final Collection<User> users = new HashSet<>();

    public static MangaCollection defaultMangaCollection() {
        return new MangaCollection(mangaCollectionId, name, mangas, users);
    }

    public static MangaCollectionDTO defaultMangaCollectionDTO() {
        Collection<Long> mangaIds = new ArrayList<>();
        Collection<Long> userIds = new ArrayList<>();

        return new MangaCollectionDTO(mangaCollectionId, name, mangaIds, userIds);
    }
}