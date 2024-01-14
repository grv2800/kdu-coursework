package org.example;

import Global.GlobalData;
import org.json.simple.JSONObject;

public class Buy {
    private Coins coin;
    private long quantity;
    private String walletAddress;
    public Buy(JSONObject jsonObject){
        String coinsymbol=(String)jsonObject.get("coin");
        this.coin= GlobalData.coinsSymbol.get(coinsymbol);
        this.quantity=(long) jsonObject.get("quantity");
        this.walletAddress=(String) jsonObject.get("wallet_address");
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
