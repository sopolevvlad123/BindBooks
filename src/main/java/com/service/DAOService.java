package com.service;

import com.dao.BookDao;
import com.dao.BookDaoImpl;
import com.dao.IrbisDao;
import com.dao.IrbisDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pc9 on 02.12.15.
 */
@Service
public class DAOService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private IrbisDao irbisDao;

    public DAOService() {

    }

    public IrbisDao getIrbisDao() {
        return irbisDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

}
