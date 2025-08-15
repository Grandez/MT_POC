package com.sdp.poc.threading.mtlatchdb.core;
/**
 * Motor del paradigma Porductor/Consumidor
 * Recibe las dos clases asociadas:
 *  - El productor
 *  - El Consumidor
 *
 *  Se encarga de ejecutar el sistema de acuerdo con
 *  la parametrizacion de hilos
 *
 *  Primero se inician Consumidores
 *  Luego Productor
 *  Se finaliza en orden inverso
 *
 */

import com.sdp.poc.threading.base.parameters.Props;
import com.sdp.poc.threading.mtlatch.core.MTProducer;
import com.sdp.poc.threading.mtlatch.core.Motor;
import com.sdp.poc.threading.mtlatch.core.Timer;
import com.sdp.poc.threading.mtlatch.interfaces.IMTConsumer;
import com.sdp.poc.threading.mtlatch.interfaces.IMTProducer;
import com.sdp.poc.threading.mtlatchdb.config.CtxDB;
import com.sdp.poc.threading.mtlatchdb.interfaces.IMTDBConsumer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class MotorDB  extends Motor {
    // Constructores
    public MotorDB()                           { this.ctx = null; }
    public MotorDB(String file)                { this(file, null, null);    }
    public MotorDB(String file, String label)  { this(file, label, null); }
    public MotorDB(String file, CtxDB ca)      { this(file,null, ca);    }
    public MotorDB(CtxDB ca)                   { this("application",null, ca);    }
    public MotorDB(CtxDB ca, String file)      { this("application", null, ca);    }
    public MotorDB(CtxDB ca, String file, String prfx)    { this(file,prfx, ca);    }

    /**
     * Constructor del motor de threading
     * @param fConfig Fichero de configuracion, si null mt.properties
     * @param label   Prefijo de las entradas de configuracion
     * @param ctx     Parametros pasados por linea de comandos en la interfaz
     */
    private MotorDB(String fConfig, String label, CtxDB ctx) {
        super(fConfig, label, ctx);
    }

    @SuppressWarnings("unchecked")
    public void run(Class prodClass, Class consClass) {
        beg = System.currentTimeMillis();
        Thread thTimer;

        int threads = ctx.getNumThreads() + 1; // Sumamos el productor
        ctx.setLatch(new CountDownLatch(threads));
        executor = Executors.newFixedThreadPool(threads);

        try {
            // Arranca los consumidores
            for (int i = 0; i < ctx.getNumThreads(); i++) {
                IMTDBConsumer iCons = (IMTDBConsumer) consClass.getConstructor().newInstance();
                MTDBConsumer consumer = new MTDBConsumer((CtxDB) ctx, iCons);
                executor.execute(consumer); // Consumers
            }

            // Arranca el productor
            IMTProducer iprod = (IMTProducer) prodClass.getConstructor().newInstance();
            Thread thrProd = new Thread(new MTProducer(ctx, iprod));

            // Arrancar el gestor de tiempo
            thTimer = startTimer(thrProd);

            executor.execute(thrProd);
            executor.shutdown();          // No mas hilos
            ctx.getLatch().await();       // Esperar

            // Si habia timer, ha acabado antes de tiempo. Pararlo
            if (ctx.getTimeout() > 0) {
                thTimer.interrupt();
                thTimer.join();
            }
        } catch (InterruptedException ex) {
            System.err.println(ex.getLocalizedMessage());
        } catch(Exception ex) {
            System.err.println(ex.getLocalizedMessage());
            ex.printStackTrace();
            System.out.println(ex);
        }
    }
    // Arranca el monitor de tiempo
    public Thread startTimer(Thread prod) {
        if (ctx.getTimeout() == 0) return null;
        Thread thr = new Thread(new Timer(prod, ctx));
        thr.start();
        return thr;
    }
    private void loadPropsData(Props props, String prfx) {
        if (props == null) return;
        String p = prfx == null ? "" : prfx + ".";
        ctx.setNumThreads(props.getInteger(p + "threads", ctx.getNumThreads()));
        ctx.setTimeout(props.getInteger(p + "timeout", ctx.getTimeout()));
        ctx.setChunk(props.getInteger(p + "chunk",   ctx.getChunk()));
    }
    private String getFileProperties (String fileProps) {
        if (fileProps.compareToIgnoreCase("NONE") == 0) return null;
        if (fileProps == null) {
            String[] toks = getClass().getName().split("\\.");
            fileProps = toks[toks.length - 3];
        }
        if (fileProps.indexOf('.') == -1) fileProps = fileProps + ".properties";
        return fileProps;
    }
}
