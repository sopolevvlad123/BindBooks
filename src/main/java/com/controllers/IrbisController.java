package com.controllers;

import com.entity.BookIrbisHtml;
import com.service.IrbisService;
import com.service.JsonWrappingServise;
import it.sauronsoftware.ftp4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by pc9 on 01.12.15.
 */
@Controller
public class IrbisController {

    @Autowired
    private IrbisService irbisService;
    @Autowired
    private JsonWrappingServise jsonWrappingServise;
    @ModelAttribute
    public void setVaryResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }




    @ResponseBody
    @RequestMapping(value = "/search", produces = "text/plain;charset=UTF-8")
    public String IrbisSearch (@RequestParam(value = "searchExpr", required = true) String searchExpr){

        List<BookIrbisHtml> bookIrbisHtmlList = null;
        try {
            bookIrbisHtmlList = irbisService.getSearchResBookHtml(searchExpr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String result = jsonWrappingServise.getJsonIrbisBookHtml(bookIrbisHtmlList);
        return result;

        }

}
