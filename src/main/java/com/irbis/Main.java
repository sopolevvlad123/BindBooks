package com.irbis;

import com.dao.IrbisDao;
import com.dao.IrbisDaoImpl;
import com.entity.BookIrbis;
import com.service.IrbisService;
import com.service.UrlSevice;

import java.util.List;

/**
 * Created by pc8 on 28.10.15.
 */
public class Main {
    public static void main(String... args) {
     //   IrbisClient64 irbisClient64 = new IrbisClient64("library.nlu.edu.ua", 6666, "library" , "55555", "IBIS");
        IrbisClient64 irbisClient64 = new IrbisClient64("10.251.0.19", 6666, "master" , "MASTERKEY", "IBIS");
     //   IrbisClient64 irbisClient64 = new IrbisClient64(); ;

        try {

            irbisClient64.connect();

//       тут я забираю данные в анси
         //   List<String> list  = irbisClient64.readRecordAnswer(11,false);
           // а тут в ютф
           IrbisRecord64 irbisRecord64  = irbisClient64.readRecord(12, false);

            System.out.println(irbisRecord64 + "//");

     //    TESTED
//            List<Integer> searchRes = irbisClient64.search("T=Уголовный$");
//            System.out.println(searchRes.get(0));
//            System.out.println(searchRes.size());
//           String resultData = irbisClient64.readFormatedRecord(searchRes.get(0), "@INFOW");
//           IrbisService irbisService = new IrbisService();
//            irbisService.filterAnswer(resultData);
//
//            System.out.println(irbisService.filterAnswer(resultData));
//              resultData = irbisClient64.readFormatedRecord(searchRes.get(0), "@BRIEFP");
//            System.out.println(irbisService.filterAnswer(resultData));
//            resultData = irbisClient64.readFormatedRecord(searchRes.get(0), "@UMARCW");
//            System.out.println(irbisService.filterAnswer(resultData));
//            resultData = irbisClient64.readFormatedRecord(searchRes.get(0), "@ATHRAW");
//            System.out.println(resultData);

//               IrbisRecord64 irbisRecord64 = irbisClient64.readRecord(447, false);
//
//               IrbisRecordService irbisRecordService = new IrbisRecordService();
//               BookIrbis bookIrbis =  irbisRecordService.convert(irbisRecord64);
//               System.out.println(bookIrbis.getName());
//               System.out.println(bookIrbis.getAuthors());
//               System.out.println(bookIrbis.getPublishinCity());
//               System.out.println(bookIrbis.getPublishingYear());
//               System.out.println(bookIrbis.getKeyWords());
//
//               BookIrbis bookIrbis = new IrbisDaoImpl().getBookIrbis(11996);
//               System.out.println("1: " + bookIrbis.getName());
//               System.out.println("2: " + bookIrbis.getAuthors());
//               System.out.println("3: " + bookIrbis.getPublishinOffice());
//               System.out.println("4: " + bookIrbis.getPublishinCity());
//               System.out.println("5: " + bookIrbis.getPublishingYear());
//               System.out.println("6: " + bookIrbis.getLibDescription());
//               System.out.println("7: " + bookIrbis.getKeyWords());
//               System.out.println("8: " + bookIrbis.getPartName());
//               System.out.println("9: " + bookIrbis.getIndexBbk());
//               System.out.println("10: " + bookIrbis.getCountPage());


          //    System.out.println(list.get(6).getTag());



            //IrbisRecordService irbisRecordService = new IrbisRecordService();

//         for (String str : list){
//             System.out.println(str);
//             System.out.println("|");
//         }
//
//            list = new IrbisService().filterListAnswer(list);




         //   System.out.println(irbisRecord64.getData("200", 'I'));

//               System.out.println(data);
//               data = irbisRecord64.getData("210");

           // System.out.println(list);


           //  DELETE
//            for (int i = 0; i < list.size(); i++){
//               if(list.get(i).startsWith("951#") ){
//                    list.remove(i);
//                }
//
//            }

//            String str = "200#^AО минувшем";
//            byte text[] = str.getBytes();
//            String result = new String(text, "windows-1251");
//
//           list.add(result);
        //   list.add("907#^CКТ^A20110531^B111");

            //тут я пишу юрл в их базу
            IrbisDao irbisDao = new IrbisDaoImpl();
            irbisDao.setUrl(new UrlSevice().getUrl(0), 9);

//            IrbisRecord64 irbisRecord64 = IrbisRecord64.parse(list,1);
//            irbisClient64.writeRecord(irbisRecord64, false, false);
//            list  = irbisClient64.readRecordAnswer(6440,false);
//            System.out.println(list);


     //       System.out.println(irbisRecord64);


       //     IrbisRecord64 irbisRecord64 = IrbisRecord64.parse(list, 1);
         //   irbisRecord64 = IrbisRecord64.parse(list, 1);
        //    irbisClient64.writeRecord(irbisRecord64, false, false);




                    } catch (Exception e) {
            e.printStackTrace();

       } finally {
            irbisClient64.disconnect();
        }
    }
}
