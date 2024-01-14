package org.example;

import Global.GlobalData;
import org.json.simple.JSONObject;

public class Sell {
    private Coins coin;
    private long quantity;
    private String walletAddress;
    public Sell(JSONObject jsonObject){
        this.quantity=(long)jsonObject.get("quantity");
        this.coin= GlobalData.coinsSymbol.get((String) jsonObject.get("coin"));
    }

    public Coins getCoin() {
        return coin;
    }

    public void setCoin(Coins coin) {
        this.coin = coin;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }
}
