package br.ufpb.mangatoonapi.integration;

import br.ufpb.mangatoonapi.model.User;
import br.ufpb.mangatoonapi.repository.UserRepository;
import br.ufpb.mangatoonapi.util.UserCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testGetUser() throws Exception {
        User userCreated = UserCreator.defaultUser();
        userRepository.save(userCreated);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/1")
                        .with(user("user").roles("USER")) // Adjust roles as necessary
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
