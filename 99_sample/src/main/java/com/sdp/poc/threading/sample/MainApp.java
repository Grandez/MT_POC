package com.sdp.poc.threading.sample;

import com.sdp.poc.threading.database.dao.UsuarioDAO;
import com.sdp.poc.threading.sample.threads.Consumer;
import com.sdp.poc.threading.sample.threads.Producer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.sdp.poc.threading");

        UsuarioDAO dao = context.getBean(UsuarioDAO.class);

        BlockingQueue queue = new ArrayBlockingQueue<>(20);

        Thread producer = new Thread(new Producer(queue));
        Thread consumer1 = new Thread(new Consumer(queue, dao));
        Thread consumer2 = new Thread(new Consumer(queue, dao));

        producer.start();
        consumer1.start();
        consumer2.start();

        try {
            producer.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        context.close();
    }
}
