package com.example.demo.repository;

import com.example.demo.model.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long>{
}
