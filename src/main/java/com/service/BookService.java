package com.service;

import com.dao.BookDaoImpl;
import com.entity.Book;

import java.util.List;

/**
 * Created by pc8 on 27.11.15.
 */
public class BookService {

      BookDaoImpl bookDao = new BookDaoImpl();

    /**
     * Method returns Book by id
     * @param id
     * @return
     */
    public Book getBook(int id){

        return bookDao.getBookById(id);

    }

    public List<Book> getAllBooks(){

        return bookDao.getAllBooks();
    }
}
