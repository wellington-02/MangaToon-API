package br.ufpb.mangatoonapi.dto.chapter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ChapterDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private int numberChapter;

    private String description;

    private String urlImage;

    @NotNull
    private Long mangaId;

    public ChapterDTO() {
    }

    public ChapterDTO(Long id, String name, int numberChapter, String description, String urlImage, Long mangaId) {
        this.id = id;
        this.name = name;
        this.numberChapter = numberChapter;
        this.description = description;
        this.urlImage = urlImage;
        this.mangaId = mangaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberChapter() {
        return numberChapter;
    }

    public void setNumberChapter(int numberChapter) {
        this.numberChapter = numberChapter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Long getMangaId() {
        return mangaId;
    }

    public void setMangaId(Long mangaId) {
        this.mangaId = mangaId;
    }
}
