package com.service;

import it.sauronsoftware.ftp4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by pc8 on 05.11.15.
 */
@Service
public class DownloadBookService {

    public DownloadBookService() {
    }

    @Value("${ip}")
    private String IP;
    @Value("${login}")
    private String LOGIN;
    @Value("${password}")
    private String PASSWORD;
    @Value("${directory}")
    private String DIRECTORY;

    @Autowired
    BookCacheService bookCacheService;

    private static final String FOLDER_TEN = "10";
    private static final String FOLDER_TWENTY = "20";
    private static final String FOLDER_THIRTY = "30";
    private static final int DEFAULT_CUR_PAGE = 1;
    private static final int DEFAULT_PAGE_RANGE = 10;


    /**
     * Method downloads each time new 10 pages
     *
     * @throws FTPException
     * @throws IOException
     * @throws FTPIllegalReplyException
     * @throws FTPAbortedException
     * @throws FTPDataTransferException
     * @throws FTPListParseException
     */
    public void download(String bookId) {
        int currentPage = DEFAULT_CUR_PAGE;
        int pageRange = DEFAULT_PAGE_RANGE;
        FtpClientAdapter ftpClientAdapter = new FtpClientAdapter(LOGIN, PASSWORD);
        try {

            ftpClientAdapter.connect(IP);
            File bookDir = new File(DIRECTORY + bookId);
            if (!bookDir.exists()) {
                bookDir.mkdir();
            }

            ftpClientAdapter.setLocalpath(DIRECTORY + bookId);

            changeDirToCoreState(bookId, ftpClientAdapter);
            ftpClientAdapter.changeDirectory(bookId);

            if (bookCacheService.contains(bookId)) {
                currentPage = bookCacheService.get(bookId);
            } else

                pageRange = checkPageRange(currentPage, getCountPage(ftpClientAdapter));

            for (int i = currentPage; i < currentPage + pageRange; i++) {
                ftpClientAdapter.download(i + ".jpg");
            }

            bookCacheService.add(bookId, currentPage + pageRange);

        } catch (Exception e) {
              e.printStackTrace();
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        } finally {
            try {
                ftpClientAdapter.disconnect();
            } catch (FTPException | IOException | FTPIllegalReplyException e) {
                e.printStackTrace();//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }
        }
    }


    private int checkPageRange(int currentPage, int countPage) {
        if (currentPage > countPage) {
            return 0;
        }
        if (countPage < currentPage + DEFAULT_PAGE_RANGE) {
            return countPage - currentPage + 1;
        }
        return 0;
    }


    private boolean changeDirToCoreState(String file, FtpClientAdapter ftpClientAdapter) {
        try {
            if (ftpClientAdapter != null) {
                if (checkDirectory(FOLDER_THIRTY, file, ftpClientAdapter)) {
                    return true;
                } else if (checkDirectory(FOLDER_TWENTY, file, ftpClientAdapter)) {
                    return true;
                } else if (checkDirectory(FOLDER_TEN, file, ftpClientAdapter)) {
                    return false;
                }
            }
        } catch (Exception e) {
          e.printStackTrace();
            //!!!!!!!!!!!!!!!!!!!!!!!!!!
        }

        return false;
    }

    private boolean checkDirectory(String dir, String file, FtpClientAdapter ftpClientAdapter) {
        try {
            if (ftpClientAdapter != null) {
                ftpClientAdapter.changeDirectory(dir);
                List<String> list = ftpClientAdapter.listNames();
                if (list.contains(file)) {
                    return true;
                }
            } else {
                throw new RuntimeException("ftpClientAdapter is null ");
            }
            ftpClientAdapter.changeDirectoryUp();
        } catch (Exception e) {
            e.printStackTrace();
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        }
        return false;
    }

    private int getCountPage(FtpClientAdapter ftpClientAdapter) {
        int count = 0;
        try {
            List<String> list = ftpClientAdapter.listNames();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).endsWith(".jpg")) {
                    count++;
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
            //!!!!!!!!!!!!!!!!!!!!
        }

        return count;
    }


}
