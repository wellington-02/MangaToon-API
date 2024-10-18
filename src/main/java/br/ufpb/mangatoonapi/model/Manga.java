package br.ufpb.mangatoonapi.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;

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

    @ManyToMany(mappedBy = "favoriteMangas")
    private Collection<User> usersWhoFavorited = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "mangaCollections_manga",
            joinColumns = @JoinColumn(name = "manga_id"),
            inverseJoinColumns = @JoinColumn(name = "mangaCollection_id"))
    private Collection<MangaCollection> mangaCollections = new HashSet<>();

    public Manga() {
    }

    public Manga(Long id, String name, String description, Float rating, String urlImage, Collection<Chapter> chapters, Collection<User> usersWhoFavorited, Collection<MangaCollection> mangaCollections) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.urlImage = urlImage;
        this.chapters = chapters;
        this.usersWhoFavorited = usersWhoFavorited;
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

    public Collection<User> getUsersWhoFavorited() {
        return usersWhoFavorited;
    }

    public void setUsersWhoFavorited(Collection<User> usersWhoFavorited) {
        this.usersWhoFavorited = usersWhoFavorited;
    }

    public Collection<MangaCollection> getMangaCollections() {
        return mangaCollections;
    }

    public void setMangaCollections(Collection<MangaCollection> mangaCollections) {
        this.mangaCollections = mangaCollections;
    }
}