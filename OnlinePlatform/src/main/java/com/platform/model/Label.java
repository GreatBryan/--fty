package com.platform.model;

import javax.persistence.*;

@Entity
@Table(name = "label")
public class Label {
    @Id
    private int id;

    private String label;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
