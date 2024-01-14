package org.example;
import Global.GlobalData;
import Operations.AddVolumeTransaction;
import Operations.BuyTransaction;
import Operations.SellTransaction;
import Operations.UpdatePriceTransaction;
import Read.ReadTransaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.logging.Logger;

public class Main {


    public static void main(String[] args) {
        Logger logger=Logger.getLogger(Main.class.getName());
        try {
            GlobalData.reader();
            MenuDriven md = new MenuDriven();
            md.start();
            JSONArray transaction =ReadTransaction.parseTransaction("/home/hp/Desktop/Backend/Assignment-1/Assignment_1/Code/src/main/java/Resources/test_transaction.json");
            for (Object obj : transaction) {

                JSONObject temp = (JSONObject) obj;

                if (ReadTransaction.parseType(temp).equals("BUY")) {
                    try {
                        Buy buy = new Buy((JSONObject) ReadTransaction.parseData(temp));
                        Thread buyCoinTransaction = new BuyTransaction(buy);
                        buyCoinTransaction.start();
                    }
                    catch (Exception e) {
                        logger.info("Hello");
                        e.printStackTrace();
                    }
                }
                else if(ReadTransaction.parseType(temp).equals("SELL")) {
                    try {
                        Sell sell = new Sell((JSONObject) ReadTransaction.parseData(temp));
                        Thread sellCoinTransaction = new SellTransaction(sell);
                        sellCoinTransaction.start();
                    } catch (Exception e) {
                        logger.info("sell");
                        logger.info(e.getMessage());
                    }
                } else if(ReadTransaction.parseType(temp).equals("ADD_VOLUME")) {
                    try {
                        AddVolume addVolume = new AddVolume((JSONObject) ReadTransaction.parseData(temp));
                        Thread addVolumeTransaction = new AddVolumeTransaction(addVolume);
                        addVolumeTransaction.start();
                    } catch (Exception e) {
                        logger.info("add volume");
                        logger.info(e.getMessage());
                    }
                } else if(ReadTransaction.parseType(temp).equals("UPDATE_PRICE")) {
                    try {
                        UpdatePrice updatePrice = new UpdatePrice((JSONObject) ReadTransaction.parseData(temp));
                        Thread updatePriceTransaction = new UpdatePriceTransaction(updatePrice);
                        updatePriceTransaction.start();
                    } catch (Exception e) {
                        logger.info("update price");
                        logger.info(e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }





}