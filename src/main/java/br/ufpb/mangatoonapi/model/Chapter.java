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

}
