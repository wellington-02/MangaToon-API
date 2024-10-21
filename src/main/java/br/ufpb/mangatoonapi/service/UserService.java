package br.ufpb.mangatoonapi.service;

import br.ufpb.mangatoonapi.dto.user.UserDTO;
import br.ufpb.mangatoonapi.dto.user.UserFullDTO;
import br.ufpb.mangatoonapi.dto.user.UserMapper;
import br.ufpb.mangatoonapi.exception.ObjectNotFoundException;
import br.ufpb.mangatoonapi.model.Manga;
import br.ufpb.mangatoonapi.model.MangaCollection;
import br.ufpb.mangatoonapi.model.User;
import br.ufpb.mangatoonapi.model.enums.UserType;
import br.ufpb.mangatoonapi.repository.MangaCollectionRepository;
import br.ufpb.mangatoonapi.repository.MangaRepository;
import br.ufpb.mangatoonapi.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final MangaRepository mangaRepository;
    private final MangaCollectionRepository mangaCollectionRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, MangaRepository mangaRepository, MangaCollectionRepository mangaCollectionRepository, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.mangaRepository = mangaRepository;
        this.mangaCollectionRepository = mangaCollectionRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<UserDTO> listUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    public UserDTO getUser(Long userId) {
        return userMapper.toDTO(userRepository.findById(userId).
                orElseThrow(() -> new ObjectNotFoundException("User " + userId + " not found!")));
    }

    public UserDTO createUser(UserFullDTO userFullDTO) {
        User user = userMapper.userFullToEntity(userFullDTO);
        user.setPassword(bCryptPasswordEncoder.encode(userFullDTO.password()));
        User userSaved = userRepository.save(user);

        return userMapper.toDTO(userSaved);
    }

    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User " + userId + " not found!"));

        Collection<Long> mangaIds = userDTO.favoriteMangasId() != null ? userDTO.favoriteMangasId() : new ArrayList<>();
        Collection<Long> mangaCollectionsIds = userDTO.mangaCollectionsId() != null ? userDTO.mangaCollectionsId() : new ArrayList<>();

        List<Manga> favoriteMangas = mangaIds.stream()
                .map(mangaId -> mangaRepository.findById(mangaId)
                        .orElseThrow(() -> new ObjectNotFoundException("Manga " + mangaId + " not found!")))
                .filter(manga -> !user.getFavoriteMangas().contains(manga))
                .toList();
        user.getFavoriteMangas().addAll(favoriteMangas);

        List<MangaCollection> mangaCollections = mangaCollectionsIds.stream()
                .map(mangaCollectionId -> mangaCollectionRepository.findById(mangaCollectionId)
                        .orElseThrow(() -> new ObjectNotFoundException("Manga Collection " + mangaCollectionId + " not found!")))
                .filter(mangaCollection -> !user.getMangaCollections().contains(mangaCollection))
                .toList();
        user.getMangaCollections().addAll(mangaCollections);

        if (userDTO.name() != null) {
            user.setName(userDTO.name());
        }
        if (userDTO.email() != null) {
            user.setEmail(userDTO.email());
        }
        if (userDTO.type() != null) {
            user.setType(UserType.fromId(userDTO.type()));
        }

        return userMapper.toDTO(userRepository.save(user));
    }

    public void deleteUser(Long userId) {
        User u = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User " + userId + " not found!"));

        userRepository.delete(u);

    }
}
