package com.example.GB_JAVA_SpringCore_HW4.repositories;

import com.example.GB_JAVA_SpringCore_HW4.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> { // базовые методы для работы с сущностями, такие как сохранение, удаление, поиск и т. д.
}
