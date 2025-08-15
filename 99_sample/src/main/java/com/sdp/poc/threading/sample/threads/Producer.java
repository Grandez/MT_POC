package com.sdp.poc.threading.sample.threads;

import com.sdp.poc.threading.database.entity.Usuario;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<Usuario> queue;

    public Producer(BlockingQueue<Usuario> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i=0; i<10; i++) {
                Usuario u = new Usuario("Nombre" + i, "Apellido" + i, "NIF" + i);
                queue.put(u);
            }
            queue.put(new Usuario("POISON", "", "")); // señal de fin
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
