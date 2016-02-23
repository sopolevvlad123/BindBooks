package com.service;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

/**
 * Created by pc8 on 06.08.15.
 */


public class CloseableSession implements AutoCloseable {

    private final Session session;

    public CloseableSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public void close() {
        session.close();
    }

}
