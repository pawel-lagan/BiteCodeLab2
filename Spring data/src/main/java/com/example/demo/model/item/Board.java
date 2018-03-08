package com.example.demo.model.item;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="item_board")
public class Board extends Item{
    public Board() {
        this("dummyBoard",0f);
    }

    public Board(String name, Float price) {
        super(name, price);
    }
}
