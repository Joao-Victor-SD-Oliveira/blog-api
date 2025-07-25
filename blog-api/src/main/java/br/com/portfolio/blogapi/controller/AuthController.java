package br.com.portfolio.blogapi.controller;

import br.com.portfolio.blogapi.dto.LoginDTO;
import br.com.portfolio.blogapi.dto.RegisterDTO;
import br.com.portfolio.blogapi.dto.TokenDTO;
import br.com.portfolio.blogapi.model.User;
import br.com.portfolio.blogapi.repository.UserRepository;
import br.com.portfolio.blogapi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterDTO data) {
        if (userRepository.findByEmail(data.email()).isPresent()) {
            return ResponseEntity.badRequest().build(); // Email já existe
        }
        String encryptedPassword = passwordEncoder.encode(data.password());
        User newUser = User.builder()
                .name(data.name())
                .email(data.email())
                .password(encryptedPassword)
                .build();
        userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}