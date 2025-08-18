package com.sdp.base;

import com.sdp.base.config.CtxBase;
import com.sdp.base.logging.Logger;
import com.sdp.base.logging.QLogger;
import com.sdp.base.logging.loggers.CLogger;
import com.sdp.base.parameters.Props;
import com.sdp.sal.datetime.Time;
import com.sdp.sal.mask.RC;
import com.sdp.sal.system.Shutdown;

public abstract class MainBase {
    protected CtxBase ctx;
    protected Logger  logger;

    // Este es el metodo que debe implementar la aplicacion/webservice
    protected abstract void execute();
    // Analiza la linea de comandos segun la aplicacion
    protected abstract Props parseParms(String[] args);
    // Sobreescribe la configuracion de la app con los parametros de linea de comandos
    protected abstract void  loadConfiguration();
    // Muestra la ayuda de la aplicacion
    protected abstract void  showHelp();

    public void run(String name, CtxBase ctx, String[] args, String fName) {
        try {
            appInit(name, ctx, args);
//            motor = new Motor(ctx, fName);
            execute();
        } catch (SecurityException se) {
            ctx.rc |= RC.INTERRUPTED;
            System.err.println("Control-c pulsado");
        } catch (Exception se) {
            ctx.rc |= RC.CRITICAL;
            System.err.println(se.getLocalizedMessage());
        } finally {
            appEnd();
        }
    }

    protected void appInit(String name, CtxBase ctx, String[] args) {
        Shutdown.setHook();

        this.ctx = ctx;
        ctx.setAppName(name);
        ctx.setCommandLine(parseParms(args));

        loadConfiguration();

        // Todas las configuraciones deben estar cargadas
        // antes de arrancar el logger
        QLogger.start(name);
        logger = QLogger.getLogger();
    }

    protected void appEnd() {
        // Separamos las ejecuciones con timeout que las que no
        // Desvituarian los analisis
        String type = ctx.getTimeout() > 0 ? "SMR01000" : "SMR01001";
        logger.info(0,type, System.currentTimeMillis() - ctx.getBeg()
                , ctx.getRC()
                , ctx.getInput(), ctx.getOutput(), ctx.getErrors()
                , ctx.getNumThreads(), ctx.getChunk(), ctx.getTimeout()
        );
        QLogger.stop();
        CLogger.info(String.format("RC: %2d - Elapsed: %s", ctx.getRC(),
                Time.elapsed(System.currentTimeMillis() - ctx.getBeg(), true)));
        System.exit(ctx.getRC());
    }

}
