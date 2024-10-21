package br.ufpb.mangatoonapi.service;

import br.ufpb.mangatoonapi.dto.mangaCollection.MangaCollectionDTO;
import br.ufpb.mangatoonapi.dto.mangaCollection.MangaCollectionMapper;
import br.ufpb.mangatoonapi.exception.ObjectNotFoundException;
import br.ufpb.mangatoonapi.model.MangaCollection;
import br.ufpb.mangatoonapi.repository.MangaCollectionRepository;
import br.ufpb.mangatoonapi.repository.MangaRepository;
import br.ufpb.mangatoonapi.repository.UserRepository;
import br.ufpb.mangatoonapi.util.MangaCollectionCreator;
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
public class MangaCollectionServiceTest {

    @InjectMocks
    private MangaCollectionService mangaCollectionService;

    @Mock
    private MangaCollectionRepository mangaCollectionRepository;

    @Mock
    private MangaRepository mangaRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MangaCollectionMapper mangaCollectionMapper;

    @Test
    void testListMangaCollections_returnsListOfMangaCollections_whenSuccess() {
        when(mangaCollectionRepository.findAll()).thenReturn(List.of(MangaCollectionCreator.defaultMangaCollection()));
        when(mangaCollectionMapper.toDTO(any())).thenReturn(MangaCollectionCreator.defaultMangaCollectionDTO());

        List<MangaCollectionDTO> mangaCollections = mangaCollectionService.listMangaCollections();

        assertNotNull(mangaCollections);
        assertEquals(1, mangaCollections.size());
        assertEquals("Name test", mangaCollections.get(0).name());
    }

    @Test
    void testGetMangaCollection_returnsMangaCollection_whenSuccess() {
        when(mangaCollectionRepository.findById(anyLong())).thenReturn(Optional.of(MangaCollectionCreator.defaultMangaCollection()));
        when(mangaCollectionMapper.toDTO(any())).thenReturn(MangaCollectionCreator.defaultMangaCollectionDTO());

        MangaCollectionDTO returnedMangaCollection = mangaCollectionService.getMangaCollection(1L);

        assertNotNull(returnedMangaCollection);
        assertEquals("Name test", returnedMangaCollection.name());
    }

    @Test
    void testGetMangaCollection_throwsException_whenMangaCollectionNotFound() {
        when(mangaCollectionRepository.findById(anyLong())).thenReturn(Optional.empty());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> mangaCollectionService.getMangaCollection(999L));

        assertEquals("MangaCollection 999 not found!", exception.getMessage());
    }

    @Test
    void testCreateMangaCollection_returnsMangaCollectionDTO_whenSuccess() {
        MangaCollection mangaCollection = MangaCollectionCreator.defaultMangaCollection();
        MangaCollectionDTO mangaCollectionDTO = MangaCollectionCreator.defaultMangaCollectionDTO();

        when(mangaCollectionMapper.toEntity(any())).thenReturn(mangaCollection);
        when(mangaCollectionRepository.save(any())).thenReturn(mangaCollection);
        when(mangaCollectionMapper.toDTO(any())).thenReturn(mangaCollectionDTO);

        MangaCollectionDTO createdMangaCollection = mangaCollectionService.createMangaCollection(mangaCollectionDTO);

        assertNotNull(createdMangaCollection);
        assertEquals(mangaCollectionDTO.name(), createdMangaCollection.name());
    }

    @Test
    void testUpdateMangaCollection_throwsException_whenMangaCollectionNotFound() {
        when(mangaCollectionRepository.findById(anyLong())).thenReturn(Optional.empty());

        MangaCollectionDTO mangaCollectionDTO = MangaCollectionCreator.defaultMangaCollectionDTO();

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> mangaCollectionService.updateMangaCollection(999L, mangaCollectionDTO));

        assertEquals("MangaCollection 999 not found!", exception.getMessage());
    }

    @Test
    void testDeleteMangaCollection_deletesMangaCollectionSuccessfully_whenMangaCollectionExists() {
        MangaCollection mangaCollection = MangaCollectionCreator.defaultMangaCollection();

        when(mangaCollectionRepository.findById(anyLong())).thenReturn(Optional.of(mangaCollection));
        doNothing().when(mangaCollectionRepository).delete(mangaCollection);

        assertDoesNotThrow(() -> mangaCollectionService.deleteMangaCollection(mangaCollection.getId()));
    }

    @Test
    void testDeleteMangaCollection_throwsException_whenMangaCollectionNotFound() {
        when(mangaCollectionRepository.findById(anyLong())).thenReturn(Optional.empty());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> mangaCollectionService.deleteMangaCollection(999L));

        assertEquals("MangaCollection 999 not found!", exception.getMessage());
    }
}