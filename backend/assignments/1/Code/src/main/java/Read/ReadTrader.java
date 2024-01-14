package Read;

import org.example.Trader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class ReadTrader {
    static Logger logger=Logger.getLogger(ReadTrader.class.getName());
    public static String tradersLocation = "/home/hp/Desktop/Backend/Assignment-1/Assignment_1/Code/src/main/java/Resources/traders.csv";
    public static void traderInfo(Map<String, Trader> mp2) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(tradersLocation))) {
            String line;
            boolean firsttime = true;
            while ((line = bufferedReader.readLine()) != null) {
                if (firsttime) {
                    firsttime = false;
                    continue;
                }
                String[] arr = line.split(",");

                Trader traderInfo = new Trader();
                traderInfo.setFirst_name(arr[1]);
                traderInfo.setLast_name(arr[2]);
                traderInfo.setPhone(Integer.parseInt(arr[3]));
                traderInfo.setWallet_Address(arr[4]);
                mp2.put(arr[1], traderInfo);
            }
        } catch (Exception e) {
            logger.info("exception caught");
        }
    }
    public static void getTraderPortfolio(Map<String, Trader> mp2, String name){
        String ans="The trader holds the following points"+ Arrays.toString(mp2.get(name).getPortfolio());
       logger.info(ans);
    }
    public static void getTraderStatus(Map<String, Trader> mp2, String name){
        int temp=mp2.get(name).getProfitOrLoss();
        if(temp >=0 ){
            String ans="The trader has made a profit of: "+temp;
           logger.info(ans);
            return;
        }
        String ans = "The trader has made a loss of: " + temp;
        logger.info(ans);
    }
    public static void get5Trader(Map<String, Trader> mp2){
        List<Map.Entry<String, Trader>> list = new ArrayList<>(mp2.entrySet());
        list.sort(Comparator.comparingInt(entry->entry.getValue().getProfitOrLoss()));
        logger.info("Top traders");
        for(int i=0;i<5;i++){
            Map.Entry<String, Trader> entry= list.get(i);
            logger.info("Trader: "+entry.getKey());
        }
        int n=list.size();
        logger.info("Bottom traders: ");
        for(int i = n-1; i >=(n-5); i--){
            Map.Entry<String,Trader> entry=list.get(i);
            logger.info("Trader: "+entry.getKey());
        }
    }
}
