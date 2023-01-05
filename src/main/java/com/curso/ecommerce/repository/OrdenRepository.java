package com.curso.ecommerce.repository;

import com.curso.ecommerce.model.Orden;
import com.curso.ecommerce.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer>{
    List<Orden> findByUsuario(Usuario usuario);
}
