package com.farmaciasalud.dto.response;
import java.util.Date;

public class AuthResponse {
    private String token;
    private final String tipo = "Bearer";
    private String username;
    private String nombreCompleto;
    private String rol;
    private Date fechaEmision;
    private Date fechaExpiracion;

    public AuthResponse() {}
    public AuthResponse(String token, String username, String nombreCompleto,
                        String rol, Date fechaEmision, Date fechaExpiracion) {
        this.token = token; this.username = username; this.nombreCompleto = nombreCompleto;
        this.rol = rol; this.fechaEmision = fechaEmision; this.fechaExpiracion = fechaExpiracion;
    }
    public String getToken() { return token; }
    public String getTipo() { return tipo; }
    public String getUsername() { return username; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getRol() { return rol; }
    public Date getFechaEmision() { return fechaEmision; }
    public Date getFechaExpiracion() { return fechaExpiracion; }
}
