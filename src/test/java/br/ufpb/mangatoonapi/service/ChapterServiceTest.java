package br.ufpb.mangatoonapi.service;

import br.ufpb.mangatoonapi.dto.chapter.ChapterDTO;
import br.ufpb.mangatoonapi.dto.chapter.ChapterMapper;
import br.ufpb.mangatoonapi.exception.ObjectNotFoundException;
import br.ufpb.mangatoonapi.model.Chapter;
import br.ufpb.mangatoonapi.model.Manga;
import br.ufpb.mangatoonapi.repository.ChapterRepository;
import br.ufpb.mangatoonapi.repository.MangaRepository;
import br.ufpb.mangatoonapi.util.ChapterCreator;
import br.ufpb.mangatoonapi.util.MangaCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChapterServiceTest {

    @InjectMocks
    private ChapterService chapterService;

    @Mock
    private ChapterRepository chapterRepository;

    @Mock
    private MangaRepository mangaRepository;

    @Mock
    private ChapterMapper chapterMapper;

    @Test
    void testListChapters_returnsListOfChapters_whenSuccess() {
        when(chapterRepository.findAll()).thenReturn(List.of(ChapterCreator.defaultChapter()));
        when(chapterMapper.toDTO(any())).thenReturn(ChapterCreator.defaultChapterDTO());

        List<ChapterDTO> chapters = chapterService.listChapters();

        assertNotNull(chapters);
        assertEquals(1, chapters.size());
        assertEquals("Name test", chapters.get(0).getName());
    }

    @Test
    void testGetChapter_returnsChapter_whenSuccess() {
        when(chapterRepository.findById(anyLong())).thenReturn(Optional.of(ChapterCreator.defaultChapter()));
        when(chapterMapper.toDTO(any())).thenReturn(ChapterCreator.defaultChapterDTO());

        ChapterDTO returnedChapter = chapterService.getChapter(1L);

        assertNotNull(returnedChapter);
        assertEquals("Name test", returnedChapter.getName());
    }

    @Test
    void testGetChapter_throwsException_whenChapterNotFound() {
        when(chapterRepository.findById(anyLong())).thenReturn(Optional.empty());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> chapterService.getChapter(999L));

        assertEquals("Chapter 999 not found!", exception.getMessage());
    }

    @Test
    void testCreateChapter_returnsChapterDTO_whenSuccess() {
        Manga manga = MangaCreator.defaultManga();
        Chapter chapter = ChapterCreator.defaultChapter();
        ChapterDTO chapterDTO = ChapterCreator.defaultChapterDTO();

        when(mangaRepository.findById(anyLong())).thenReturn(Optional.of(manga));
        when(chapterMapper.toEntity(any())).thenReturn(chapter);
        when(chapterRepository.save(any())).thenReturn(chapter);
        when(chapterMapper.toDTO(any())).thenReturn(chapterDTO);

        ChapterDTO createdChapter = chapterService.createChapter(chapterDTO);

        assertNotNull(createdChapter);
        assertEquals(chapterDTO.getName(), createdChapter.getName());
    }

    @Test
    void testCreateChapter_throwsException_whenMangaNotFound() {
        when(mangaRepository.findById(anyLong())).thenReturn(Optional.empty());

        ChapterDTO chapterDTO = ChapterCreator.defaultChapterDTO();

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> chapterService.createChapter(chapterDTO));

        assertEquals("Manga " + chapterDTO.getMangaId() + " not found!", exception.getMessage());
    }

    @Test
    void testUpdateChapter_updatesChapterSuccessfully_whenSuccess() {
        Chapter chapter = ChapterCreator.defaultChapter();
        ChapterDTO chapterDTO = ChapterCreator.defaultChapterDTO();

        when(chapterRepository.findById(anyLong())).thenReturn(Optional.of(chapter));
        when(chapterMapper.toEntity(any())).thenReturn(chapter);
        when(chapterRepository.save(any(Chapter.class))).thenReturn(chapter);
        when(chapterMapper.toDTO(any())).thenReturn(chapterDTO);

        ChapterDTO updatedChapter = chapterService.updateChapter(chapter.getId(), chapterDTO);

        assertNotNull(updatedChapter);
        assertEquals(chapterDTO.getName(), updatedChapter.getName());
    }

    @Test
    void testUpdateChapter_throwsException_whenChapterNotFound() {
        when(chapterRepository.findById(anyLong())).thenReturn(Optional.empty());

        ChapterDTO chapterDTO = ChapterCreator.defaultChapterDTO();

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> chapterService.updateChapter(999L, chapterDTO));

        assertEquals("Chapter 999 not found!", exception.getMessage());
    }

    @Test
    void testDeleteChapter_deletesChapterSuccessfully_whenChapterExists() {
        Chapter chapter = ChapterCreator.defaultChapter();

        when(chapterRepository.findById(anyLong())).thenReturn(Optional.of(chapter));
        doNothing().when(chapterRepository).delete(chapter);

        assertDoesNotThrow(() -> chapterService.deleteChapter(chapter.getId()));
    }

    @Test
    void testDeleteChapter_throwsException_whenChapterNotFound() {
        when(chapterRepository.findById(anyLong())).thenReturn(Optional.empty());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> chapterService.deleteChapter(999L));

        assertEquals("Chapter 999 not found!", exception.getMessage());
    }
}