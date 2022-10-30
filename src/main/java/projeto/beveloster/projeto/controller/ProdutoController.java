package projeto.beveloster.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.beveloster.projeto.model.Produto;
import projeto.beveloster.projeto.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    // Listar todos os produtos
    @GetMapping()
    public ResponseEntity<List<Produto>> getAll(){
        return new ResponseEntity<>(produtoRepository.findAll(), HttpStatus.OK);
    }

    // Listar produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable(value = "id") Integer id){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()){
            return new ResponseEntity<Produto>(produtoOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Criar produto
    @PostMapping()
    public ResponseEntity<Produto> saveProduto(@RequestBody Produto produto){
        return new ResponseEntity<Produto>(produtoRepository.save(produto), HttpStatus.CREATED);

    }

    // Alterar um produto
    @PutMapping("/{id}")
    public ResponseEntity<Produto> putProduto(@PathVariable(value = "id") Integer id, @RequestBody Produto novoProduto){
        Optional<Produto> antigoProduto = produtoRepository.findById(id);
        if (antigoProduto.isPresent()){
            Produto produto = antigoProduto.get();
            produto.setNome(novoProduto.getNome());
            produto.setPreco(novoProduto.getPreco());
            produto.setQuantidade(novoProduto.getQuantidade());

            produtoRepository.save(produto);

            return new ResponseEntity<Produto>(produto, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Detetar produto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable(value = "id") Integer id){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()){
            produtoRepository.delete(produtoOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
