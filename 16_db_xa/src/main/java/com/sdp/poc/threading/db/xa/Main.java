package com.sdp.poc.threading.db.xa;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}

/*
package com.example.xa;

import com.example.xa.entities.Master;
import com.example.xa.entities.Slave;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        XaService service = new XaService(
                ctx.getBean("sf1", org.hibernate.SessionFactory.class),
                ctx.getBean("sf2", org.hibernate.SessionFactory.class),
                ctx.getBean(javax.transaction.UserTransaction.class)
        );

        Master m = new Master();
        m.setName("John");

        Slave s = new Slave();
        s.setCode("X001");

        service.saveInBoth(m, s);

        ctx.close();
    }
}
*/