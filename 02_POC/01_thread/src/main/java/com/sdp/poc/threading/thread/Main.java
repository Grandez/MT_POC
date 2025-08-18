package com.sdp.poc.threading.thread;

import com.sdp.base.MainBase;
import com.sdp.base.parameters.*;
import com.sdp.poc.threading.thread.core.CtxThread;
import com.sdp.poc.threading.thread.prodcons.Consumer;
import com.sdp.sal.mask.RC;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.out;

public class Main extends MainBase {
    private static CtxThread ctx = CtxThread.getInstance();
    private ExecutorService executor = null;

    public static void main(String[] args) { (new Main()).run("simple", ctx, args, "NONE"); }
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

    /**
     * Mas que crear y destruir hilos
     * Lo relega en el executor que es mas optimo, pero no cambia la idea
     * NO se retuilizan hilos
     */
    @Override
    protected void execute() {
        for (int i = 0; i< ctx.getItems() * 1000; i += ctx.getNumThreads()) {
            try {
                int threads = ctx.getNumThreads(); // Sumamos el productor
                ctx.setLatch(new CountDownLatch(threads));
                executor = Executors.newFixedThreadPool(threads);
                for (int t = 0; t < threads; t++) executor.execute(new Consumer(ctx));
                executor.shutdown();          // No mas hilos
                ctx.getLatch().await();       // Esperar
            } catch (Exception ex) {
                System.err.print("Error: " + ex.getLocalizedMessage());
            }
        }
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
    protected void loadConfig() {
        Props props = ctx.getCommandLine();
        ctx.setItems(props.getInteger("items", ctx.getItems()));
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
