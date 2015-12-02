package com.service;

import com.dao.BookDao;
import com.dao.BookDaoImpl;
import com.dao.IrbisDao;
import com.dao.IrbisDaoImpl;
import org.springframework.stereotype.Service;

/**
 * Created by pc9 on 02.12.15.
 */
@Service
public class DAOService {
    public DAOService() {
    }

    public IrbisDao getIrbisDao(){
        return new IrbisDaoImpl();
    }

    public BookDao getBookDao(){
        return new BookDaoImpl();
    }

}
