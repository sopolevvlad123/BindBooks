package com.service;

/**
 * Created by pc8 on 01.12.15.
 */
public class UrlSevice {

    /**
     * Method returns url by id
     * @param bookId
     * @return
     */
    public String getUrl(String bookId){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://oldlib.nlu.edu.ua/index.php?r=preview%2Findex&book_id=");
        stringBuilder.append(bookId);
        return stringBuilder.toString();
    }


}
