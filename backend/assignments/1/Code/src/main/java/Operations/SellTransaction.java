package Operations;

import org.example.Sell;



import static Global.GlobalData.traderMap;

public class SellTransaction extends Thread{
    private final Sell sell;

    public Sell getSell(){
        return sell;
    }
    public SellTransaction(Sell sell){
        this.sell=sell;
    }

    @Override
    public void run(){
        synchronized (this.sell.getCoin()) {
                try {
                    this.sell.getCoin().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            double releasedRevenue = this.sell.getCoin().getPrice() * this.sell.getQuantity() + traderMap.get(this.sell.getWalletAddress()).getReleasedRevenue();
                traderMap.get(this.sell.getWalletAddress()).setReleasedRevenue(releasedRevenue);

                long changeQuantity = traderMap.get(this.sell.getWalletAddress()).coinsMap.get(this.sell.getCoin().getSymbol()).getCirculatingSupply();
                traderMap.get(this.sell.getWalletAddress()).coinsMap.get(this.sell.getCoin().getSymbol()).setCirculatingSupply(changeQuantity - this.sell.getQuantity());

                this.sell.getCoin().setCirculatingSupply(sell.getCoin().getCirculatingSupply() + sell.getQuantity());
                this.sell.getCoin().notifyAll();
        }
    }

}
