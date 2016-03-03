package com.service;

import com.entity.Book;
import com.entity.BookIrbisHtml;
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

            List<Book>  bookList = (List<Book>) session.getAttribute("bookLi");
            Book book = null;

            if (bookIndex != null) {
                book = bookList.get(bookIndex);
            } else {
                book = bookList.get(0);
            }
            model.addAttribute("bookIndex", bookIndex);

            downloadBookService.download(String.valueOf(book.getBookId()));

            return getJsonString(book);
    }





    /**
     * method converts Book obj to Json string
     * @param book
     * @return
     */
      private String getJsonString(Book book) {
        JSONObject jsObject = new JSONObject();

        jsObject.put("name", book.getEname());
        jsObject.put("year", book.getYear());
        jsObject.put("author", book.getAuthor());
        jsObject.put("bookId", book.getBookId());

        return jsObject.toJSONString();
    }

    /**
     * converts BookIrbisHtml obj to Json string
     * @param bookHtmlList
     * @return
     */
    public String getJsonIrbisBookHtml(List<BookIrbisHtml> bookHtmlList) {

        org.json.JSONArray jsonArray = new org.json.JSONArray(bookHtmlList);
        return jsonArray.toString();

    }

}
