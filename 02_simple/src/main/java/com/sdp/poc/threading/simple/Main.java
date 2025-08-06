package com.sdp.poc.threading.simple;

import com.sdp.poc.threading.base.mask.RC;
import com.sdp.poc.threading.base.parameters.CLP;
import com.sdp.poc.threading.base.parameters.CLP_Parameter;
import com.sdp.poc.threading.base.parameters.CLP_TYPE;
import com.sdp.poc.threading.base.parameters.Props;
import com.sdp.poc.threading.base.MainBase;
import com.sdp.poc.threading.base.logging.QLoggerProd;
import com.sdp.poc.threading.mtlatch.core.Motor;
import com.sdp.poc.threading.simple.core.CtxSimple;
import com.sdp.poc.threading.simple.prodcons.Consumer;
import com.sdp.poc.threading.simple.prodcons.Productor;

import java.util.*;

import static java.lang.System.out;

public class Main extends MainBase {
    private CtxSimple ctx = CtxSimple.getInstance();
    private QLoggerProd logger;

    public static void main(String[] args) {
        (new Main()).run(args);
    }
    private void run(String[] args) {
        try {
            appInit("simple", ctx, args);
            Motor motor = new Motor(ctx);
            motor.run(Productor.class, Consumer.class);
        } catch (SecurityException se) {
           System.err.println("Control-c pulsado");
        } catch (Exception se) {
            System.err.println(se.getLocalizedMessage());
            ctx.setRC(32);
        } finally {
            appEnd();
        }

    }
    ///////////////////////////////////////////////////////////////////////////
    // MainBase
    ///////////////////////////////////////////////////////////////////////////

     protected Props parseParms(String[] args) {
        Map<String, CLP_Parameter> options = new HashMap<>();

        options.put("items", new CLP_Parameter("items",   "items",   CLP_TYPE.PINT));
        options.put("threads", new CLP_Parameter("threads", "threads", CLP_TYPE.NINT));
        options.put("timeout", new CLP_Parameter("timeout", "timeout", CLP_TYPE.NINT));

        Props props = CLP.parseParms(args, options);
        if (props.get("help") != null) showHelp();
        return props;
    }
    protected void loadConfig() {
        Props props = ctx.getCommandLine();
        ctx.setItems(props.getInteger("items", ctx.getItems()));
    }
    protected void showHelp() {
        out.println("POC para analisis de procesos multihilo");
        out.println("Simplemente saca un mensaje por consola");
        out.println();
        out.println("Uso: java -jar 02_simple.jar [--items n] [--threads n] [--timeout n]");
        out.println("\t   --items   n - Numero de mensajes");
        out.println("\t   --threads n - Numero de hilos");
        out.println("\t   --timeout n - Maximo tiempo elapsed en minutos");
        System.exit(RC.OK);
    }

}
