package com.example.GB_JAVA_SpringCore_HW4.repositories;

import com.example.GB_JAVA_SpringCore_HW4.models.Burger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BurgerRepository extends JpaRepository<Burger, Long> {
}
