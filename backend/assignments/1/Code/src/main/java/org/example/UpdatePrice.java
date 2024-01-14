package org.example;

import Global.GlobalData;
import org.json.simple.JSONObject;

public class UpdatePrice {
    private Coins coin;
    private long quantity;
    private String walletAddress;
    private double price;
    UpdatePrice(JSONObject jsonObject){
        this.price=(double) jsonObject.get("price");
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
