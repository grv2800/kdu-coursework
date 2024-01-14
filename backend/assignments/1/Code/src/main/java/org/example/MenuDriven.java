package org.example;

import Global.GlobalData;
import Read.ReadCoin;
import Read.ReadTrader;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class MenuDriven extends Thread {
    static Logger logger=Logger.getLogger(MenuDriven.class.getName());
    static Scanner sc = new Scanner(System.in);
    public static int menu;

    public static void getChoice() throws IOException {
        boolean flag = true;
        while (flag) {
            logger.info("Enter your choice 1 || 2 || 3 || 4 || 5 || 6\n");
            logger.info("1 -> Given the name or code of a coin, retrieve all its details.\n2 -> Display top 50 coins in the market based on price.\n3 -> For a given trader, show his portfolio.\n4 -> For a given trader, display the total profit or loss they have made trading in the crypto market.\n5 -> Show top 5 and bottom 5 traders based on their profit/loss.\n6 -> EXIT");
            menu = sc.nextInt();
            switch (menu) {
                case 1:
                    String name = sc.nextLine();
                    ReadCoin.CoinInfo(GlobalData.coinName, name);
                    break;
                case 2:
                    ReadCoin.getCoinDetails(GlobalData.coinName, 50);
                    break;
                case 3:
                    String tradername=sc.nextLine();
                    ReadTrader.getTraderPortfolio(GlobalData.traderMap,tradername);
                    break;
                case 4:
                    sc.nextLine();
                    String traderName = sc.nextLine();
                    ReadTrader.getTraderStatus(GlobalData.traderMap,traderName);
                    break;
                case 5:
                    logger.info("Enter value of n");
                    ReadTrader.get5Trader(GlobalData.traderMap);
                    break;
                case 6:
                    flag = false;
                    break;
                default:
                    logger.info("Enter a valid choice");
                    break;
            }
        }
    }

    @Override
    public void run() {
        try {
            getChoice();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}