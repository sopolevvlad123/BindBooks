package com;

import com.entity.BookIrbis;
import com.irbis.IrbisClient64;
import com.irbis.IrbisClient64Exception;
import it.sauronsoftware.ftp4j.*;
import com.dao.IrbisDaoImpl;
import com.entity.BookIrbisHtml;

import java.io.IOException;
import java.util.List;

/**
 * Created by pc8 on 05.11.15.
 */
public class MainTest {


   /* public static void main(String[] args) throws FTPException, IOException, FTPIllegalReplyException, FTPAbortedException, FTPDataTransferException, FTPListParseException {
//        MainTest mainTest = new MainTest();
//        FtpClientAdapter ftpClientAdapter = new FtpClientAdapter("convertbookuser", "ktnf.obqntktdbpjh");
//
//        ftpClientAdapter.connect("77.244.44.21");
//        ftpClientAdapter.setLocalpath("/home/pc8/TEST");
//        File bookDir = new File("/home/pc8/TEST");
//        if (!bookDir.mkdir()) {
//            System.out.println("FAIL");
//        }
//        ftpClientAdapter.download("1006");
//        ftpClientAdapter.download("1006");
//         ftpClientAdapter.disconnect();
//        DownloadBookService downloadService = new DownloadBookService("1035");
//        for (int i = 0; i < 100; i++) {
//            downloadService.download();
//        }

           IrbisDaoImpl irbisDao = new IrbisDaoImpl();
           BookIrbis bookIrbis = irbisDao.getBookIrbis(1);
        System.out.println(bookIrbis);
//        IrbisDaoImpl irbisDao = new IrbisDaoImpl();
//        List<BookIrbisHtml> list = irbisDao.find("Порядок");
//        for (BookIrbisHtml bookIrbisHtml : list){
//            System.out.println(bookIrbisHtml);
//        }

//        IrbisClient64 irbisClient64 = new IrbisClient64("library.nlu.edu.ua", 6666, "library" , "55555", "IBIS");
//        try {
//            irbisClient64.connect();
//            String code = "T=КОДЕКС$";
//            byte ptext[] = code.getBytes();
//            String value = new String(ptext, "windows-1251");
//            List<Integer> searchRes = irbisClient64.search(value);
//            System.out.println(searchRes);
//            System.out.println(irbisClient64.readFormatedRecord(1, "@BRIEFP"));
//        } catch (IrbisClient64Exception e) {
//            e.printStackTrace();
//        } finally {
//            irbisClient64.disconnect();
//        }


//        FileService fileService = new FileService();
//        fileService.deleteAllFiles();
*/
    }

