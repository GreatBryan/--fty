package com.platform.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "label2")
public class Label2 {
    @Id
    private int id;

    private int label1;

    private String label2name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLabel1() {
        return label1;
    }

    public void setLabel1(int label1) {
        this.label1 = label1;
    }

    public String getLabel2name() {
        return label2name;
    }

    public void setLabel2name(String label2name) {
        this.label2name = label2name;
    }

}
