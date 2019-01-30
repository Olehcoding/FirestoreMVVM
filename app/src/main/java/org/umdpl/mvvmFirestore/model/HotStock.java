package org.umdpl.mvvmFirestore.model;

public class HotStock {
    private String price;
    private String ticker;

    public HotStock() {
    }


    public HotStock(String price, String ticker) {
        this.price = price;
        this.ticker = ticker;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
