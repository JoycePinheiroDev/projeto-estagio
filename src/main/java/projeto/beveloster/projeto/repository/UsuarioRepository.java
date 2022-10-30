package projeto.beveloster.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.beveloster.projeto.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
