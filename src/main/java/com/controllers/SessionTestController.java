package com.controllers;

import com.entity.Book;
import com.service.BookService;
import com.service.TestServise;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by pc9 on 12.12.15.
 */
@Controller
@SessionAttributes(types = TestServise.class)
public class SessionTestController {

    /*@RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(Model model) {
        model.addAttribute("bookList", new BookService().getAllBooks());
        return "static/index.html";
    }*/

    @RequestMapping(value = "/sessTest", method = RequestMethod.GET)
    public String ptintSession(Model model, HttpSession session) {
        System.out.println("Sess test START");
        System.out.println("SESSION" + session);

        String sessTest = (String) session.getAttribute("sessTest");


        System.out.println("BOOK LIST"+session.getAttribute("bookList"));
        TestServise testServise = (TestServise) session.getAttribute("testServise");
        System.out.println("Model native Test"+ testServise.getTestField());
        System.out.println("Servlet style test"+sessTest);
        return sessTest;
    }
}
