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

    @Autowired
    private JsonWrappingServise jsonWrappingServise;
    @Autowired
    private DownloadBookService downloadBookService;



    @ModelAttribute
    public void setVaryResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    /**
     * method returns Json string, that contains book info from postgre DB
      *
     * @param bookIndex
     * @param session
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/book", produces = {"application/json; charset=UTF-8"})
    public String viewBookList(@RequestParam(value = "bookIndex", required = false) Integer bookIndex
            , HttpSession session, Model model) {
        List<Book>  bookList = (List<Book>) session.getAttribute("bookLi");
        Book book = null;

        if (bookIndex != null) {
            book = bookList.get(bookIndex);
        } else {
            book = bookList.get(0);
        }
        model.addAttribute("bookIndex", bookIndex);

        downloadBookService.download(String.valueOf(book.getBookId()));

        return jsonWrappingServise.getJsonString(book);
    }

    /**
     * method returns book images (10 images each time)  to the view side
     */
    @ResponseBody
    @RequestMapping(value = "/download")
    public void downloadBookJpg(@RequestParam(value = "bookIndex", required = true) Integer bookIndex
            , HttpSession session) {

        List<Book>  bookList = (List<Book>) session.getAttribute("bookLi");
        Book book = null;

        if (bookIndex != null) {
            book = bookList.get(bookIndex);
        } else {
            book = bookList.get(0);
        }

        downloadBookService.download(String.valueOf(book.getBookId()));

         //   downloadBookService.download();

    }

}
