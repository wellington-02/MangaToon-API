package br.ufpb.mangatoonapi.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String type;

    private String username;

    private String password;

    @ManyToMany
    @JoinTable(name = "user_favorite_mangas",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "manga_id"))
    private Collection<Manga> favoriteMangas;

    @ManyToMany
    @JoinTable(name = "userMangaCollections_manga",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "mangaCollection_id"))
    private Collection<MangaCollection> mangaCollections;

    public User() {
    }

    public User(Long id, String name, String email, String type, String username, String password, Collection<Manga> favoriteMangas, Collection<MangaCollection> mangaCollections) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
        this.username = username;
        this.password = password;
        this.favoriteMangas = favoriteMangas;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Manga> getFavoriteMangas() {
        return favoriteMangas;
    }

    public void setFavoriteMangas(Collection<Manga> favoriteMangas) {
        this.favoriteMangas = favoriteMangas;
    }

    public Collection<MangaCollection> getMangaCollections() {
        return mangaCollections;
    }

    public void setMangaCollections(Collection<MangaCollection> mangaCollections) {
        this.mangaCollections = mangaCollections;
    }
}