package com.dao;

import com.entity.BookIrbis;
import com.entity.BookIrbisHtml;

import java.util.List;

/**
 * Created by pc8 on 18.11.15.
 */
public interface IrbisDao {

        void setUrl(String url, int mfn);
        BookIrbis getBookIrbis(int mfn);
        List<BookIrbisHtml> find(String find);

        void setUrl(String url);
}
