package com.sdp.poc.threading.matrix;

import com.sdp.base.parameters.CLP;
import com.sdp.base.parameters.CLP_Parameter;
import com.sdp.base.parameters.CLP_TYPE;
import com.sdp.base.parameters.Props;
import com.sdp.base.logging.QLoggerProd;
import com.sdp.base.mask.RC;
import com.sdp.poc.threading.matrix.core.CtxMatrix;
import com.sdp.poc.threading.matrix.core.Matrix;
import com.sdp.poc.threading.matrix.core.TYPES;
import com.sdp.poc.threading.matrix.prodcons.Consumer;
import com.sdp.poc.threading.matrix.prodcons.Productor;
import com.sdp.poc.threading.matrix.tools.MatrixBuilder;
import com.sdp.poc.threading.mtlatch.base.MainMT;
import com.sdp.poc.threading.mtlatch.core.Motor;

import java.util.*;

import static java.lang.System.out;

public class Main extends MainMT {
    private CtxMatrix ctx = CtxMatrix.getInstance();
    private QLoggerProd logger;

    public static void main(String[] args) {
        (new Main()).run("matrix", args);
    }

    @Override
    protected void execute() {
        for (Matrix m : creaMatrices()) {
            m.split(); // Fuerza a crear los arrays rows y cols
            ctx.setMatrix(m);
            motor.run(Productor.class, Consumer.class);
            ctx.getResult().print("Result:");
        }
    }
    private List<Matrix> creaMatrices() {
        MatrixBuilder builder = new MatrixBuilder();
        List<Matrix> lista = new ArrayList<>();
        lista.add(builder.build(ctx.getRows(), ctx.getRows(), TYPES.RANDOM));
        // Generaria una lista de matrices caracteristicas diferentes
//        for (TYPES type : TYPES.values()) {
//            lista.add(builder.build(ctx.getRows(), ctx.getRows(), type));
//        }
        return lista;
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
        ctx.setRows(props.getInteger("rows", ctx.getRows()));
    }
    protected void showHelp() {
        out.println("POC para analisis de procesos multihilo");
        out.println("Matrices: Eleva una matriz al cuadrado");
        out.println();
        out.println("Uso: java -jar 03_matrix.jar [--threads n][--timeout n][--rows n]");
        out.println("\t   --threads n - Numero de hilos");
        out.println("\t   --timeout n - Maximo tiempo elapsed en minutos");
        out.println("\t   --rows    n - Numero de filas/columnas de la matriz");
        System.exit(0);
    }

}