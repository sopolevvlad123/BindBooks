package com.service;

import com.dao.IrbisDao;
import com.dao.IrbisDaoImpl;
import com.entity.BookIrbisHtml;
import com.irbis.IrbisClient64;
import com.irbis.IrbisClient64Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc8 on 20.11.15.
 */
@Service
public class IrbisService {

    public IrbisService() {
    }

    @Autowired
    private IrbisDao irbisDao;

    /**
     * Method remove needless data from answer
     *
     * @return
     */
    public String filterAnswer(String answer) {


        if (answer.contains("IRBIS_BINARY_DATA")) {
            int index = answer.indexOf("IRBIS_BINARY_DATA");
            answer = answer.substring(0, index);
        }
        return answer;
    }

    /**
     * This method remove needless data from list of answer
     *
     * @param answerList
     * @return
     */
    public List<String> filterListAnswer(List<String> answerList) {
        List<String> resultList = new ArrayList<>();
        for (String str : answerList) {
            if (str.contains("IRBIS_BINARY_DATA")) {
                break;
            }
            resultList.add(str);
        }
        return resultList;
    }

    public List<BookIrbisHtml> getSearchResBookHtml(String searchExpr) throws UnsupportedEncodingException {

        String expr = exprEncoding(searchExpr);

        List<BookIrbisHtml> bookIrbisHtmlList = irbisDao.find(expr);
        return bookIrbisHtmlList;
    }

    private String exprEncoding(String expr) throws UnsupportedEncodingException {
        byte text[] = expr.getBytes();
        String result = new String(text, "windows-1251");
        return result;
    }

}
