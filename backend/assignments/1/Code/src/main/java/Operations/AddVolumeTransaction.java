package Operations;

import org.example.AddVolume;

public class AddVolumeTransaction extends Thread{
    private final AddVolume addVolume;

    public AddVolume getAddVolume(){
        return addVolume;
    }
    public AddVolumeTransaction(AddVolume addVolume){
        this.addVolume=addVolume;
    }

    @Override
    public void run() {
        synchronized (this.addVolume.getCoin()) {
            try {
                this.addVolume.getCoin().wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.addVolume.getCoin().setPrice((addVolume.getCoin().getCirculatingSupply() + addVolume.getVolume()));
        this.addVolume.getCoin().notifyAll();
    }
}
