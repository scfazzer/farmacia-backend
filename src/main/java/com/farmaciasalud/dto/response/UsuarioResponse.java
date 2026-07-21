package com.farmaciasalud.dto.response;

public class UsuarioResponse {
    private Long id;
    private String username;
    private String nombreCompleto;
    private String rol;
    private Boolean activo;
    // SIN campo password — nunca se expone

    public UsuarioResponse() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; } public void setUsername(String u) { this.username = u; }
    public String getNombreCompleto() { return nombreCompleto; } public void setNombreCompleto(String n) { this.nombreCompleto = n; }
    public String getRol() { return rol; } public void setRol(String r) { this.rol = r; }
    public Boolean getActivo() { return activo; } public void setActivo(Boolean a) { this.activo = a; }
}
