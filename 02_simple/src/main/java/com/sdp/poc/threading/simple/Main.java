package com.sdp.poc.threading.simple;

import com.sdp.poc.threading.base.mask.RC;
import com.sdp.poc.threading.base.parameters.*;
import com.sdp.poc.threading.base.MainBase;
import com.sdp.poc.threading.base.logging.QLoggerProd;
import com.sdp.poc.threading.mtlatch.base.MainMT;
import com.sdp.poc.threading.mtlatch.core.Motor;
import com.sdp.poc.threading.simple.core.CtxSimple;
import com.sdp.poc.threading.simple.prodcons.*;

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
        Map<String, CLP_Parameter> options = new HashMap<>();

        options.put("items", new CLP_Parameter("items",   CLP_TYPE.PINT));
        options.put("threads", new CLP_Parameter("threads", CLP_TYPE.NINT));
        options.put("timeout", new CLP_Parameter( "timeout", CLP_TYPE.NINT));

        Props props = CLP.parseParms(args, options);
        if (props.get("help") != null) showHelp();
        return props;
    }
    @Override
    protected void loadConfig() {
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
