package com.sdp.poc.parg.client;

import com.sdp.base.MainBase;
import com.sdp.base.MainDB;
import com.sdp.base.config.CtxBase;
import com.sdp.base.config.CtxDB;
import com.sdp.base.parameters.*;
import com.sdp.poc.parg.client.config.CtxParg;
import com.sdp.sal.mask.RC;

import java.util.*;

import static java.lang.System.out;

public class Main extends MainDB {
    private static CtxDB ctx = CtxDB.getInstance();
    public static void main(String[] args) { (new Main()).run("parg", (CtxBase) ctx, args, "NONE"); }

    @Override
    protected void execute() {
    }

    ///////////////////////////////////////////////////////////////////////////

    protected Props parseParms(String[] args) {
        Map<String, CLPParameter> options = new HashMap<>();

        options.put("items",   new CLPParameter("items", "items", CLPType.PINT));
        options.put("threads", new CLPParameter("threads", "threads", CLPType.NINT));
        options.put("timeout", new CLPParameter("timeout", "timeout", CLPType.NINT));

        Props props = CLP.parseParms(args, options);
        if (props.get("help") != null) showHelp();
        return props;
    }
    protected void loadConfiguration() {
        Props props = ctx.getCommandLine();
    }
    protected void showHelp() {
        out.println("POC para analisis de procesos multihilo");
        out.println("Crea y destruye hilos por bloques bajo demanda");
        out.println();
        out.println("Uso: java -jar 01_thread.jar [--threads n][--items n]");
        out.println("\t   --threads n - Numero de hilos");
        out.println("\t   --items n - Numero de mensajes");
        System.exit(RC.OK);
    }
}
