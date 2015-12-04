package com.controllers;

/**
 * Created by pc9 on 24.11.15.
 */

import  org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {


        @RequestMapping(value = "/", method = RequestMethod.GET)
        public String printWelcome() {



            return "static/index.html";


        }






}
