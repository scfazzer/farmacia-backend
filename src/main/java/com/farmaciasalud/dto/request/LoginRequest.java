package com.farmaciasalud.dto.request;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "El username es obligatorio")
    private String username;
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    public LoginRequest() {}
    public String getUsername() { return username; } public void setUsername(String u) { this.username = u; }
    public String getPassword() { return password; } public void setPassword(String p) { this.password = p; }
}
