package com.controllers;

/**
 * Created by pc9 on 24.11.15.
 */

import com.entity.Book;
import com.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes(types = BookService.class)
public class MainController {

    @Autowired
    private BookService bookService;

     @RequestMapping(value = "/", method = RequestMethod.GET)
     public String getHome(Model model) {
         List<Book> bookList= bookService.getAllBooks();
         model.addAttribute("bookList",bookList);
         return "static/index.html";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        return "static/error.html";
    }
}
