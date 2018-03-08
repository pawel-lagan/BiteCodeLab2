package com.example.demo;

import com.example.demo.model.item.Board;
import com.example.demo.model.item.Chair;
import com.example.demo.model.item.Item;
import com.example.demo.model.order.Order;
import com.example.demo.model.user.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@org.springframework.core.annotation.Order(1)
@Profile("!test")
@Component
public class InsertDataCMDLineRunner implements CommandLineRunner{
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("----Create data start----");
        createUsers();
        createItems();
        createOrders();
        System.out.println("----Create data end----");

    }
    private void createUsers(){
        Set<User> users = new HashSet<>();
        users.add(new User("login1","password2"));
        users.add(new User("login2","password2"));
        users.add(new User("login3","password3"));
        users.add(new User("login4","password4"));
        users.add(new User("login5","password5"));
        userRepository.saveAll(users);
    }
    private void createItems(){
        Set<Item> items = new HashSet<>();
        items.add(new Chair("fotel obrotowy",52.2f));
        items.add(new Chair("fotel stacjonarny",11.6f));
        items.add(new Chair("fotel skórzany",100.25f));
        items.add(new Board("drewniany",60.f));
        items.add(new Board("metalowy",50.f));
        items.add(new Board("plastikowy",20f));
        itemRepository.saveAll(items);
    }
    private void createOrders(){
        Random generator = new Random();
        List<User> users = userRepository.findAll();
        List<Item> items = itemRepository.findAll();
        for (User user:users){
            int gen = generator.nextInt(5);
            for (int i=0;i<gen+1;i++){
                Order order = new Order();
                Collections.shuffle(items);
                for (Item item: items.stream().limit(generator.nextInt(4)+1).collect(Collectors.toList())) {
                    order.addItem(item);
                }
                user.addOrder(order);
            }
            userRepository.save(user);
        }
    }
}
