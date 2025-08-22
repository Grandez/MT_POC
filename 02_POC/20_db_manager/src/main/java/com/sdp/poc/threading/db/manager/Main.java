package com.sdp.poc.threading.db.manager;

import com.sdp.base.MainDB;
import com.sdp.base.parameters.CLP;
import com.sdp.base.parameters.CLPParameter;
import com.sdp.base.parameters.CLPType;
import com.sdp.base.parameters.Props;
import com.sdp.poc.threading.db.manager.config.ACTION;
import com.sdp.poc.threading.db.manager.config.CtxDBManager;
import com.sdp.poc.threading.db.manager.prodcons.Consumer;
import com.sdp.poc.threading.db.manager.prodcons.Producer;
import com.sdp.poc.threading.db.manager.services.*;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

public class Main extends MainDB { // implements ApplicationRunner {
    private static CtxDBManager ctx = CtxDBManager.getInstance();

    Initializer initializer;
    Loader loader;

    public static void main(String[] args) { (new Main()).run("dbmanager", ctx, args); }

    @Override
    protected void execute() {
        // Init y reset no son multihilo, Loader si
        try {
            switch (ctx.getAction()) {
                case RESET: break;
                case LOAD: motor.run(Producer.class, Consumer.class); break;
                case INIT: initializer = (Initializer) ctx.getBean(Initializer.class);
                           initializer.initialize(); break;
            }
        } catch (Throwable t) {
            System.err.println("Para");
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // MainBase
    ///////////////////////////////////////////////////////////////////////////

    protected Props parseParms(String[] args) {
        Map<String, CLPParameter> options = new HashMap<>();

        options.put("threads", new CLPParameter("threads", CLPType.PINT));
        options.put("timeout", new CLPParameter("timeout", CLPType.PINT));
        options.put("chunk",   new CLPParameter("chunk",   CLPType.PINT));
        options.put("rows",    new CLPParameter("rows",    CLPType.PINT));
        options.put("init",    new CLPParameter("init",    CLPType.BOOL));
        options.put("reset",   new CLPParameter("reset",   CLPType.BOOL));
        options.put("load",    new CLPParameter("load",    CLPType.BOOL));
        Props props = CLP.parseParms(args, options);
        if (props.get("help") != null) showHelp();
        return props;
    }

    protected void loadConfiguration() {
        Props props = ctx.getCommandLine();

        if (props.getBoolean("init", false)) ctx.setAction(ACTION.INIT);
        if (props.getBoolean("reset",false)) ctx.setAction(ACTION.RESET);
        if (props.getBoolean("load", false)) ctx.setAction(ACTION.LOAD);
    }
    protected void showHelp() {
        out.println("POC para analisis de procesos multihilo");
        out.println("Utilidad para hacer las pruebas repetibles");
        out.println();
        out.println("Uso: java -jar 15_db_manager.jar [-init | -reset | -load | check]");
        out.println("                                 [--threads n][--timeout n][--rows n]");
        out.println("\t   -init       - Inicializa las tablas destino");
        out.println("\t   -reset      - Inicializa la base de datos completa");
        out.println("\t   -load       - Inicializa las tablas origen");
        out.println("\t   -check      - Verifica el estado de la base de datos");
        out.println("\t   --rows    n - Numero de registros a generar en miles");
        out.println("\t   --threads n - Numero de hilos");
        out.println("\t   --timeout n - Maximo tiempo elapsed en minutos");
        out.println("\t   --chunk   n - Numero de operaciones para persistir la transaccion");
        System.exit(0);
    }

}
