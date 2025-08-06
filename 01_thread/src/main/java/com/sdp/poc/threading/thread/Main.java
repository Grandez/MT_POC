package com.sdp.poc.threading.thread;

import com.sdp.poc.threading.base.mask.RC;
import com.sdp.poc.threading.base.parameters.CLP;
import com.sdp.poc.threading.base.parameters.CLP_Parameter;
import com.sdp.poc.threading.base.parameters.CLP_TYPE;
import com.sdp.poc.threading.base.parameters.Props;
import com.sdp.poc.threading.base.MainBase;
import com.sdp.poc.threading.base.logging.QLoggerProd;
import com.sdp.poc.threading.mtlatch.core.Motor;
import com.sdp.poc.threading.thread.core.CtxThread;
import com.sdp.poc.threading.thread.prodcons.Consumer;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.out;

public class Main extends MainBase {
    private CtxThread ctx = CtxThread.getInstance();
    private QLoggerProd logger;
    private ExecutorService executor = null;

    public static void main(String[] args) {
        (new Main()).run(args);
    }
    private void run(String[] args) {
        int item = 0;
        Consumer nothread = new Consumer(ctx);
        int threads = ctx.getNumThreads();

        appInit("thread", ctx, args);
        Motor motor = new Motor(ctx);
        while (item < ctx.getItems() * 1000) {
            ctx.read();
            item += (threads > 0) ? threading(threads) : nothread.proceso();
        }
        appEnd();
    }
    private int threading(int threads) {
        try {
            ctx.setLatch(new CountDownLatch(threads));
            executor = Executors.newFixedThreadPool(threads);
            for (int thread = 0; thread < threads; thread++) {
                Consumer cons = new Consumer(ctx);
                cons.run();
            }
            ctx.getLatch().await();
        } catch (SecurityException se) {
            ctx.rc |= RC.INTERRUPTED;
            System.err.println("Control-c pulsado");
        } catch (Exception se) {
            ctx.rc |= RC.CRITICAL;
            System.err.println(se.getLocalizedMessage());
        }
        return threads;
    }
    ///////////////////////////////////////////////////////////////////////////
    // MainBase
    ///////////////////////////////////////////////////////////////////////////

     protected Props parseParms(String[] args) {
        Map<String, CLP_Parameter> options = new HashMap<>();

        options.put("items",   new CLP_Parameter("items", "items", CLP_TYPE.PINT));
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
        out.println("Crea hilos bajo demanda");
        out.println();
        out.println("Uso: java -jar 01_thread.jar [--threads n][--items n]");
        out.println("\t   --threads n - Numero de hilos");
        out.println("\t   --items n - Numero de mensajes");
        System.exit(RC.OK);
    }
}
