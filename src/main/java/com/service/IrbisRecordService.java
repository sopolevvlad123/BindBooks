package com.service;

import com.entity.BookIrbis;
import com.irbis.IrbisClient64;
import com.irbis.IrbisField64;
import com.irbis.IrbisRecord64;
import com.irbis.IrbisSubField64;

import java.util.List;

/**
 * Created by pc8 on 19.11.15.
 */
public class IrbisRecordService {

    private IrbisService irbisService = new IrbisService();
    private IrbisClient64 irbisClient64;
    private IrbisRecord64 irbisRecord64;


    /**
     * Method convert IrtbisRecord to BookIrbis
     *
     * @return
     */
    public BookIrbis convert(IrbisRecord64 irbisRecord64) {
        this.irbisRecord64 = irbisRecord64;

        BookIrbis bookIrbis = new BookIrbis();
        bookIrbis.setMfn(irbisRecord64.getMfn());
        bookIrbis.setName(getName());
        bookIrbis.setAuthors(getAutors());
        bookIrbis.setPublishinCity(getPublishinCity());
        bookIrbis.setPublishingYear(getPublishingYear());
        bookIrbis.setKeyWords(getKeyWords());
        bookIrbis.setPublishinOffice(getPublishinOffice());
        bookIrbis.setCountPage(getCountPage());
        bookIrbis.setIndexBbk(getIndexBbk());
        bookIrbis.setPartName(getPartName());
        bookIrbis.setLibDescription(getBibDescription());
        return bookIrbis;
    }


    private String getName() {
        StringBuilder result = new StringBuilder();
        List<IrbisField64> fieldList = irbisRecord64.getFieldSet();
        IrbisSubField64 irbisSubField64;
        for (IrbisField64 irbisField64 : fieldList) {
            if (irbisField64.getTag().equals("200")) {
                irbisSubField64 = irbisField64.getSubFieldList().get('A');
                if (irbisSubField64 != null) {
                    result.append(irbisSubField64.getText());
                }
            }

        }
        return result.toString();
    }

    private String getAutors() {
        StringBuilder result = new StringBuilder();
        List<IrbisField64> fieldList = irbisRecord64.getFieldSet();
        IrbisSubField64 irbisSubField64;
        for (IrbisField64 irbisField64 : fieldList) {
            if (irbisField64.getTag().equals("700")) {
                irbisSubField64 = irbisField64.getSubFieldList().get('A');
                if (irbisSubField64 != null) {
                    result.append(irbisSubField64.getText());
                }
                irbisSubField64 = irbisField64.getSubFieldList().get('B');
                if (irbisSubField64 != null) {
                    result.append(" " + irbisSubField64.getText());
                }
            }

            if (irbisField64.getTag().equals("701")) {
                if (result.length() != 0) {
                    result.append(", ");
                }
                irbisSubField64 = irbisField64.getSubFieldList().get('A');
                if (irbisSubField64 != null) {
                    result.append(irbisSubField64.getText());
                }
                irbisSubField64 = irbisField64.getSubFieldList().get('B');
                if (irbisSubField64 != null) {
                    result.append(irbisSubField64.getText());
                }
            }

            if (irbisField64.getTag().equals("702")) {
                if (result.length() != 0) {
                    result.append(", ");
                }
                irbisSubField64 = irbisField64.getSubFieldList().get('A');
                if (irbisSubField64 != null) {
                    result.append(irbisSubField64.getText());
                }

            }
            if (irbisField64.getTag().equals("710")) {
                if (result.length() != 0) {
                    result.append(", ");
                }
                irbisSubField64 = irbisField64.getSubFieldList().get('A');
                if (irbisSubField64 != null) {
                    result.append(irbisSubField64.getText());
                }
                irbisSubField64 = irbisField64.getSubFieldList().get('B');
                if (irbisSubField64 != null) {
                    result.append(irbisSubField64.getText());
                }

            }

        }

        return result.toString();
    }

    private String getPublishinCity() {
        StringBuilder result = new StringBuilder();
        List<IrbisField64> fieldList = irbisRecord64.getFieldSet();
        IrbisSubField64 irbisSubField64;
        for (IrbisField64 irbisField64 : fieldList) {
            if (irbisField64.getTag().equals("210")) {
                irbisSubField64 = irbisField64.getSubFieldList().get('A');
                if (irbisSubField64 != null) {
                    result.append(irbisSubField64.getText());
                }
                irbisSubField64 = irbisField64.getSubFieldList().get('X');
                if (irbisSubField64 != null) {
                    result.append(" " + irbisSubField64.getText());
                }
                irbisSubField64 = irbisField64.getSubFieldList().get('Y');
                if (irbisSubField64 != null) {
                    result.append(" " + irbisSubField64.getText());
                }

            }

        }
        return result.toString();
    }

    private String getPublishingYear() {
        StringBuilder result = new StringBuilder();
        List<IrbisField64> fieldList = irbisRecord64.getFieldSet();
        IrbisSubField64 irbisSubField64;
        for (IrbisField64 irbisField64 : fieldList) {
            if (irbisField64.getTag().equals("210")) {
                irbisSubField64 = irbisField64.getSubFieldList().get('D');
                if (irbisSubField64 != null) {
                    result.append(irbisSubField64.getText());
                }
            }
        }
        return result.toString();
    }

    private String getKeyWords() {
        StringBuilder result = new StringBuilder();
        List<IrbisField64> fieldList = irbisRecord64.getFieldSet();
        for (IrbisField64 irbisField64 : fieldList) {
            if (irbisField64.getTag().equals("610")) {
                String data = irbisField64.getData();
                if (data != null) {
                    if (result.length() == 0) {
                        result.append(data);
                    } else {
                        result.append(", " + data);
                    }
                }
            }
        }
        return result.toString();
    }

    private String getPublishinOffice(){
        StringBuilder result = new StringBuilder();
        List<IrbisField64> fieldList = irbisRecord64.getFieldSet();
        IrbisSubField64 irbisSubField64;
        for (IrbisField64 irbisField64 : fieldList) {
            if (irbisField64.getTag().equals("210")) {
                irbisSubField64 = irbisField64.getSubFieldList().get('C');
                if (irbisSubField64 != null) {
                    result.append(irbisSubField64.getText());
                }
            }
        }
        return result.toString();
    }

    private String getPartName(){
        StringBuilder result = new StringBuilder();
        List<IrbisField64> fieldList = irbisRecord64.getFieldSet();
        IrbisSubField64 irbisSubField64;
        for (IrbisField64 irbisField64 : fieldList) {
            if (irbisField64.getTag().equals("923")) {
                irbisSubField64 = irbisField64.getSubFieldList().get('U');
                if (irbisSubField64 != null) {
                    result.append(irbisSubField64.getText());
                }
                irbisSubField64 = irbisField64.getSubFieldList().get('I');
                if (irbisSubField64 != null) {
                    if (result.length() != 0 ) {
                        result.append(", " + irbisSubField64.getText());
                    }
                    result.append(irbisSubField64.getText());
                }
                irbisSubField64 = irbisField64.getSubFieldList().get('H');
                if (irbisSubField64 != null) {
                    if (result.length() != 0 ) {
                        result.append(", " + irbisSubField64.getText());
                    }
                    result.append(irbisSubField64.getText());
                }

                irbisSubField64 = irbisField64.getSubFieldList().get('L');
                if (irbisSubField64 != null) {
                    if (result.length() != 0 ) {
                        result.append(", " + irbisSubField64.getText());
                    }
                    result.append(irbisSubField64.getText());
                }

                irbisSubField64 = irbisField64.getSubFieldList().get('K');
                if (irbisSubField64 != null) {
                    if (result.length() != 0 ) {
                        result.append(", " + irbisSubField64.getText());
                    }
                    result.append(irbisSubField64.getText());
                }

                irbisSubField64 = irbisField64.getSubFieldList().get('N');
                if (irbisSubField64 != null) {
                    if (result.length() != 0 ) {
                        result.append(", " + irbisSubField64.getText());
                    }
                    result.append(irbisSubField64.getText());
                }

                irbisSubField64 = irbisField64.getSubFieldList().get('M');
                if (irbisSubField64 != null) {
                    if (result.length() != 0 ) {
                        result.append(", " + irbisSubField64.getText());
                    }
                    result.append(irbisSubField64.getText());
                }

            }

        }
        return result.toString();

    }

    private String getCountPage(){
        StringBuilder result = new StringBuilder();
        List<IrbisField64> fieldList = irbisRecord64.getFieldSet();
        IrbisSubField64 irbisSubField64;
        for (IrbisField64 irbisField64 : fieldList) {
            if (irbisField64.getTag().equals("215")) {
                irbisSubField64 = irbisField64.getSubFieldList().get('A');
                if (irbisSubField64 != null) {
                    result.append(irbisSubField64.getText());
                }
            }
        }
        return result.toString();
    }

    private String getIndexBbk() {
        StringBuilder result = new StringBuilder();
        List<IrbisField64> fieldList = irbisRecord64.getFieldSet();
        for (IrbisField64 irbisField64 : fieldList) {
            if (irbisField64.getTag().equals("621")) {
                String data = irbisField64.getData();
                if (data != null) {
                    if (result.length() == 0) {
                        result.append(data);
                    } else {
                        result.append(" + " + data);
                    }
                }
            }
        }
        return result.toString();
    }

    private String getBibDescription(){
        String resultData = null;
      try {
           resultData = irbisClient64.readFormatedRecord(irbisRecord64.getMfn(), "@BRIEFP");
           resultData = irbisService.filterAnswer(resultData);
      } catch ( Exception e){
          e.printStackTrace();

      }
        return  resultData;
    }
}
