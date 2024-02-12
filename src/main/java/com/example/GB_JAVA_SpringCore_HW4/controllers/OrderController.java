package com.example.GB_JAVA_SpringCore_HW4.controllers;

import com.example.GB_JAVA_SpringCore_HW4.models.Burger;
import com.example.GB_JAVA_SpringCore_HW4.models.Order;
import com.example.GB_JAVA_SpringCore_HW4.models.User;
import com.example.GB_JAVA_SpringCore_HW4.services.BurgerService;
import com.example.GB_JAVA_SpringCore_HW4.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private final OrderService orderService;
    @Autowired
    private final BurgerService burgerService;

    @PostMapping
    public String getOrder(@RequestBody Map<String, Object> requestData) {
        // Создаем объекты Burger
        List<Object> burgersIdObjects = (List<Object>) requestData.get("burgersId");
        List<Long> burgersId = burgersIdObjects.stream()
                .map(id -> Long.parseLong(id.toString()))
                .collect(Collectors.toList());
        List<Burger> burgers = new ArrayList<>();
        burgersId.forEach(id -> burgers.add(burgerService.getBurgerById(id)));

        // Создаем объект User
        String userName = (String) requestData.get("userName");
        String userPhone = (String) requestData.get("userPhone");
        User user = new User();
        user.setUserName(userName);
        user.setPhoneNumber(userPhone);

        // Создаем объект Order
        Order order = new Order();
        order.setBurgers(burgers);
        order.setUser(user);

        orderService.saveOrder(order);
        return "burgers";
    }
}
