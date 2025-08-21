package com.sdp.base.parg.client;

import com.sdp.base.parg.client.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService service = ctx.getBean(UserService.class);

        service.getAllUsers().forEach(u ->
                System.out.println(u.getId() + " - " + u.getName() + " - " + u.getEmail())
        );

        ctx.close();
    }
}
