package org.example;

import Global.GlobalData;
import org.json.simple.JSONObject;

public class AddVolume {
    private Coins coin;
    private long volume;
    public AddVolume(JSONObject jsonObject){
        this.volume=(long) jsonObject.get("volume");
        this.coin= GlobalData.coinsSymbol.get((String) jsonObject.get("symbol"));
    }

    public Coins getCoin() {
        return coin;
    }

    public void setCoin(Coins coin) {
        this.coin = coin;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }
}
