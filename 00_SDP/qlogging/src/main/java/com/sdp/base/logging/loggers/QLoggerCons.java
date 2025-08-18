package com.sdp.base.logging.loggers;
/**
 * Generador de Logs
 * Lee de la cola y los escribe donde se decida
 * El proceso acabara cuando se reciba un mensaje tipo -1
 */

import com.sdp.base.logging.config.DBConfig;
import com.sdp.base.logging.entity.Log;
import com.sdp.base.logging.objects.QLogMsg;
import com.sdp.base.logging.objects.QObject;
import com.sdp.sal.ctes.Color;
import com.sdp.sal.files.MFiles;
import com.sdp.sal.mask.Bit;
import com.sdp.sal.system.PID;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QLoggerCons extends QLoggerBase implements Runnable {

    private int   level = 0x3F;
    private Color color;
    private PrintStream pout = System.out;

    boolean std = false;
    boolean out = false;
    boolean err = false;
    String  app = "NOAPP";
    BufferedWriter buff;
    FileWriter log;

    private SessionFactory sessionFactory;
    private Session session;
    private List<Log> entities = new ArrayList<>();

    public QLoggerCons()           { this("NONAME"); }
    public QLoggerCons(String app) {
        this.app   = app;
        sessionFactory = DBConfig.buildSessionFactory();
        session = sessionFactory.openSession();
    }
    @Override
    public void run() {
        int counter = 0;
        Thread.currentThread().setName("logger");
        openLogFile();

        try {
            while (true) {
                QObject msg = qlog.take();
                if (msg.isLastMessage()) break;
                write2file(msg.data);
                write2db(msg.data);

                // Transacciones por bloques
                counter++;
                if (counter % 50 == 0) counter = persistDB();
            }
            persistDB();
        } catch (InterruptedException e) {
            // Por ahora simplemente cerramos el flujo
            System.err.println("Interrupcion del logger");
            Thread.currentThread().interrupt();
        } finally {
            closeConnections();
        }

    }
    private void print(String fmt, Object... args) {
        String msg = String.format(fmt, args);
        pout.println(color.getColor() + getPrfx() + msg + Color.RESET.getColor());
    }
    private void write2file(Object obj) {
        QLogMsg msg = (QLogMsg) obj;
        try {
            log.write(msg + "\n");
        } catch (Exception ex) {
            // Do nothing
        }
    }
    private void write2db(Object obj) {
        QLogMsg msg = (QLogMsg) obj;
        Log log = new Log();
        log.setApp(msg.app);

        log.setData(msg.data);

        log.setMsg(msg.msg);

        log.setPid(msg.pid);
        log.setThread(msg.thread);
        log.setTms(msg.tms);

        log.setType(msg.msg.substring(0,3));
        log.setBlock(Integer.parseInt(msg.msg.substring(3,5)));
        log.setCode(Integer.parseInt(msg.msg.substring(5)));

        log.setFile(msg.source.getFileName());
        log.setCls(msg.source.getClassName());
        log.setLine(msg.source.getLineNumber());
        log.setMethod(msg.source.getMethodName());

        log.setUuid(PID.uuid());

        entities.add(log);
    }

    private int persistDB() {
        Transaction tx = session.beginTransaction();

        for (Log log : entities) {
            try { session.save(log);
            } catch (Exception e) {
                // Chequear que el error es de la entidad y no otro
                System.err.println("Error guardando entidad " + log.getUuid() + ": " + e.getMessage());
                session.evict(log);
            }
        }
        // Limpiar memoria
        session.flush();
        session.clear();

        tx.commit();
        entities = new ArrayList<>();
        return 0;
    }
    private static String getPrfx() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(new Date()) + " - ";
    }
    private void openLogFile() {
        try {
            File dir = MFiles.createTempSubDir("mt");
            String name = app == null ? "mt" : app;
            log = MFiles.createAppendFile(dir, name + ".log", "rw-rw-rw-");

//            buff = Files.newBufferedWriter(file);
//            log = new PrintWriter(buff);
        } catch (Exception e) {
            System.err.println("Error creando log file");
            System.err.println(e.getLocalizedMessage());
        };
    }
    private void closeConnections() {
        try {
            if (log != null)  log.close();
            if (session != null) session.close();
            if (sessionFactory != null) sessionFactory.close();
        } catch (Exception ex) {
            // Do nothing
        } finally {
            log = null;
            session = null;
            sessionFactory = null;
        }
    }
    ///  Chequea si el mensaje se debe sacar por consola
    private long checkConsole(long mark) {
        std = false;
        if (Bit.set(mark, 1)) { // Usar consola
            std = true;
            out = Bit.set(mark, 2) ? false : true;
            err = Bit.set(mark, 2) ? true : false;
        }
        return Bit.unset(mark,3);
    }
}
