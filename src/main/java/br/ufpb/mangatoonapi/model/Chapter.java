package br.ufpb.mangatoonapi.model;

import jakarta.persistence.*;

@Entity
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int numberChapter;

    private String description;

    private String urlImage;

    @ManyToOne
    @JoinColumn(name = "manga_id")
    private Manga manga;

    public Chapter() {
    }

    public Chapter(Long id, String name, int numberChapter, String description, String urlImage, Manga manga) {
        this.id = id;
        this.name = name;
        this.numberChapter = numberChapter;
        this.description = description;
        this.urlImage = urlImage;
        this.manga = manga;
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

    public Manga getManga() {
        return manga;
    }

    public void setManga(Manga manga) {
        this.manga = manga;
    }
}
