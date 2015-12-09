package com.controllers;

import com.dao.BookDao;
import com.dao.IrbisDao;
import com.entity.Book;
import com.entity.BookIrbis;
import com.service.BookService;
import com.service.DAOService;
import com.service.IrbisService;
import com.service.UrlSevice;
import it.sauronsoftware.ftp4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by pc9 on 01.12.15.
 */
@Controller
public class BindController {
    @Autowired
    private DAOService daoService;
    @Autowired
    private UrlSevice urlSevice;



    @ModelAttribute
    public void setVaryResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
    }

    @ResponseBody
    @RequestMapping(value = "/bindBook")
    public void doBind(@RequestParam(value = "bookId", required = true) Integer bookId,
                         @RequestParam(value = "mfn", required = true) Integer mfn)
            throws IOException, FTPAbortedException, FTPDataTransferException,
            FTPException, FTPListParseException, FTPIllegalReplyException {

        BookDao bookDao = daoService.getBookDao();
        IrbisDao irbisDao = daoService.getIrbisDao();
        BookIrbis bookIrbis = irbisDao.getBookIrbis(mfn);
        bookDao.updateBook(bookId, bookIrbis);
        irbisDao.setUrl(urlSevice.getUrl(bookId), mfn);
    }

    @ResponseBody
    @RequestMapping(value = "/noBook")
    public void resetBook(@RequestParam(value = "bookId", required = true) Integer bookId)
            throws IOException, FTPAbortedException, FTPDataTransferException,
            FTPException, FTPListParseException, FTPIllegalReplyException {

        BookDao bookDao = daoService.getBookDao();
        bookDao.updateMfn(bookId, -1);

    }

}
