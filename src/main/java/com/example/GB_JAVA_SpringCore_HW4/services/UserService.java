package com.example.GB_JAVA_SpringCore_HW4.services;

import com.example.GB_JAVA_SpringCore_HW4.aspects.TrackUserAction;
import com.example.GB_JAVA_SpringCore_HW4.models.Order;
import com.example.GB_JAVA_SpringCore_HW4.models.User;
import com.example.GB_JAVA_SpringCore_HW4.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @TrackUserAction
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @TrackUserAction
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    @TrackUserAction
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    @TrackUserAction
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    @TrackUserAction
    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }
}
