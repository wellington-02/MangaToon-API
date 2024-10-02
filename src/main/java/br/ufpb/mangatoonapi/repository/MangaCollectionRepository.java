package br.ufpb.mangatoonapi.repository;

import br.ufpb.mangatoonapi.model.MangaCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MangaCollectionRepository extends JpaRepository<MangaCollection, Long> {
}
