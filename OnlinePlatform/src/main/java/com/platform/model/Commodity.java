package com.platform.model;

import javax.persistence.*;

@Entity
@Table(name = "commodity")
public class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private int price;

    private int label_1;

    private int label_2;

    private int label_3;

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

    public int getLabel_1() {
        return label_1;
    }

    public void setLabel_1(int label_1) {
        this.label_1 = label_1;
    }

    public int getLabel_2() {
        return label_2;
    }

    public void setLabel_2(int label_2) {
        this.label_2 = label_2;
    }

    public int getLabel_3() {
        return label_3;
    }

    public void setLabel_3(int label_3) {
        this.label_3 = label_3;
    }
}
