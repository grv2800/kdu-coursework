package Read;

import org.example.Coins;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class ReadCoin {
    static Logger logger=Logger.getLogger(ReadCoin.class.getName());
    public static String location = "/home/hp/Desktop/Backend/Assignment-1/Assignment_1/Code/src/main/java/Resources/coins.csv";

public static void readCoin(Map<String, Coins> mp,Map<String, Coins> mp2) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(location));
        try {
            String line;
            boolean firsttime=true;
            while ((line = bufferedReader.readLine()) != null) {
                if(firsttime){
                    firsttime=false;
                    continue;
                }
                String[] arr = line.split(",");
                Coins coins = new Coins();
                coins.setRank(Integer.parseInt(arr[1]));
                String name = arr[2];
                String symbol = arr[3];
                coins.setSymbol(symbol);
                coins.setPrice(Double.parseDouble(arr[4]));
                coins.setCirculatingSupply(Long.parseLong(arr[5]));
                mp.put(name, coins);
                mp2.put(symbol,coins);
            }
        } catch (Exception e) {
            logger.info("Exception caught");
        }
        finally {
            bufferedReader.close();
        }
    }
   public static void CoinInfo(Map<String, Coins> mp, String string) throws IOException {

        Coins obj = mp.get(string);
        logger.info("rank :" + obj.getRank());
        logger.info("symbol: "+obj.getSymbol());
        logger.info("price: "+obj.getPrice());
        logger.info("circulating supply: "+obj.getCirculatingSupply());
    }
    public static void getCoinDetails(Map<String, Coins> mp, int n){
        List<Map.Entry<String, Coins>> list = new ArrayList<>(mp.entrySet());
        list.sort(Comparator.comparingDouble(entry->entry.getValue().getPrice()));
        for(int i=0;i<n;i++){
            Map.Entry<String, Coins> entry= list.get(i);
            logger.info("Coin Name: "+entry.getKey());
        }
    }
}
