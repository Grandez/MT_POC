package com.sdp.threading.mtlatchdb;

import org.hibernate.Session;

public interface ICtxDB {
    Session getSession();
    Object getBean( Class<?> cls);
}
