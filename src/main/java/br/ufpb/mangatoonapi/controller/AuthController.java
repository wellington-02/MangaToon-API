package br.ufpb.mangatoonapi.controller;

import br.ufpb.mangatoonapi.dto.auth.LoginRequestDTO;
import br.ufpb.mangatoonapi.dto.auth.TokenResponseDTO;
import br.ufpb.mangatoonapi.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public TokenResponseDTO createAuthenticationToken(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.username(), loginRequestDTO.password())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDTO.username());
        String token = jwtUtil.generateToken(userDetails);
        return new TokenResponseDTO(token);

    }

//    @PostMapping("/change-password")
//    public String changePassword(@RequestBody ChangePasswordRequestDTO changePasswordRequestDTO) {
//        // Implementar lógica para mudança de senha
//        return "Senha alterada com sucesso";
//    }
}