package com.dao;

import com.entity.Book;
import com.entity.BookIrbis;

import java.util.List;

/**
 * Created by pc8 on 03.11.15.
 */
public interface BookDao {

    Book getBookById(int id);

    List<Book> getAllBooks();

    void updateMfn(int bookId, int mfn);

    void updateName(int bookId, String name);

    void updateBook(int bookId, BookIrbis bookIrbis);
}
