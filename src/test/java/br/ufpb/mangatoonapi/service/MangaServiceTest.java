package br.ufpb.mangatoonapi.service;

import br.ufpb.mangatoonapi.dto.manga.MangaDTO;
import br.ufpb.mangatoonapi.dto.manga.MangaMapper;
import br.ufpb.mangatoonapi.exception.ObjectNotFoundException;
import br.ufpb.mangatoonapi.model.Manga;
import br.ufpb.mangatoonapi.repository.MangaCollectionRepository;
import br.ufpb.mangatoonapi.repository.MangaRepository;
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
public class MangaServiceTest {

    @InjectMocks
    private MangaService mangaService;

    @Mock
    private MangaRepository mangaRepository;

    @Mock
    private MangaCollectionRepository mangaCollectionRepository;

    @Mock
    private MangaMapper mangaMapper;

    @Test
    void testListMangas_returnsListOfMangas_whenSuccess() {
        when(mangaRepository.findAll()).thenReturn(List.of(MangaCreator.defaultManga()));
        when(mangaMapper.toDTO(any())).thenReturn(MangaCreator.defaultMangaDTO());

        List<MangaDTO> mangas = mangaService.listMangas();

        assertNotNull(mangas);
        assertEquals(1, mangas.size());
        assertEquals("Name test", mangas.get(0).name());
    }

    @Test
    void testGetManga_returnsManga_whenSuccess() {
        when(mangaRepository.findById(anyLong())).thenReturn(Optional.of(MangaCreator.defaultManga()));
        when(mangaMapper.toDTO(any())).thenReturn(MangaCreator.defaultMangaDTO());

        MangaDTO returnedManga = mangaService.getManga(1L);

        assertNotNull(returnedManga);
        assertEquals("Name test", returnedManga.name());
    }

    @Test
    void testGetManga_throwsException_whenMangaNotFound() {
        when(mangaRepository.findById(anyLong())).thenReturn(Optional.empty());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> mangaService.getManga(999L));

        assertEquals("Manga 999 not found!", exception.getMessage());
    }

    @Test
    void testCreateManga_returnsMangaDTO_whenSuccess() {
        Manga manga = MangaCreator.defaultManga();
        MangaDTO mangaDTO = MangaCreator.defaultMangaDTO();

        when(mangaMapper.toEntity(any())).thenReturn(manga);
        when(mangaRepository.save(any())).thenReturn(manga);
        when(mangaMapper.toDTO(any())).thenReturn(mangaDTO);

        MangaDTO createdManga = mangaService.createManga(mangaDTO);

        assertNotNull(createdManga);
        assertEquals(mangaDTO.name(), createdManga.name());
    }

    @Test
    void testUpdateManga_updatesMangaSuccessfully_whenSuccess() {
        Manga manga = MangaCreator.defaultManga();
        MangaDTO mangaDTO = MangaCreator.defaultMangaDTO();

        when(mangaRepository.findById(anyLong())).thenReturn(Optional.of(manga));
        when(mangaMapper.toEntity(any())).thenReturn(manga);
        when(mangaRepository.save(any(Manga.class))).thenReturn(manga);
        when(mangaMapper.toDTO(any())).thenReturn(mangaDTO);

        MangaDTO updatedManga = mangaService.updateManga(manga.getId(), mangaDTO);

        assertNotNull(updatedManga);
        assertEquals(mangaDTO.name(), updatedManga.name());
    }

    @Test
    void testUpdateManga_throwsException_whenMangaNotFound() {
        when(mangaRepository.findById(anyLong())).thenReturn(Optional.empty());

        MangaDTO mangaDTO = MangaCreator.defaultMangaDTO();

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> mangaService.updateManga(999L, mangaDTO));

        assertEquals("Manga 999 not found!", exception.getMessage());
    }

    @Test
    void testDeleteManga_deletesMangaSuccessfully_whenMangaExists() {
        Manga manga = MangaCreator.defaultManga();

        when(mangaRepository.findById(anyLong())).thenReturn(Optional.of(manga));
        doNothing().when(mangaRepository).delete(manga);

        assertDoesNotThrow(() -> mangaService.deleteManga(manga.getId()));
    }

    @Test
    void testDeleteManga_throwsException_whenMangaNotFound() {
        when(mangaRepository.findById(anyLong())).thenReturn(Optional.empty());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> mangaService.deleteManga(999L));

        assertEquals("Manga 999 not found!", exception.getMessage());
    }
}