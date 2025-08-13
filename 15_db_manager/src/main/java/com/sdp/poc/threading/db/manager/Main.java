package com.sdp.poc.threading.db.manager;

import com.sdp.base.parameters.CLP;
import com.sdp.base.parameters.CLP_Parameter;
import com.sdp.base.parameters.Props;
import com.sdp.base.parameters.CLP_TYPE;

import com.sdp.base.logging.QLoggerProd;
import com.sdp.poc.threading.db.manager.config.ACTION;
import com.sdp.poc.threading.db.manager.core.CtxDBManager;
import com.sdp.poc.threading.db.manager.prodcons.Consumer;
import com.sdp.poc.threading.db.manager.prodcons.Producer;
import com.sdp.poc.threading.db.manager.services.*;
import com.sdp.poc.threading.mtlatch.base.MainMT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

@SpringBootApplication
public class Main extends MainMT implements ApplicationRunner {
    private CtxDBManager ctx = CtxDBManager.getInstance();
    private QLoggerProd logger;

    @Autowired
    Initializer initializer;
    @Autowired
    Loader loader;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        run("dbmanager", ctx, args.getSourceArgs());
    }

    @Override
    protected void execute() {
        // Init y reset no son multihilo
        // Loader si
        try {
            switch (ctx.getAction()) {
                case INIT: initializer.initialize(); break;
                case RESET: break;
                case LOAD:  motor.run(Producer.class, Consumer.class); break;
            }
        } catch (Throwable t) {
            System.err.println("Para");
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // MainBase
    ///////////////////////////////////////////////////////////////////////////

    protected Props parseParms(String[] args) {
        Map<String, CLP_Parameter> options = new HashMap<>();

        options.put("threads", new CLP_Parameter("threads", CLP_TYPE.PINT));
        options.put("timeout", new CLP_Parameter("timeout", CLP_TYPE.PINT));
        options.put("rows",    new CLP_Parameter("rows",    CLP_TYPE.PINT));
        options.put("init",    new CLP_Parameter("init",    CLP_TYPE.BOOL));
        options.put("reset",   new CLP_Parameter("reset",   CLP_TYPE.BOOL));
        options.put("load",    new CLP_Parameter("load",    CLP_TYPE.BOOL));
        Props props = CLP.parseParms(args, options);
        if (props.get("help") != null) showHelp();
        return props;
    }

    protected void loadConfig() {
        // No hay fichero de propiedades. solo linea de comandos
        Props props = ctx.getCommandLine();

        if (props.getBoolean("init", false)) ctx.setAction(ACTION.INIT);
        if (props.getBoolean("reset",false)) ctx.setAction(ACTION.RESET);
        if (props.getBoolean("load", false)) ctx.setAction(ACTION.LOAD);

//        ctx.setRows(props.getInteger("rows", ctx.getRows()));
    }
    protected void showHelp() {
        out.println("POC para analisis de procesos multihilo");
        out.println("Utilidad para hacer las pruebas repetibles");
        out.println();
        out.println("Uso: java -jar 15_db_manager.jar [-init | -reset | -load][--threads n][--timeout n][--rows n]");
        out.println("\t   -init       - Inicializa las tablas destino");
        out.println("\t   -reset      - Inicializa la base de datos completa");
        out.println("\t   -load       - Inicializa las tablas origen");
        out.println("\t   --rows    n - Numero de registros a generar en miles");
        out.println("\t   --threads n - Numero de hilos");
        out.println("\t   --timeout n - Maximo tiempo elapsed en minutos");
        System.exit(0);
    }

}
