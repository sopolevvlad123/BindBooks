package com.controllers;

/**
 * Created by pc9 on 24.11.15.
 */

import com.entity.Book;
import com.service.BookService;
import com.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.utils.Constants;

@Controller
@SessionAttributes(Constants.BOOK_LIST)
public class MainController {

    /*@ModelAttribute
    public void setVaryResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }*/


    @Autowired
    private BookService bookService;

    /**
     * method returns index.html (main view)
     * @return
     */
     @RequestMapping(value = "/")
        public String getHome(Model model) {
         List<Book> bookList= bookService.getAllBooks();
         model.addAttribute(Constants.BOOK_LIST,bookList);

         return "static/index.html";
    }

    /**
     * method returns login page
     * @return
     */
    @RequestMapping(value = "/login")
    public String getLogin() {
        return "static/login.html";
    }

    /**
     *  method returns error page
     * @return
     */
    @RequestMapping(value = "/error")
    public String error() {
        return "static/error.html";
    }
}
