package com.sdp.poc.threading.db.manager.prodcons;

import com.sdp.poc.threading.base.QObject;
import com.sdp.poc.threading.base.otros.Operacion;
import com.sdp.poc.threading.db.manager.core.CtxDBManager;
import com.sdp.poc.threading.mtlatch.interfaces.IMTConsumer;

public class Consumer implements IMTConsumer {
    CtxDBManager ctx;

    public Consumer() {
        ctx = CtxDBManager.getInstance();
    }

    public void consumir(QObject msg) {
//        System.out.println(Thread.currentThread().getName() + " - Recibe " + msg);

        int row = Math.toIntExact(msg.id / 10000);
        int col = Math.toIntExact(msg.id % 10000);
        int val = 0;
//        int[] r = m.getRow(row);
//        int[] c = m.getCol(col);

        // Ambas dimensiones son iguales (Si no chequear errores)
    }
}
