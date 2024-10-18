package br.ufpb.mangatoonapi.service;

import br.ufpb.mangatoonapi.dto.chapter.ChapterDTO;
import br.ufpb.mangatoonapi.dto.chapter.ChapterMapper;
import br.ufpb.mangatoonapi.exception.ObjectNotFoundException;
import br.ufpb.mangatoonapi.model.Chapter;
import br.ufpb.mangatoonapi.model.Manga;
import br.ufpb.mangatoonapi.repository.ChapterRepository;
import br.ufpb.mangatoonapi.repository.MangaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChapterService {

    private final ChapterRepository chapterRepository;

    private final MangaRepository mangaRepository;

    private final ChapterMapper chapterMapper;

    public ChapterService(ChapterRepository chapterRepository, MangaRepository mangaRepository, ChapterMapper chapterMapper) {
        this.chapterRepository = chapterRepository;
        this.mangaRepository = mangaRepository;
        this.chapterMapper = chapterMapper;
    }

    public List<ChapterDTO> listChapters() {
        List<Chapter> chapter = chapterRepository.findAll();
        return chapter.stream().map(chapterMapper::toDTO).collect(Collectors.toList());
    }

    public ChapterDTO getChapter(Long chapterId) {
        return chapterMapper.toDTO(chapterRepository.findById(chapterId).
                orElseThrow(() -> new ObjectNotFoundException("Chapter " + chapterId + " not found!")));
    }

    public ChapterDTO createChapter(ChapterDTO chapterDTO) {
        Manga manga = mangaRepository.findById(chapterDTO.getMangaId())
                .orElseThrow(() -> new ObjectNotFoundException("Manga " + chapterDTO.getMangaId() + " not found!"));

        Chapter chapter = chapterMapper.toEntity(chapterDTO);
        chapter.setManga(manga);

        Chapter savedChapter = chapterRepository.save(chapter);

        return chapterMapper.toDTO(savedChapter);
    }

    public ChapterDTO updateChapter(Long chapterId, ChapterDTO chapterDTO) {
        Chapter chapter = chapterRepository.findById(chapterId).
                orElseThrow(() -> new ObjectNotFoundException("Chapter " + chapterId + " not found!"));

        Chapter c = chapterMapper.toEntity(chapterDTO);

        chapter.setName(c.getName());
        chapter.setNumberChapter(c.getNumberChapter());
        chapter.setDescription(c.getDescription());
        chapter.setUrlImage(c.getUrlImage());
        return chapterMapper.toDTO(chapterRepository.save(chapter));
    }

    public void deleteChapter(Long chapterId) {
        Chapter c = chapterRepository.findById(chapterId).
                orElseThrow(() -> new ObjectNotFoundException("Chapter " + chapterId + " not found!"));

        chapterRepository.delete(c);
    }
}
