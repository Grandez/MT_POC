package com.sdp.poc.threading.mtlatchdb.interfaces;

import com.sdp.poc.threading.mtlatch.interfaces.IMTConsumer;
import org.hibernate.Session;

public interface IMTDBConsumer extends IMTConsumer {
    Session getSession();
}
