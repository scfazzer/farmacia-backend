package com.farmaciasalud.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.farmaciasalud.dto.request.LoginRequest;
import com.farmaciasalud.dto.response.AuthResponse;
import com.farmaciasalud.repository.UsuarioRepository;
import com.farmaciasalud.service.JwtService;
import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private JwtService jwtService;
    @Autowired private PasswordEncoder passEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        return usuarioRepo.findByUsernameAndActivoTrue(req.getUsername())
            .filter(u -> passEncoder.matches(req.getPassword(), u.getPassword()))
            .map(u -> ResponseEntity.ok((Object) jwtService.buildAuthResponse(u)))
            .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("mensaje", "Credenciales inválidas o usuario inactivo")));
    }
}
