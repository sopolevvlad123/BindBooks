package com.controllers;

import com.entity.Book;
import com.service.BookService;
import com.service.DownloadBookService;
import com.service.JsonWrappingServise;


import it.sauronsoftware.ftp4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by pc9 on 27.11.15.
 */
@Controller
public class JsonController {

    public JsonController() {
    }
    @Autowired
    private BookService bookService;
    @Autowired
    private JsonWrappingServise jsonWrappingServise;
    @PostConstruct
    public void init() {
        System.out.println("Hello");
        this.bookList = bookService.getAllBooks();
    }
    @ModelAttribute
    public void setVaryResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    List<Book> bookList = null;
    private DownloadBookService downloadBookService;

    @ResponseBody
    @RequestMapping(value = "/book", produces={"application/json; charset=UTF-8"})
    public String viewBookList(@RequestParam(value = "bookIndex", required = false) Integer bookIndex)
            throws IOException, FTPAbortedException, FTPDataTransferException,
                   FTPException, FTPListParseException, FTPIllegalReplyException {

        Book book = null;


        if (bookIndex!=null){
             book = bookList.get(bookIndex);
        } else {
             book = bookList.get(0);
        }

        downloadBookService = new DownloadBookService(String.valueOf(book.getBookId()));
        downloadBookService.download();

        System.out.println(jsonWrappingServise.getJsonString(book));
        return  jsonWrappingServise.getJsonString(book);
    }

    @ResponseBody
    @RequestMapping(value = "/download")
    public void downloadBookJpg()
            throws IOException, FTPAbortedException, FTPDataTransferException,
            FTPException, FTPListParseException, FTPIllegalReplyException {

        downloadBookService.download();
        System.out.println("download action --- /download");
    }

}
