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
        public String printWelcome(ModelMap model) {

            model.addAttribute("message", "Spring 3 MVC Hello World");

            return "static/index.html";


        }

        @RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
        public ModelAndView hello(@PathVariable("name") String name) {

            ModelAndView model = new ModelAndView();
            model.setViewName("hello");
            model.addObject("msg", name);

            return model;

        }

        @RequestMapping(value = "other")
        public String hext(){
            return "static/index.html";
        }

        @RequestMapping(value = "supa")
        public ModelAndView suppa() {

            ModelAndView model = new ModelAndView();
            model.setViewName("supaCool");


            return model;

        }

        @RequestMapping(value = "boot")
        public ModelAndView bootstrap() {

            ModelAndView model = new ModelAndView();
            model.setViewName("verstka");


            return model;

        }

        @RequestMapping(value = "dataTransit")
        public ModelAndView someData (){
            ModelAndView model = new ModelAndView();
            model.addObject("name", "LOX");

            return model;


        }




}
