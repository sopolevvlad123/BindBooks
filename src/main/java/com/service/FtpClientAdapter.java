package com.service;

import it.sauronsoftware.ftp4j.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pc8 on 05.11.15.
 */
public class FtpClientAdapter {

    private String login;
    private String password;
    private String localpath;
    private FTPClient ftpClient = new FTPClient();

    public FtpClientAdapter(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void connect(String host) throws FTPException, IOException, FTPIllegalReplyException {
        this.ftpClient.connect(host);
        this.ftpClient.login(login, password);
    }

    public void disconnect() throws FTPException, IOException, FTPIllegalReplyException {
        if (ftpClient != null) {
            ftpClient.disconnect(true);
        }

    }

    public void download(String file) throws FTPException, IOException, FTPIllegalReplyException, FTPAbortedException, FTPDataTransferException, FTPListParseException {
        if (ftpClient != null) {

            this.ftpClient.download(file, new File(localpath + "/" + file));
        }
    }


    public void changeDirectory(String dir) throws FTPException, IOException, FTPIllegalReplyException {
        if (ftpClient != null) {
              ftpClient.changeDirectory(dir);
        }
    }

    public void setLocalpath(String localpath) {
        this.localpath = localpath;
    }


    public void changeDirectoryUp() throws FTPException, IOException, FTPIllegalReplyException {
        this.ftpClient.changeDirectoryUp();
    }

    public List<String> listNames() throws FTPException, IOException, FTPDataTransferException, FTPListParseException, FTPIllegalReplyException, FTPAbortedException {
        return Arrays.asList(this.ftpClient.listNames());
    }

}
