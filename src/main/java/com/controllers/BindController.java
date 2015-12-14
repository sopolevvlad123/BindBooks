package com.controllers;

import com.dao.BookDao;
import com.dao.IrbisDao;
import com.entity.Book;
import com.entity.BookIrbis;
import com.service.*;
import it.sauronsoftware.ftp4j.*;
import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by pc9 on 01.12.15.
 */
@Controller
public class BindController {
    private static final Logger logger = Logger.getLogger(BindController.class);
    @Autowired
    private DAOService daoService;
    @Autowired
    private UrlSevice urlSevice;
    @Autowired
    private FileService  fileService;

    @Value("${directory}")
    private String DIRECTORY;

    @ModelAttribute
    public void setVaryResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
    }

    @ResponseBody
    @RequestMapping(value = "/bindBook")
    public void doBind(@RequestParam(value ="bookId", required = true) Integer bookId,
                         @RequestParam(value ="mfn", required = true) Integer mfn,HttpSession session)
            throws IOException, FTPAbortedException, FTPDataTransferException,
            FTPException, FTPListParseException, FTPIllegalReplyException {

        System.out.println("bindBook; bookId = " + bookId + ", mfn = " + mfn);
        BookDao bookDao = daoService.getBookDao();
        IrbisDao irbisDao = daoService.getIrbisDao();
        BookIrbis bookIrbis = irbisDao.getBookIrbis(mfn);

        /*bookDao.updateBook(bookId, bookIrbis);
        irbisDao.setUrl(urlSevice.getUrl(bookId)), mfn);*/

        fileService.deleteFile(new File(DIRECTORY + bookId));
        removeBookFromList(session);

        if(logger.isDebugEnabled()){
            logger.debug("Book bound: post bookId = " + bookId + ", IRBIS mfn = " + mfn );
        }

    }

    @ResponseBody
    @RequestMapping(value = "/noBook")
    public void resetBook(@RequestParam(value = "bookId", required = true) Integer bookId, HttpSession session)
            throws IOException, FTPAbortedException, FTPDataTransferException,
            FTPException, FTPListParseException, FTPIllegalReplyException
             {
        /*BookDao bookDao = daoService.getBookDao();
        bookDao.updateMfn(bookId, -1);*/

        fileService.deleteFile(new File(DIRECTORY + bookId));
        removeBookFromList(session);

        if(logger.isDebugEnabled()){
            logger.debug("No march found for bookId" + bookId);
        }

    }


    private  int getBookIndex(HttpSession session){
        int res = ((Integer)session.getAttribute("bookIndex"));

        if (res<0){
            return 0;
        }
        else {return res;}
    }

    private void removeBookFromList(HttpSession session){
        List<Book> bookList =  (List<Book>) session.getAttribute("bookList");
        bookList.remove(getBookIndex(session));
    }

}
