package com.controllers;

import com.entity.Book;
import com.service.BookService;
import com.service.DownloadBookService;
import com.service.JsonWrappingServise;


import it.sauronsoftware.ftp4j.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by pc9 on 27.11.15.
 */
@Controller
@SessionAttributes("bookIndex")
public class JsonController {
    private static final Logger logger = Logger.getLogger(JsonController.class);

    public JsonController() {
    }

    @Autowired
    private BookService bookService;
    @Autowired
    private JsonWrappingServise jsonWrappingServise;
    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private DownloadBookService downloadBookService;

    private List<Book> bookList = null;

    @ModelAttribute
    public void setVaryResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    @ResponseBody
    @RequestMapping(value = "/book", produces = {"application/json; charset=UTF-8"})
    public String viewBookList(@RequestParam(value = "bookIndex", required = false) Integer bookIndex
            , HttpSession session, Model model) {
        System.out.println("/book");
        System.out.println(session.getAttribute("bookServise"));
        bookList = (List<Book>) session.getAttribute("bookList");
        System.out.println(bookList);
        Book book = null;
        if (bookIndex != null) {
            book = bookList.get(bookIndex);
        } else {
            book = bookList.get(0);
        }
        model.addAttribute("bookIndex", bookIndex);
        downloadBookService = (DownloadBookService) appContext.getBean("downloadBookService", String.valueOf(book.getBookId()));
        try {
            downloadBookService.download();

        } catch (Exception e) {
            logger.error("Exception during downloading book list", e);
            throw new RuntimeException(e);
        }
        return jsonWrappingServise.getJsonString(book);
    }

    @ResponseBody
    @RequestMapping(value = "/download")
    public void downloadBookJpg() {
        try {
            downloadBookService.download();
        } catch (Exception e) {
            logger.error("Exception during downloading book scans", e);
            throw new RuntimeException(e);
        }

    }

}
