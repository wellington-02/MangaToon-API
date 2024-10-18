package br.ufpb.mangatoonapi.dto.chapter;

import br.ufpb.mangatoonapi.model.Chapter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChapterMapper {

    @Mapping(target = "mangaId", source = "manga.id")
    ChapterDTO toDTO(Chapter chapter);

    @Mapping(target = "manga", ignore = true)
    Chapter toEntity(ChapterDTO chapterDTO);

}
