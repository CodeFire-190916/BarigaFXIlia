package com.ilia.barigafx;

/**
 * Created by Илья on 30.01.2017.
 */
public class Currency {

    private String name;
    private double buy;
    private double sale;

    public Currency(String name, double buy, double sale) {
        this.name = name;
        this.buy = buy;
        this.sale = sale;
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public double getBuy() {
        return buy;
    }

    public double getSale() {
        return sale;
    }
}
