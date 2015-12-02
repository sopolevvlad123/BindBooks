package com.controllers;

import com.service.DownloadBookService;
import it.sauronsoftware.ftp4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Created by pc9 on 30.11.15.
 */
@Controller
public class JpgDownloadController {


    private DownloadBookService downloadBookService;

    @ModelAttribute
    public void setVaryResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");


    }

    @ResponseBody
    @RequestMapping(value = "/jpgView")
    public String jpgView(@RequestParam(value = "pageIndex", required = false) Integer padeIndex)
            throws IOException, FTPAbortedException, FTPDataTransferException,
                   FTPException, FTPListParseException, FTPIllegalReplyException {
        downloadBookService.download();

        return  null;

    }
}
