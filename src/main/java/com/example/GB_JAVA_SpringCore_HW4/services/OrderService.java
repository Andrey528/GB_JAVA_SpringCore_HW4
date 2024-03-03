package com.example.GB_JAVA_SpringCore_HW4.services;

import com.example.GB_JAVA_SpringCore_HW4.aspects.TrackUserAction;
import com.example.GB_JAVA_SpringCore_HW4.models.Order;
import com.example.GB_JAVA_SpringCore_HW4.models.User;
import com.example.GB_JAVA_SpringCore_HW4.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final UserService userService;

    @TrackUserAction
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    @TrackUserAction
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
    @TrackUserAction
    public Order saveOrder(Order order) {
        User userExists = userService.findUserByPhoneNumber(order.getUser().getPhoneNumber());

        if (userExists == null) {
            User user = userService.saveUser(order.getUser());
            order.setUser(user);
        }
        else {
            order.setUser(userExists);
        }

        return orderRepository.save(order);
    }
    @TrackUserAction
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
