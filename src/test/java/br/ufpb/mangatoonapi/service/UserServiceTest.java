package br.ufpb.mangatoonapi.service;

import br.ufpb.mangatoonapi.dto.user.UserDTO;
import br.ufpb.mangatoonapi.dto.user.UserFullDTO;
import br.ufpb.mangatoonapi.dto.user.UserMapper;
import br.ufpb.mangatoonapi.exception.ObjectNotFoundException;
import br.ufpb.mangatoonapi.model.User;
import br.ufpb.mangatoonapi.repository.MangaCollectionRepository;
import br.ufpb.mangatoonapi.repository.MangaRepository;
import br.ufpb.mangatoonapi.repository.UserRepository;
import br.ufpb.mangatoonapi.util.UserCreator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MangaRepository mangaRepository;

    @Mock
    private MangaCollectionRepository mangaCollectionRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void testListUsers_returnsListOfUsers_whenSuccess(){
        when(userRepository.findAll()).thenReturn(List.of(UserCreator.defaultUser()));
        int expectedSize = 1;

        assertEquals(expectedSize, userService.listUsers().size());
    }

    @Test
    void testGetUser_returnsUser_whenSuccess() {
        when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(UserCreator.defaultUser()));
        User user = UserCreator.defaultUser();
        Long expectedId = user.getId();

        Optional<User> returnedUser = userRepository.findById(expectedId);

        assertTrue(returnedUser.isPresent());
        assertEquals(expectedId, returnedUser.get().getId());
    }

    @Test
    void testGetUser_returnsEmpty_whenNotFound() {
        when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Long nonExistentId = 0L;
        Optional<User> returnedUser = userRepository.findById(nonExistentId);

        assertFalse(returnedUser.isPresent());
    }

    @Test
    void testCreateUser_returnsUserFullDTO_whenSuccess() {
        UserFullDTO userFullDTO = UserCreator.defaultUserFullDTO();
        UserDTO expectedUserDTO = UserCreator.defaultUserDTO();
        User expectedUser = UserCreator.defaultUser();

        when(userRepository.save(ArgumentMatchers.any())).thenReturn(expectedUser);
        when(userMapper.userFullToEntity(ArgumentMatchers.any())).thenReturn(expectedUser);

        when(userMapper.toDTO(ArgumentMatchers.any())).thenReturn(expectedUserDTO);

        UserDTO createdUser = userService.createUser(userFullDTO);

        assertNotNull(createdUser);
        assertEquals(expectedUserDTO.email(), createdUser.email());
    }

    @Test
    void testUpdateUser_updatesUserSuccessfully_whenSuccess() {
        UserDTO userDTO = UserCreator.defaultUserDTO();
        User expectedUser = UserCreator.defaultUser();

        when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(expectedUser));
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(expectedUser);
        when(userMapper.toDTO(ArgumentMatchers.any(User.class))).thenReturn(userDTO);

        UserDTO updatedUser = userService.updateUser(expectedUser.getId(), userDTO);

        assertNotNull(updatedUser);
        assertEquals(userDTO.email(), updatedUser.email());
    }

    @Test
    void testUpdateUser_throwsException_whenUserNotFound() {
        Long userId = 999L;
        UserDTO userDTO = UserCreator.defaultUserDTO();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> userService.updateUser(userId, userDTO));
    }

    @Test
    void testDeleteUser_deletesUserSuccessfully_whenUserExists() {
        User user = UserCreator.defaultUser();

        when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        assertDoesNotThrow(() -> userService.deleteUser(user.getId()));
    }

    @Test
    void testDeleteUser_throwsException_whenUserNotFound() {
        Long nonExistentUserId = 999L;

        when(userRepository.findById(nonExistentUserId)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> userService.deleteUser(nonExistentUserId));
    }
}
