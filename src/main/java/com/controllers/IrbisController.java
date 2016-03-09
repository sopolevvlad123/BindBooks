package com.controllers;

import com.entity.BookIrbisHtml;
import com.service.IrbisService;
import com.service.JsonService;
import com.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

import java.util.List;

import org.apache.log4j.*;

/**
 * Created by pc9 on 01.12.15.
 */
@Controller
public class IrbisController {
    private static final Logger logger = Logger.getLogger(IrbisController.class);

    @Autowired
    private IrbisService irbisService;
    @Autowired
    private JsonService jsonService;

    /*@ModelAttribute
    public void setVaryResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }*/

    /**
     * method returns to the view side search results
     * @param searchExpr
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", produces = "text/plain;charset=UTF-8")
    public String IrbisSearch(@RequestParam(value = Constants.SEARCH_EXPR, required = true) String searchExpr) {

        List<BookIrbisHtml> bookIrbisHtmlList = null;
        try {
            bookIrbisHtmlList = irbisService.getSearchResBookHtml(searchExpr);
        } catch (Exception e) {
            logger.error("Wrong encoding, Amigo", e);
            throw new RuntimeException(e);
        }
        String result = jsonService.getJSONIrbisBookHtml(bookIrbisHtmlList);
        return result;
    }

}
