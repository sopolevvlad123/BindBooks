package com.service;

import com.entity.Book;
import com.entity.BookIrbisHtml;
import org.json.JSONString;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pc9 on 27.11.15.
 */
@Service
public class JsonWrappingServise {
    public JsonWrappingServise() {
    }

    public String getJsonString(Book book){
        JSONObject jsObject = new JSONObject();

        jsObject.put("name",book.getEname());
        jsObject.put("year",book.getYear());
        jsObject.put("author", book.getAuthor());
        jsObject.put("bookId",book.getBookId());

        return jsObject.toJSONString();
    }

    public String getJsonIrbisBookHtml(List<BookIrbisHtml> bookHtmlList){

       org.json.JSONArray jsonArray = new org.json.JSONArray(bookHtmlList);
       return jsonArray.toString();

    }
    public JSONString getJsonTest(String txt){
        org.json.JSONString jString = (JSONString) new org.json.JSONTokener(txt);
        return jString;

    }
}
