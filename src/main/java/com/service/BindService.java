package com.service;

import com.dao.BookDao;
import com.dao.IrbisDao;
import com.entity.Book;
import com.entity.BookIrbis;
import com.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

/**
 * Created by pc9 on 03.03.16.
 */
@Service
public class BindService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private IrbisDao irbisDao;

    @Autowired
    private UrlSevice urlSevice;

    @Autowired
    private FileService fileService;

    @Value("${directory}")
    private String DIRECTORY;


    public void bind(Integer bookId, Integer mfn, HttpSession session){
        BookIrbis bookIrbis = irbisDao.getBookIrbis(mfn);
        bookDao.updateBook(bookId, bookIrbis);
        irbisDao.setUrl(urlSevice.getUrl(bookId), mfn);
        fileService.deleteFile(new File(DIRECTORY + bookId));
        removeBookFromList(session);
    }


    public void reset(Integer bookId, HttpSession session){
        bookDao.updateMfn(bookId, -1);
        fileService.deleteFile(new File(DIRECTORY + bookId));
        removeBookFromList(session);
    }





    private int getBookIndex(HttpSession session) {
        int res = ((Integer) session.getAttribute(Constants.BOOK_INDEX));
        if (res < 0) {
            return 0;
        } else {
            return res;
        }
    }

    private void removeBookFromList(HttpSession session) {
        List<Book> bookList = (List<Book>) session.getAttribute(Constants.BOOK_LIST);
        bookList.remove(getBookIndex(session));
    }


}
