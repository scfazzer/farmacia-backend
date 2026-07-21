package com.farmaciasalud.dto.request;
import jakarta.validation.constraints.*;

public class CategoriaRequest {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @Size(max = 300, message = "La descripción no puede superar 300 caracteres")
    private String descripcion;

    public CategoriaRequest() {}
    public String getNombre() { return nombre; } public void setNombre(String n) { this.nombre = n; }
    public String getDescripcion() { return descripcion; } public void setDescripcion(String d) { this.descripcion = d; }
}
