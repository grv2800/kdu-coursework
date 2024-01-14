package org.example;

import java.util.HashMap;
import java.util.Map;

public class Trader {
    private String first_name;
    private String last_name;
    private long phone;
    private String Wallet_Address;
    private String[] portfolio;
    private int profitOrLoss;
    private double spending=0.0;
    private double ReleasedRevenue;
    public Map<String,Coins> coinsMap=new HashMap<>();

    public double getProfit() {
        double revenue = 0.0;
        for(Map.Entry<String, Coins> coinSymbol : coinsMap.entrySet()) {
            revenue += coinSymbol.getValue().getPrice() * coinSymbol.getValue().getCirculatingSupply();
        }
        return revenue - spending;
    }

    public void setSpending(double spending) {
        this.spending = spending;
    }
    public Double getSpending(){
        return spending;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getWallet_Address() {
        return Wallet_Address;
    }

    public void setWallet_Address(String wallet_Address) {
        Wallet_Address = wallet_Address;
    }

    public String[] getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String[] portfolio) {
        this.portfolio = portfolio;
    }

    public int getProfitOrLoss() {
        return profitOrLoss;
    }

    public void setProfitOrLoss(int profitOrLoss) {
        this.profitOrLoss = profitOrLoss;
    }
    public void setReleasedRevenue(double ReleasedRevenue){
        this.ReleasedRevenue=ReleasedRevenue;
    }

    public double getReleasedRevenue() {
        return ReleasedRevenue;
    }
}
