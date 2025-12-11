package com.example.favoritos_service.service;

import com.example.favoritos_service.model.Favorito;
import com.example.favoritos_service.repository.FavoritoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FavoritoService {

    private final FavoritoRepository repository;

    public FavoritoService(FavoritoRepository repository) {
        this.repository = repository;
    }

    public List<Favorito> listarPorUsuario(Long idUsuario) {
        return repository.findByIdUsuario(idUsuario);
    }

    public List<Favorito> listarTodos() {
        return repository.findAll();
    }

    public Favorito agregar(Favorito favorito) {
        favorito.setFechaAgregado(LocalDateTime.now());
        return repository.save(favorito);
    }

    public Favorito modificar(Long id, Favorito cambios) {
        return repository.findById(id)
                .map(fav -> {
                    fav.setIdUsuario(cambios.getIdUsuario());
                    fav.setIdReporte(cambios.getIdReporte());
                    fav.setNota(cambios.getNota());
                    return repository.save(fav);
                })
                .orElseThrow(() -> new RuntimeException("Favorito no encontrado con id: " + id));
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
