package com.farmaciasalud.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;
import com.farmaciasalud.dto.response.AuthResponse;
import com.farmaciasalud.model.Usuario;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "FarmaciaS@ludPlus.API.SecretKey.2025.Segura!!.SuperSecreta";
    private static final long EXPIRATION_MS = 7200000L; // 2 horas

    private SecretKey signingKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public Date fechaEmision() { return new Date(System.currentTimeMillis()); }
    public Date fechaExpiracion() { return new Date(System.currentTimeMillis() + EXPIRATION_MS); }

    public String generarToken(Usuario usuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", usuario.getRol().name());
        claims.put("idUsuario", usuario.getId());
        claims.put("nombreCompleto", usuario.getNombreCompleto());
        return Jwts.builder().claims(claims).subject(usuario.getUsername())
                .issuedAt(fechaEmision()).expiration(fechaExpiracion())
                .signWith(signingKey()).compact();
    }

    public AuthResponse buildAuthResponse(Usuario u) {
        return new AuthResponse(generarToken(u), u.getUsername(),
                u.getNombreCompleto(), u.getRol().name(), fechaEmision(), fechaExpiracion());
    }

    public String extraerUsername(String token) {
        try {
            return Jwts.parser().verifyWith(signingKey()).build()
                    .parseSignedClaims(token).getPayload().getSubject();
        } catch (ExpiredJwtException e) { return e.getClaims().getSubject(); }
    }

    public String extraerRol(String token) {
        try {
            return (String) Jwts.parser().verifyWith(signingKey()).build()
                    .parseSignedClaims(token).getPayload().get("rol");
        } catch (Exception e) { return null; }
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().verifyWith(signingKey()).build().parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException e) { return false; }
        catch (Exception e) { return false; }
    }
}
