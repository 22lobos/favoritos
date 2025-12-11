package com.example.favoritos_service.repository;

import com.example.favoritos_service.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {

    List<Favorito> findByIdUsuario(Long idUsuario);
}
