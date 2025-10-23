package com.camilly.api.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.camilly.api.rest.model.Usuario;

// Interface que estende JpaRepository para operações CRUD na entidade Usuario
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
}
