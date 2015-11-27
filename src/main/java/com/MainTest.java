package com;

import it.sauronsoftware.ftp4j.*;
import com.dao.IrbisDaoImpl;
import com.entity.BookIrbisHtml;

import java.io.IOException;
import java.util.List;

/**
 * Created by pc8 on 05.11.15.
 */
public class MainTest {


    public static void main(String[] args) throws FTPException, IOException, FTPIllegalReplyException, FTPAbortedException, FTPDataTransferException, FTPListParseException {
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
        List<BookIrbisHtml> list = irbisDao.find("Порядок");
        for (BookIrbisHtml bookIrbisHtml : list){
            System.out.println(bookIrbisHtml);
        }


//        FileService fileService = new FileService();
//        fileService.deleteAllFiles();

    }
}
