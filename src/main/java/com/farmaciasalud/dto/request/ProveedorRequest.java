package com.farmaciasalud.dto.request;
import jakarta.validation.constraints.*;

public class ProveedorRequest {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 150)
    private String nombre;

    @NotBlank(message = "El RUC es obligatorio")
    @Pattern(regexp = "\\d{11}", message = "El RUC debe tener exactamente 11 dígitos numéricos")
    private String ruc;

    @Size(max = 200)
    private String direccion;

    @Size(max = 20)
    private String telefono;

    @Email(message = "El email no tiene un formato válido")
    @Size(max = 120)
    private String email;

    public ProveedorRequest() {}
    public String getNombre() { return nombre; } public void setNombre(String n) { this.nombre = n; }
    public String getRuc() { return ruc; } public void setRuc(String r) { this.ruc = r; }
    public String getDireccion() { return direccion; } public void setDireccion(String d) { this.direccion = d; }
    public String getTelefono() { return telefono; } public void setTelefono(String t) { this.telefono = t; }
    public String getEmail() { return email; } public void setEmail(String e) { this.email = e; }
}
