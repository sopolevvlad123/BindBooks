package com.service;

import com.dao.BookDaoImpl;
import com.dao.IrbisDaoImpl;
import com.entity.BookIrbis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pc9 on 12.12.15.
 */
@Service
public class TestServise {
    private final static String TEST_FIELD= "!!!!!!!TEST FIELD!!!!!!!!!!";
    @Autowired
    public IrbisDaoImpl irbisDao;

    public TestServise(){
        System.out.println("test constructor"+TEST_FIELD);
    }
    static {
        System.out.println("test servise"+TEST_FIELD);
    }
    public String getTestField (){
        return TEST_FIELD;
    }
    public BookIrbis getIrbisTest(int mfn){
        BookIrbis res = irbisDao.getBookIrbis(mfn);
        return res;
    }
}
