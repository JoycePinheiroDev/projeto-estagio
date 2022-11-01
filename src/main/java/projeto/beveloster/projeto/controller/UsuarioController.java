package projeto.beveloster.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.beveloster.projeto.model.Produto;
import projeto.beveloster.projeto.model.Usuario;
import projeto.beveloster.projeto.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    // Lista todos
    @GetMapping()
    public ResponseEntity<List<Usuario>> getAll(){
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }

    // Lista por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable(value = "id") Integer id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()){
            return new ResponseEntity<>(usuarioOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Cria um novo usuário
    @PostMapping()
    public ResponseEntity<Usuario> saveUsuario (@RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    // Detetar um usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") Integer id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()){
            usuarioRepository.delete(usuarioOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
