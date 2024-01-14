package Operations;

import org.example.UpdatePrice;
public class UpdatePriceTransaction extends Thread{
    private UpdatePrice updatePrice;

    public UpdatePrice getUpdatePrice() {
        return updatePrice;
    }
    public UpdatePriceTransaction(UpdatePrice updatePrice){
        this.updatePrice=updatePrice;
    }

    @Override
    public void run(){
        synchronized (this.updatePrice.getCoin()) {
                try {
                    this.updatePrice.getCoin().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        this.updatePrice.getCoin().setPrice(updatePrice.getCoin().getPrice() + updatePrice.getPrice());
            this.updatePrice.getCoin().notifyAll();
        }
}

