package com.farmaciasalud.dto.request;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class ClienteRequest {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 150, message = "El nombre debe tener entre 2 y 150 caracteres")
    private String nombre;

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos numéricos")
    private String dni;

    @Size(max = 20)
    private String telefono;

    @Email(message = "El email no tiene un formato válido")
    @Size(max = 120)
    private String email;

    @Size(max = 200)
    private String direccion;

    private LocalDate fechaNacimiento;

    public ClienteRequest() {}
    public String getNombre() { return nombre; } public void setNombre(String n) { this.nombre = n; }
    public String getDni() { return dni; } public void setDni(String d) { this.dni = d; }
    public String getTelefono() { return telefono; } public void setTelefono(String t) { this.telefono = t; }
    public String getEmail() { return email; } public void setEmail(String e) { this.email = e; }
    public String getDireccion() { return direccion; } public void setDireccion(String d) { this.direccion = d; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; } public void setFechaNacimiento(LocalDate f) { this.fechaNacimiento = f; }
}
