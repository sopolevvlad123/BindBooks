package com.service;

/**
 * Created by pc8 on 20.11.15.
 */
public class IrbisService {

    /**
     * Method remove needless data from answer
     * @return
     */
    public String filterAnswer(String answer){

        if (answer.contains("IRBIS_BINARY_DATA")){
            int index = answer.indexOf("IRBIS_BINARY_DATA");
            answer = answer.substring(0,index);
        }
        return answer;
    }

}
