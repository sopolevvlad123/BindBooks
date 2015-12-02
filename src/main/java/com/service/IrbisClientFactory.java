package com.service;

import com.irbis.IrbisClient64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by pc8 on 18.11.15.
 */
@Service
public class IrbisClientFactory {
    public IrbisClientFactory() {
    }

    @Value("${irbis.url}")
    private String URL;
    @Value("${irbis.port}")
    private String PORT;
    @Value("${irbis.username}")
    private String USERNAME;
    @Value("${irbis.password}")
    private String PASSWORD;
    @Value("${irbis.database}")
    private String DATABASE;

    public IrbisClient64 getIrbisClient(String url, Integer port, String username, String password, String database){
          return new IrbisClient64(url, port, username, password, database);
    }

    public IrbisClient64 getIrbisClient(){
    // нормальная версия
    //    return  new IrbisClient64(URL, PORT, USERNAME, PASSWORD, DATABASE);

        //тестовая версия
        return new IrbisClient64("library.nlu.edu.ua", 6666, "library" , "55555", "IBIS");
    }

}
