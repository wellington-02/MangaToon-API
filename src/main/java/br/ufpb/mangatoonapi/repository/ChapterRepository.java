package br.ufpb.mangatoonapi.repository;

import br.ufpb.mangatoonapi.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
}
