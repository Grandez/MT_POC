package com.sdp.base;

import com.sdp.base.config.CtxBase;
import com.sdp.base.logging.interfaces.Logger;
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

        // Las configuraciones deben estar cargadas antes del logger
        QLogger.start(name);
        logger = QLogger.getLogger();
        logger.startApp();
    }

    protected void appEnd() {
        logger.endApp(ctx);
        QLogger.stop();
        CLogger.info(String.format("RC: %2d - Elapsed: %s", ctx.getRC(),
                Time.elapsed(System.currentTimeMillis() - ctx.getBeg(), true)));
        System.exit(ctx.getRC());
    }

}
