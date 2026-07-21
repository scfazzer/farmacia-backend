package com.farmaciasalud.dto.request;
import jakarta.validation.constraints.*;
import com.farmaciasalud.model.Usuario;

public class UsuarioRequest {
    @NotBlank(message = "El username es obligatorio")
    @Size(min = 3, max = 60, message = "El username debe tener entre 3 y 60 caracteres")
    private String username;

    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(max = 120)
    private String nombreCompleto;

    @NotNull(message = "El rol es obligatorio")
    private Usuario.Rol rol;

    public UsuarioRequest() {}
    public String getUsername() { return username; } public void setUsername(String u) { this.username = u; }
    public String getPassword() { return password; } public void setPassword(String p) { this.password = p; }
    public String getNombreCompleto() { return nombreCompleto; } public void setNombreCompleto(String n) { this.nombreCompleto = n; }
    public Usuario.Rol getRol() { return rol; } public void setRol(Usuario.Rol r) { this.rol = r; }
}
