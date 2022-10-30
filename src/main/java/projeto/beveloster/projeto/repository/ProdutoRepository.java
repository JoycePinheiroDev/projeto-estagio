package projeto.beveloster.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.beveloster.projeto.model.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
