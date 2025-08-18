package com.sdp.threading.mtlatchdb.interfaces;

import com.sdp.threading.mtlatch.interfaces.IMTConsumer;
import org.hibernate.Session;

public interface IMTDBConsumer extends IMTConsumer {
    Session getSession();
}
