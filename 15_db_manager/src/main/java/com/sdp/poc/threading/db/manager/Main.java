package com.sdp.poc.threading.db.manager;

import com.sdp.poc.threading.base.parameters.*;

import com.sdp.poc.threading.base.MainBase;
import com.sdp.poc.threading.base.logging.QLoggerProd;
import com.sdp.poc.threading.db.manager.core.CtxDBManager;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

@SpringBootApplication
public class Main extends MainBase implements ApplicationRunner {
    private CtxDBManager ctx = CtxDBManager.getInstance();;
    private QLoggerProd logger;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("# NonOptionArgs: " + args.getNonOptionArgs().size());

        System.out.println("NonOptionArgs:");
        args.getNonOptionArgs().forEach(System.out::println);

        System.out.println("# OptionArgs: " + args.getOptionNames().size());
        System.out.println("OptionArgs:");

        args.getOptionNames().forEach(optionName -> {
            System.out.println(optionName + "=" + args.getOptionValues(optionName));
        });
    }

    ///////////////////////////////////////////////////////////////////////////
    // MainBase
    ///////////////////////////////////////////////////////////////////////////

    protected Props parseParms(String[] args) {
        Map<String, CLP_Parameter> options = new HashMap<>();

        options.put("threads", new CLP_Parameter("threads", "threads", CLP_TYPE.PINT));
        options.put("timeout", new CLP_Parameter("timeout", "timeout", CLP_TYPE.PINT));
        options.put("rows",    new CLP_Parameter("rows", "rows", CLP_TYPE.PINT));

        Props props = CLP.parseParms(args, options);
        if (props.get("help") != null) showHelp();
        return props;
    }

    protected void loadConfig() {
        // No hay fichero de propiedades. solo linea de comandos
        Props props = ctx.getCommandLine();
//        ctx.setRows(props.getInteger("rows", ctx.getRows()));
    }
    protected void showHelp() {
        out.println("POC para analisis de procesos multihilo");
        out.println("DataBase: Procesa una base de datos por defecto");
        out.println();
        out.println("Uso: java -jar 04_db_thread.jar [--threads n][--timeout n]");
        out.println("\t   --threads n - Numero de hilos");
        out.println("\t   --timeout n - Maximo tiempo elapsed en minutos");
        System.exit(0);
    }

}
