package br.ufpb.mangatoonapi.service;

import br.ufpb.mangatoonapi.dto.user.UserMapper;
import br.ufpb.mangatoonapi.model.User;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    void setUp(){
        when(userRepository.findAll()).thenReturn(List.of(UserCreator.defaultUser()));
        when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(UserCreator.defaultUser()));
        when(userRepository.save(ArgumentMatchers.any())).thenReturn(Optional.of(UserCreator.defaultUser()));
    }


    @Test
    void testListUsers_returnsListOfUsers_whenSuccess(){
        int expectedSize = 1;

        assertEquals(expectedSize, userService.listUsers().size());
    }

    @Test
    void testGetUser_returnsUser_whenSuccess() {
        User user = UserCreator.defaultUser();
        Long expectedId = user.getId();

        Optional<User> returnedUser = userRepository.findById(expectedId);

        assertTrue(returnedUser.isPresent(), "User should be present");
        assertEquals(expectedId, returnedUser.get().getId(), "User ID should match");
    }

    @Test
    void testGetUser_returnsEmpty_whenNotFound() {
        when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Long nonExistentId = 0L;
        Optional<User> returnedUser = userRepository.findById(nonExistentId);

        assertFalse(returnedUser.isPresent(), "User should not be present");
    }

    @Test
    void testCreateUser_returnsUserFullDTO_whenSuccess() {

    }
}
