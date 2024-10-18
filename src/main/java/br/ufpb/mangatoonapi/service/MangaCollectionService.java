package br.ufpb.mangatoonapi.service;

import br.ufpb.mangatoonapi.dto.mangaCollection.MangaCollectionDTO;
import br.ufpb.mangatoonapi.dto.mangaCollection.MangaCollectionMapper;
import br.ufpb.mangatoonapi.exception.ObjectNotFoundException;
import br.ufpb.mangatoonapi.model.Manga;
import br.ufpb.mangatoonapi.model.MangaCollection;
import br.ufpb.mangatoonapi.model.User;
import br.ufpb.mangatoonapi.repository.MangaCollectionRepository;
import br.ufpb.mangatoonapi.repository.MangaRepository;
import br.ufpb.mangatoonapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MangaCollectionService {

    private final MangaCollectionRepository mangaCollectionRepository;

    private final MangaRepository mangaRepository;

    private final UserRepository userRepository;

    private final MangaCollectionMapper mangaCollectionMapper;

    public MangaCollectionService(MangaCollectionRepository mangaCollectionRepository, UserRepository userRepository, MangaRepository mangaRepository, MangaCollectionMapper mangaCollectionMapper) {
        this.mangaCollectionRepository = mangaCollectionRepository;
        this.userRepository = userRepository;
        this.mangaRepository = mangaRepository;
        this.mangaCollectionMapper = mangaCollectionMapper;
    }

    public List<MangaCollectionDTO> listMangaCollections() {
        List<MangaCollection> mangaCollections = mangaCollectionRepository.findAll();
        return mangaCollections.stream().map(mangaCollectionMapper::toDTO).collect(Collectors.toList());
    }

    public MangaCollectionDTO getMangaCollection(Long mangaCollectionId) {
        return mangaCollectionMapper.toDTO(mangaCollectionRepository.findById(mangaCollectionId).
                orElseThrow(() -> new ObjectNotFoundException("MangaCollection " + mangaCollectionId + " not found!")));
    }

    public MangaCollectionDTO createMangaCollection(MangaCollectionDTO mangaCollectionDTO) {
        MangaCollection mangaCollection = mangaCollectionRepository.save(mangaCollectionMapper.toEntity(mangaCollectionDTO));

        return mangaCollectionMapper.toDTO(mangaCollection);
    }

    public MangaCollectionDTO updateMangaCollection(Long mangaCollectionId, MangaCollectionDTO mangaCollectionDTO) {
        MangaCollection mangaCollection = mangaCollectionRepository.findById(mangaCollectionId)
                .orElseThrow(() -> new ObjectNotFoundException("MangaCollection " + mangaCollectionId + " not found!"));

        Collection<Long> mangaIds = mangaCollectionDTO.mangaIds() != null ? mangaCollectionDTO.mangaIds() : new ArrayList<>();
        Collection<Long> userIds = mangaCollectionDTO.userIds() != null ? mangaCollectionDTO.userIds() : new ArrayList<>();

        List<Manga> mangas = mangaIds.stream()
                .map(mangaId -> mangaRepository.findById(mangaId)
                        .orElseThrow(() -> new ObjectNotFoundException("Manga " + mangaId + " not found!")))
                .filter(manga -> !mangaCollection.getMangas().contains(manga))
                .toList();
        mangas.forEach(manga -> {
            mangaCollection.getMangas().add(manga);
            manga.getMangaCollections().add(mangaCollection);
        });

        List<User> users = userIds.stream()
                .map(userId -> userRepository.findById(userId)
                        .orElseThrow(() -> new ObjectNotFoundException("User " + userId + " not found!")))
                .filter(user -> !mangaCollection.getUsers().contains(user))
                .toList();
        users.forEach(user -> {
            mangaCollection.getUsers().add(user);
            user.getMangaCollections().add(mangaCollection);
        });

        if (mangaCollectionDTO.name() != null) {
            mangaCollection.setName(mangaCollectionDTO.name());
        }
        return mangaCollectionMapper.toDTO(mangaCollectionRepository.save(mangaCollection));
    }

    public void deleteMangaCollection(Long mangaCollectionId) {
        MangaCollection m = mangaCollectionRepository.findById(mangaCollectionId).
                orElseThrow(() -> new ObjectNotFoundException("MangaCollection " + mangaCollectionId + " not found!"));

        mangaCollectionRepository.delete(m);
    }
}
