package com.camilly.api.rest.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.camilly.api.rest.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
}
