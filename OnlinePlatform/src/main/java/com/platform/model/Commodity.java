package com.platform.model;

import javax.persistence.*;

@Entity
@Table(name = "commodity")
public class Commodity {
    @Id
    private int id;

    private String name;

    private int price;

    private int label1;

    private int label2;

    private int label3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getLabel1() {
        return label1;
    }

    public void setLabel1(int label1) {
        this.label1 = label1;
    }

    public int getLabel2() {
        return label2;
    }

    public void setLabel2(int label2) {
        this.label2 = label2;
    }

    public int getLabel3() {
        return label3;
    }

    public void setLabel3(int label3) {
        this.label3 = label3;
    }
}
