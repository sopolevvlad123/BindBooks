package com.controllers;

/**
 * Created by pc9 on 24.11.15.
 */

import com.entity.Book;
import com.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes("bookList")
public class MainController {


    @Autowired
    private BookService bookService;

//    @ModelAttribute("bookList")
//    public void addStuffToRequestScope(Model model,HttpSession session) {
//        System.out.println("Inside of addStuffToRequestScope");
//        List<Book> bookList= bookService.getAllBooks();
//        System.out.println("main session"+session);
//        model.addAttribute("bookList",bookList);
//        session.setAttribute("bookList", bookList);
//    }


     @RequestMapping(value = "/", method = RequestMethod.GET)

     public String getHome(Model model,HttpSession session) {
         List<Book> bookList= bookService.getAllBooks();
         model.addAttribute("bookList",bookList);
         System.out.println("main"+session);
         return "static/index.html";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        return "static/error.html";
    }
}
