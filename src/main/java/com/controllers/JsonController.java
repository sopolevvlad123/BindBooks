package com.controllers;

import com.entity.Book;
import com.service.DownloadBookService;
import com.service.JsonService;


import com.utils.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by pc9 on 27.11.15.
 */
@Controller
@SessionAttributes({Constants.BOOK_INDEX,Constants.BOOK_LIST})

public class JsonController {

   @Autowired
   private JsonService jsonService;

   @Autowired
   private DownloadBookService downloadBookService;

    /*@ModelAttribute
    public void setVaryResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }*/

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
    public String viewBookList(@RequestParam(value =Constants.BOOK_INDEX, required = false) Integer bookIndex
            , HttpSession session, Model model) {

        return jsonService.getBook(bookIndex,session,model);

    }

    /**
     * method returns book images (10 images each time)  to the view side
     */
    @ResponseBody
    @RequestMapping(value = "/download", method = RequestMethod.PATCH)
    public void downloadBookJpg(@RequestParam(value =Constants.BOOK_INDEX, required = true) Integer bookIndex
            , HttpSession session) {

        Book book = jsonService.getBookObj(bookIndex, session);
        downloadBookService.download(String.valueOf(book.getBookId()));
    }

}
