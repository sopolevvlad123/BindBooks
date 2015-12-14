package com.service;

import it.sauronsoftware.ftp4j.*;
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

    private static final String FOLDER_TEN = "10";
    private static final String FOLDER_TWENTY = "20";
    private static final String FOLDER_THIRTY = "30";
    private static final int DEFAULT_CUR_PAGE = 1;
    private static final int DEFAULT_PAGE_RANGE = 10;
    private int pageRange = DEFAULT_PAGE_RANGE;
    private FtpClientAdapter ftpClientAdapter = null;
    private String bookId;
    private int currentPage = DEFAULT_CUR_PAGE;
    private int countPage;

    public DownloadBookService(String bookId) {
        this.bookId = bookId;
    }

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
    public void download() throws FTPException, IOException, FTPIllegalReplyException, FTPAbortedException, FTPDataTransferException, FTPListParseException {
        initFtpClientAdapter();
        ftpClientAdapter.connect(IP);
        File bookDir = new File(DIRECTORY + bookId);
        if (!bookDir.exists()) {
            bookDir.mkdir();
        }

        ftpClientAdapter.setLocalpath(DIRECTORY + bookId);

        changeDirToCoreState(bookId);
        ftpClientAdapter.changeDirectory(bookId);

        if (currentPage == 1) {
            setCountPage();
        }

        checkPageRange();
        for (int i = currentPage; i < currentPage + pageRange; i++) {
            ftpClientAdapter.download(i + ".jpg");
        }

        incrementCurrentPage();
        ftpClientAdapter.disconnect();
    }

    /**
     * This method initializes bookId
     *
     * @param bookId
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    private void checkPageRange() {
        if (currentPage > countPage) {
            pageRange = 0;
        }
        if (countPage < currentPage + pageRange) {
            pageRange = countPage - currentPage + 1;
        }
    }

    private void incrementCurrentPage() {
        currentPage += pageRange;
    }

    private void initFtpClientAdapter() {
        ftpClientAdapter = new FtpClientAdapter(LOGIN, PASSWORD);
    }

    private boolean changeDirToCoreState(String file) throws FTPException, IOException, FTPIllegalReplyException, FTPAbortedException, FTPDataTransferException, FTPListParseException {
        if (ftpClientAdapter != null) {
            if (checkDirectory(FOLDER_THIRTY, file)) {
                return true;
            } else if (checkDirectory(FOLDER_TWENTY, file)) {
                return true;
            } else if (checkDirectory(FOLDER_TEN, file)) ;
        }
        return false;
    }


    private boolean checkDirectory(String dir, String file) throws FTPException, IOException, FTPIllegalReplyException, FTPAbortedException, FTPDataTransferException, FTPListParseException {
        if (ftpClientAdapter != null) {
            ftpClientAdapter.changeDirectory(dir);
            List<String> list = ftpClientAdapter.listNames();
            if (list.contains(file)) {
                return true;
            }
        } else {
            //something
        }
        ftpClientAdapter.changeDirectoryUp();
        return false;
    }

    private void setCountPage() throws FTPException, IOException, FTPDataTransferException, FTPListParseException, FTPIllegalReplyException, FTPAbortedException {
        int count = 0;
        List<String> list = ftpClientAdapter.listNames();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).endsWith(".jpg")) {
                count++;
            }
        }
        countPage = count;
    }


}
