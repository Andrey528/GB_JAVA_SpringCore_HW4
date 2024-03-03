package com.example.GB_JAVA_SpringCore_HW4.services;

import com.example.GB_JAVA_SpringCore_HW4.aspects.TrackUserAction;
import com.example.GB_JAVA_SpringCore_HW4.models.Burger;
import com.example.GB_JAVA_SpringCore_HW4.repositories.BurgerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BurgerService { // На будущее для пополнения меню бургеров
    @Autowired
    private final BurgerRepository burgerRepository;

    @TrackUserAction
    public List<Burger> getAllBurgers() {
        return burgerRepository.findAll();
    }
    @TrackUserAction
    public Burger getBurgerById(Long id) {
        return burgerRepository.findById(id).orElse(null);
    }
    @TrackUserAction
    public Burger saveBurger(Burger burger) {
        return burgerRepository.save(burger);
    }
    @TrackUserAction
    public void deleteBurger(Long id) {
        burgerRepository.deleteById(id);
    }
}
