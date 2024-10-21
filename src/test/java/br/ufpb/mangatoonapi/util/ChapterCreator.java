package br.ufpb.mangatoonapi.util;

import br.ufpb.mangatoonapi.dto.chapter.ChapterDTO;
import br.ufpb.mangatoonapi.model.Chapter;
import br.ufpb.mangatoonapi.model.Manga;

public class ChapterCreator {

    private static Long userId = 1L;
    private static final String name = "Name test";
    private static final int numberChapter = 1;
    private static final String description = "Description test";
    private static final String urlImage = "urlImage test";
    private static final Manga Manga = new Manga();

    public static Chapter defaultChapter() {
        return new Chapter(userId, name, numberChapter, description, urlImage, Manga);
    }

    public static ChapterDTO defaultChapterDTO() {
        Long mangaId = 1L;

        return new ChapterDTO(userId, name, numberChapter, description, urlImage, mangaId);
    }

}
