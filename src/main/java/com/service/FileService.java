package com.service;

import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by pc8 on 06.11.15.
 */
@Service

public class FileService {
    public FileService() {
    }

    //    @Value("${directory}")
//    private String DIRECTORY;
    private String DIRECTORY = "/home/pc8/TEST/";

    public void  deleteAllFiles(){
        File file = new File(DIRECTORY);
        for(File f : file.listFiles()) {
            if (!f.getName().equals("1")){
                deleteFile(f);
            }
        }
    }

    private void deleteFile(File file) {
        if (!file.exists())
            return;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    deleteFile(f);
                }
            }
        }
        if (!file.delete()) {
          //NEED LOGGING
        }
    }

}
