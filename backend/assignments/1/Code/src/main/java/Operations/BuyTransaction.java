package Operations;

import org.example.Buy;
import org.example.Coins;
import org.example.HashFile;

import static Global.GlobalData.traderMap;

public class BuyTransaction extends Thread {
    private Buy buy;
    private String transactionID;

    public Buy getBuy(){
        return this.buy=buy;
    }
    public BuyTransaction(Buy buy){
        this.buy=buy;
    }
    public void run(){
        synchronized (this.buy.getCoin()) {
            while ((this.buy.getCoin().getCirculatingSupply() < this.buy.getQuantity())) {
                try {
                    this.buy.getCoin().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.transactionID = HashFile.getBlockHash();

            if (traderMap.get(this.buy.getWalletAddress()).coinsMap.containsKey(this.buy.getCoin().getSymbol())) {
                long vol = traderMap.get(this.buy.getWalletAddress()).coinsMap.get(this.buy.getCoin().getSymbol()).getCirculatingSupply() + this.buy.getQuantity();
                traderMap.get(this.buy.getWalletAddress()).coinsMap.get(this.buy.getCoin().getSymbol()).setCirculatingSupply(vol);

                Double totalSpendings = traderMap.get(this.buy.getWalletAddress()).getSpending() + this.buy.getQuantity() * this.buy.getCoin().getPrice();
                traderMap.get(this.buy.getWalletAddress()).setSpending(totalSpendings);
            } else {
                Coins coin = new Coins();
                coin.setName(this.buy.getCoin().getName());
                coin.setPrice(this.buy.getCoin().getPrice());
                coin.setSymbol(this.buy.getCoin().getSymbol());

                coin.setRank(this.buy.getCoin().getRank());
                coin.setCirculatingSupply(this.buy.getCoin().getCirculatingSupply());
                traderMap.get(this.buy.getWalletAddress()).coinsMap.put(coin.getSymbol(), coin);
                Double totalSpendings = this.buy.getQuantity() * this.buy.getCoin().getPrice();
                traderMap.get(this.buy.getWalletAddress()).setSpending(totalSpendings);
            }

            this.buy.getCoin().setCirculatingSupply(buy.getCoin().getCirculatingSupply() - buy.getQuantity());
            this.buy.getCoin().notifyAll();
        }
    }
}
