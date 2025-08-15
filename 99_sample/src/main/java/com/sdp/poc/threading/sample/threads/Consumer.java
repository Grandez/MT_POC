package com.sdp.poc.threading.sample.threads;

import com.sdp.poc.threading.database.dao.UsuarioDAO;
import com.sdp.poc.threading.database.entity.Usuario;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<Usuario> queue;
    private UsuarioDAO usuarioDAO;

    public Consumer(BlockingQueue<Usuario> queue, UsuarioDAO usuarioDAO) {
        this.queue = queue;
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Usuario u = (Usuario) queue.take();
                if ("POISON".equals(u.getNombre())) {
                    break;
                }
                usuarioDAO.guardar(u);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
