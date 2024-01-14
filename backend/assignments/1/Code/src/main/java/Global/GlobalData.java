package Global;
import Read.ReadCoin;
import Read.ReadTrader;
import org.example.Coins;
import org.example.Trader;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalData {
    public static Map<String, Coins> coinName = new HashMap<>();
    public static Map<String, Coins> coinsSymbol = new HashMap<>();
    public static Map<String, Trader> traderMap=new HashMap<>();
    public static List<Trader> traderList=new ArrayList<>();
    public static JSONArray transaction;
    public static void reader() throws IOException {
        ReadCoin.readCoin(coinName,coinsSymbol);
        ReadTrader.traderInfo(traderMap);
    }

}
