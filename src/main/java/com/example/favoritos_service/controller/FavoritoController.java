package com.example.favoritos_service.controller;

import com.example.favoritos_service.model.Favorito;
import com.example.favoritos_service.service.FavoritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos")
@CrossOrigin(origins = "*")
public class FavoritoController {

    private final FavoritoService service;

    public FavoritoController(FavoritoService service) {
        this.service = service;
    }

    // PING de prueba: GET /api/favoritos/ping
    @GetMapping("/ping")
    public String ping() {
        return "favoritos-ok";
    }

    // GET /api/favoritos   -> todos
    @GetMapping
    public List<Favorito> listarTodos() {
        return service.listarTodos();
    }

    // GET /api/favoritos/usuario/{idUsuario}
    @GetMapping("/usuario/{idUsuario}")
    public List<Favorito> listarPorUsuario(@PathVariable Long idUsuario) {
        return service.listarPorUsuario(idUsuario);
    }

    // POST /api/favoritos
    @PostMapping
    public ResponseEntity<Favorito> agregar(@RequestBody Favorito favorito) {
        Favorito creado = service.agregar(favorito);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    // PUT /api/favoritos/{id}
    @PutMapping("/{id}")
    public Favorito modificar(
            @PathVariable Long id,
            @RequestBody Favorito favorito
    ) {
        return service.modificar(id, favorito);
    }

    // DELETE /api/favoritos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
