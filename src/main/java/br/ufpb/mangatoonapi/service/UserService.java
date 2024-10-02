package br.ufpb.mangatoonapi.service;

import br.ufpb.mangatoonapi.repository.MangaCollectionRepository;
import br.ufpb.mangatoonapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final MangaCollectionRepository mangaCollectionRepository;

    public UserService(UserRepository userRepository, MangaCollectionRepository mangaCollectionRepository) {
        this.userRepository = userRepository;
        this.mangaCollectionRepository = mangaCollectionRepository;

    }
}
