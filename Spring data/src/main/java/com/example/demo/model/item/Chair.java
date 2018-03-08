package com.example.demo.model.item;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="item_chair")
public class Chair extends Item {
    public Chair() {
        this("dummyChair",0f);
    }

    public Chair(String name, Float price) {
        super(name, price);
    }
}
