package br.ufpb.mangatoonapi.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;

@Entity
public class MangaCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "mangaCollections")
    private Collection<Manga> mangas = new HashSet<>();

    @ManyToMany(mappedBy = "mangaCollections")
    private Collection<User> users = new HashSet<>();

    public MangaCollection() {
    }

    public MangaCollection(Long id, String name, Collection<Manga> mangas, Collection<User> users) {
        this.id = id;
        this.name = name;
        this.mangas = mangas;
        this.users = users;
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

    public Collection<Manga> getMangas() {
        return mangas;
    }

    public void setMangas(Collection<Manga> mangas) {
        this.mangas = mangas;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}