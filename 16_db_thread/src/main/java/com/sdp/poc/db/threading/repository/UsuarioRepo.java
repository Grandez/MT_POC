package com.sdp.poc.db.threading.repository;

import com.sdp.poc.db.threading.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {}
