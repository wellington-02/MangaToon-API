package br.ufpb.mangatoonapi.service;

import br.ufpb.mangatoonapi.dto.manga.MangaDTO;
import br.ufpb.mangatoonapi.dto.manga.MangaMapper;
import br.ufpb.mangatoonapi.exception.ObjectNotFoundException;
import br.ufpb.mangatoonapi.model.Manga;
import br.ufpb.mangatoonapi.repository.MangaCollectionRepository;
import br.ufpb.mangatoonapi.repository.MangaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MangaService {

    private final MangaRepository mangaRepository;

    private final MangaCollectionRepository mangaCollectionRepository;

    private final MangaMapper mangaMapper;

    public MangaService(MangaRepository mangaRepository, MangaCollectionRepository mangaCollectionRepository, MangaMapper mangaMapper) {
        this.mangaRepository = mangaRepository;
        this.mangaCollectionRepository = mangaCollectionRepository;
        this.mangaMapper = mangaMapper;
    }

    public List<MangaDTO> listMangas() {
        List<Manga> mangas = mangaRepository.findAll();
        return mangas.stream().map(mangaMapper::toDTO).collect(Collectors.toList());
    }

    public MangaDTO getManga(Long mangaId) {
        return mangaMapper.toDTO(mangaRepository.findById(mangaId).
                orElseThrow(() -> new ObjectNotFoundException("Manga " + mangaId + " not found!")));
    }

    public MangaDTO createManga(MangaDTO mangaDTO) {
        Manga manga = mangaRepository.save(mangaMapper.toEntity(mangaDTO));

        return mangaMapper.toDTO(manga);
    }

    public MangaDTO updateManga(Long mangaId, MangaDTO mangaDTO) {
        Manga manga = mangaRepository.findById(mangaId).
                orElseThrow(() -> new ObjectNotFoundException("Manga " + mangaId + " not found!"));

        Manga m = mangaMapper.toEntity(mangaDTO);

        manga.setName(m.getName());
        manga.setDescription(m.getDescription());
        manga.setRating(m.getRating());
        manga.setUrlImage(m.getUrlImage());
        return mangaMapper.toDTO(mangaRepository.save(manga));
    }

    public void deleteManga(Long mangaId) {
        Manga m = mangaRepository.findById(mangaId).
                orElseThrow(() -> new ObjectNotFoundException("Manga " + mangaId + " not found!"));

        mangaRepository.delete(m);
    }
}
