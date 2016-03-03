package com.controllers;

/**
 * Created by pc9 on 24.11.15.
 */

import com.entity.Book;
import com.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes("bookList")
public class MainController {

    @ModelAttribute
    public void setVaryResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }


    @Autowired
    private BookService bookService;

    /**
     * method returns index.html (main view)
     * @return
     */
     @RequestMapping(value = "/", method = RequestMethod.GET)
        public String getHome(HttpSession session) {

         List<Book> bookList= bookService.getAllBooks();

         session.setAttribute("bookLi", bookList);

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
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        return "static/error.html";
    }
}
