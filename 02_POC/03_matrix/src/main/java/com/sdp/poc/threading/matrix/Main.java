package com.sdp.poc.threading.matrix;

import com.sdp.base.logging.loggers.QLoggerProd;
import com.sdp.base.parameters.CLP;
import com.sdp.base.parameters.CLPParameter;
import com.sdp.base.parameters.CLPType;
import com.sdp.base.parameters.Props;
import com.sdp.poc.threading.matrix.core.CtxMatrix;
import com.sdp.poc.threading.matrix.core.Matrix;
import com.sdp.poc.threading.matrix.core.TYPES;
import com.sdp.poc.threading.matrix.prodcons.Consumer;
import com.sdp.poc.threading.matrix.prodcons.Productor;
import com.sdp.poc.threading.matrix.tools.MatrixBuilder;
import com.sdp.sal.mask.RC;
import com.sdp.threading.mtlatch.base.MainMT;

import java.util.*;

import static java.lang.System.out;

public class Main extends MainMT {
    private static CtxMatrix ctx = CtxMatrix.getInstance();
    private QLoggerProd logger;

    public static void main(String[] args) { (new Main()).run("matrix", ctx, args); }

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

    @Override
    protected Props parseParms(String[] args) {
        Map<String, CLPParameter> options = new HashMap<>();

        options.put("threads", new CLPParameter("threads", "threads", CLPType.PINT));
        options.put("timeout", new CLPParameter("timeout", "timeout", CLPType.PINT));
        options.put("rows",    new CLPParameter("rows", "rows", CLPType.PINT));

        Props props = CLP.parseParms(args, options);
        if (props.get("help") != null) showHelp();
        return props;
    }

    @Override
    protected void loadConfiguration() {
        // No hay fichero de propiedades. solo linea de comandos
        Props props = ctx.getCommandLine();
        ctx.setRows(props.getInteger("rows", ctx.getRows()));
    }
    @Override
    protected void showHelp() {
        out.println("POC para analisis de procesos multihilo");
        out.println("Matrices: Eleva una matriz al cuadrado");
        out.println();
        out.println("Uso: java -jar 03_matrix.jar [--threads n][--timeout n][--rows n]");
        out.println("\t   --threads n - Numero de hilos");
        out.println("\t   --timeout n - Maximo tiempo elapsed en minutos");
        out.println("\t   --rows    n - Numero de filas/columnas de la matriz");
        System.exit(RC.OK);
    }
}