package com.sdp.poc.threading.sample.service;

import com.sdp.poc.threading.sample.entities.Usuario;
import com.sdp.poc.threading.sample.repository.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDao repo;

    @Transactional
    public void crearUsuario(String nombre) {
        repo.save(new Usuario(nombre));
    }

    public List<Usuario> listarUsuarios() {
        return repo.findAll();
    }
}
