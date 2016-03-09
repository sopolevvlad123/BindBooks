package com.service;

import com.entity.Book;
import com.entity.BookIrbisHtml;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.Constants;
import org.json.JSONString;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by pc9 on 27.11.15.
 */
@Service
public class JsonService {

    @Autowired
    private DownloadBookService downloadBookService;

    public JsonService() {
    }

    public String getBook(Integer bookIndex, HttpSession session, Model model){
            Book book  = getBookObj(bookIndex,session);
            model.addAttribute(Constants.BOOK_INDEX, bookIndex);
            downloadBookService.download(String.valueOf(book.getBookId()));

            return getJSONString(book);
    }

//////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////
    /**
     * method converts Book obj to Json string
     * @param book
     * @return
     */
      private String getJSONString(Book book) {
          ObjectMapper  mapper = new ObjectMapper();
          String result = null;
          try {
               result = mapper.writeValueAsString(book);
          } catch (JsonProcessingException e) {
              e.printStackTrace();
          }

          return result;
    }

    /**
     * converts BookIrbisHtml obj to Json string
     * @param bookHtmlList
     * @return
     */
    public String getJSONIrbisBookHtml(List<BookIrbisHtml> bookHtmlList) {
        org.json.JSONArray jsonArray = new org.json.JSONArray(bookHtmlList);

        return jsonArray.toString();
    }

    public Book getBookObj(Integer bookIndex, HttpSession session){
        List<Book>  bookList = (List<Book>) session.getAttribute(Constants.BOOK_LIST);
        Book book = null;
        if (bookIndex != null) {
            book = bookList.get(bookIndex);
        } else {
            book = bookList.get(0);
        }

        return book;
    }

}
