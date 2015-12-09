package com.dao;

import com.entity.BookIrbis;
import com.entity.BookIrbisHtml;
import com.irbis.IrbisClient64;
import com.irbis.IrbisRecord64;
import com.service.IrbisClientFactory;
import com.service.IrbisRecordService;
import com.service.IrbisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc8 on 18.11.15.
 */
@Repository
public class IrbisDaoImpl implements IrbisDao {

    public IrbisDaoImpl() {
    }

    //Test version
//    private IrbisService irbisService = new IrbisService();
//    private IrbisRecordService irbisRecordService = new IrbisRecordService();
//    private IrbisClientFactory irbisClientFactory = new IrbisClientFactory();

    //Normal version
    @Autowired
    private IrbisService irbisService;
    @Autowired
    private IrbisRecordService irbisRecordService;
    @Autowired
    private IrbisClientFactory irbisClientFactory;

    /**
     * Set url to column 951# of irbis
     *
     * @param url shoud look like as "http://url/something"
     * @param mfn
     */
    @Override
    public void setUrl(String url, int mfn) {

        IrbisClient64 irbisClient64 = irbisClientFactory.getIrbisClient();

        try {
            irbisClient64.connect();
            IrbisRecord64 irbisRecord64 = irbisClient64.readRecord(mfn, false);
            irbisRecord64.addField("951", "^B1^I" + url + "^T.");
            irbisClient64.writeRecord(irbisRecord64, false, false);
        } catch (Exception e) {
            e.printStackTrace();
            //LOGGER!!!!!!!
        } finally {
            irbisClient64.disconnect();
        }


    }

    /**
     * Return BookIrbis object which conteins parsed data about the book
     *
     * @param mfn
     * @return
     */
    @Override
    public BookIrbis getBookIrbis(int mfn) {


        IrbisClient64 irbisClient64 = irbisClientFactory.getIrbisClient();
        BookIrbis bookIrbis = null;
        try {
            irbisClient64.connect();
            IrbisRecord64 irbisRecord64 = irbisClient64.readRecord(mfn, false);
            bookIrbis = irbisRecordService.convert(irbisRecord64, irbisClient64);
            String resultData = irbisClient64.readFormatedRecord(mfn, "@BRIEFP");
            resultData = irbisService.filterAnswer(resultData);
            bookIrbis.setLibDescription(resultData);
            resultData = irbisClient64.readFormatedRecord(mfn, "@FULLW_TEST");
            bookIrbis.setFormatInfo(resultData);


        } catch (Exception e) {
            e.printStackTrace();
            //LOGGER
        } finally {
            irbisClient64.disconnect();
        }

        return bookIrbis;
    }

    /**
     * Method find books from irbis by Name
     *
     * @param find
     * @return
     */
    @Override
    public List<BookIrbisHtml> find(String find) {

        IrbisClient64 irbisClient64 = irbisClientFactory.getIrbisClient();

        List<BookIrbisHtml> resultList = new ArrayList<>();

        BookIrbisHtml bookIrbisHtml;
        String html;

        try {
            irbisClient64.connect();
            List<Integer> searchRes = irbisClient64.search("T=" + find + "$");

            for (Integer mfn : searchRes) {
                html = irbisClient64.readFormatedRecord(mfn, "@INFOW_H");
                html = irbisService.filterAnswer(html);
                bookIrbisHtml = new BookIrbisHtml(mfn, html);
                resultList.add(bookIrbisHtml);
            }

        } catch (Exception e) {

            e.printStackTrace();//NEED ADD LOGGER

        } finally {
            irbisClient64.disconnect();
        }
        return resultList;
    }


}
