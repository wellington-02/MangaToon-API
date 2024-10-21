package br.ufpb.mangatoonapi.util;

import br.ufpb.mangatoonapi.dto.manga.MangaDTO;
import br.ufpb.mangatoonapi.model.Chapter;
import br.ufpb.mangatoonapi.model.Manga;
import br.ufpb.mangatoonapi.model.MangaCollection;
import br.ufpb.mangatoonapi.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class MangaCreator {

    private static Long mangaId = 1L;
    private static final String name = "Name test";
    private static final String description = "Description test";
    private static final Float rating = 1.5F;
    private static final String urlImage = "urlImage test";
    private static final Collection<Chapter> chapters = new HashSet<>();
    private static final Collection<User> usersWhoFavorited = new HashSet<>();
    private static final Collection<MangaCollection> mangaCollections = new HashSet<>();

    public static Manga defaultManga() {
        return new Manga(mangaId, name, description, rating, urlImage, chapters, usersWhoFavorited, mangaCollections);
    }

    public static MangaDTO defaultMangaDTO() {
        Collection<Long> chapterIds = new ArrayList<>();
        Collection<Long> usersWhoFavoritedIds = new ArrayList<>();
        Collection<Long> mangaCollectionsIds = new ArrayList<>();

        return new MangaDTO(mangaId, name, description, rating, urlImage, chapterIds, usersWhoFavoritedIds, mangaCollectionsIds);
    }
}
