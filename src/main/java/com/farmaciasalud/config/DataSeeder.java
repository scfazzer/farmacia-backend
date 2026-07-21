package com.farmaciasalud.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.farmaciasalud.model.Usuario;
import com.farmaciasalud.repository.UsuarioRepository;

/**
 * Crea usuarios iniciales si la tabla está vacía.
 * Se ejecuta automáticamente al arrancar la aplicación.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired private UsuarioRepository repo;
    @Autowired private PasswordEncoder passEncoder;

    @Override
    public void run(String... args) {
        if (repo.count() > 0) return;

        Usuario admin = new Usuario();
        admin.setUsername("admin");
        admin.setPassword(passEncoder.encode("Admin@2025"));
        admin.setNombreCompleto("Administrador del Sistema");
        admin.setRol(Usuario.Rol.ADMIN);
        repo.save(admin);

        Usuario emp1 = new Usuario();
        emp1.setUsername("empleado1");
        emp1.setPassword(passEncoder.encode("Empleado@2025"));
        emp1.setNombreCompleto("María García López");
        emp1.setRol(Usuario.Rol.EMPLEADO);
        repo.save(emp1);

        Usuario emp2 = new Usuario();
        emp2.setUsername("empleado2");
        emp2.setPassword(passEncoder.encode("Empleado@2025"));
        emp2.setNombreCompleto("Carlos Quispe Mamani");
        emp2.setRol(Usuario.Rol.EMPLEADO);
        repo.save(emp2);

        System.out.println(">>> Usuarios creados: admin/Admin@2025 | empleado1/Empleado@2025 | empleado2/Empleado@2025");
    }
}
