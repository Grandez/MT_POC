package com.sdp.poc.threading.simple;

import com.sdp.base.logging.loggers.QLoggerProd;
import com.sdp.base.parameters.*;

import com.sdp.poc.threading.simple.core.CtxSimple;
import com.sdp.poc.threading.simple.prodcons.*;
import com.sdp.sal.mask.RC;
import com.sdp.threading.mtlatch.base.MainMT;

import java.util.*;

import static java.lang.System.out;

public class Main extends MainMT {
    private static CtxSimple ctx = CtxSimple.getInstance();
    private QLoggerProd logger;

    public static void main(String[] args) { (new Main()).run("simple", ctx, args); }

    @Override
    protected void execute() {
        motor.run(Productor.class, Consumer.class);
    }

    ///////////////////////////////////////////////////////////////////////////
    // MainBase
    ///////////////////////////////////////////////////////////////////////////

    @Override
    protected Props parseParms(String[] args) {
        Map<String, CLPParameter> options = new HashMap<>();

        options.put("items",   new CLPParameter("items",    CLPType.PINT));
        options.put("threads", new CLPParameter("threads",  CLPType.NINT));
        options.put("timeout", new CLPParameter( "timeout", CLPType.NINT));

        Props props = CLP.parseParms(args, options);
        if (props.get("help") != null) showHelp();
        return props;
    }
    @Override
    protected void loadConfiguration() {
        Props props = ctx.getCommandLine();
        ctx.setItems(props.getInteger("items", ctx.getItems()));
    }
    @Override
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
