package br.ufpb.mangatoonapi.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Float rating;

    private String urlImage;

    @OneToMany(mappedBy = "manga")
    private Collection<Chapter> chapters;

    @ManyToOne
    @JoinColumn(name = "creator_user_id")
    private User creator;

    @ManyToMany
    @JoinTable(name = "mangaCollections_manga",
            joinColumns = @JoinColumn(name = "manga_id"),
            inverseJoinColumns = @JoinColumn(name = "mangaCollection_id"))
    private Collection<MangaCollection> mangaCollections;

    public Manga() {
    }

    public Manga(Long id, String name, String description, Float rating, String urlImage, Collection<Chapter> chapters, User creator, Collection<MangaCollection> mangaCollections) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.urlImage = urlImage;
        this.chapters = chapters;
        this.creator = creator;
        this.mangaCollections = mangaCollections;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Collection<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(Collection<Chapter> chapters) {
        this.chapters = chapters;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Collection<MangaCollection> getMangaCollections() {
        return mangaCollections;
    }

    public void setMangaCollections(Collection<MangaCollection> mangaCollections) {
        this.mangaCollections = mangaCollections;
    }
}