package br.ufpb.mangatoonapi.repository;

import br.ufpb.mangatoonapi.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MangaRepository extends JpaRepository<Manga, Long> {
}
