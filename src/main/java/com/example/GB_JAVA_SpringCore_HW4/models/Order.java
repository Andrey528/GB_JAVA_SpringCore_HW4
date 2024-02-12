package com.example.GB_JAVA_SpringCore_HW4.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // автоматическая генерация значений с помощью базы данных
    private Long id;

    @ManyToOne // многие заказы могут быть сделаны одним пользователем
    @JoinColumn(name = "user_id", nullable = false) // связь таблицы users в таблице orders представлена как users_id
    private User user; // один единственный пользователь, связанный с заказом

    @ManyToMany // много разных бургеров могут быть в многих разных заказах
    @JoinTable(name = "order_burger", //промежуточная таблица order_burger - связь между таблицами orders и burgers
            joinColumns = @JoinColumn(name = "order_id"), // столбец, используемый для связи с таблицей orders - order_id
    inverseJoinColumns = @JoinColumn(name = "burger_id")) // столбец, используемый для связи с таблицей burgers - burger_id
    private List<Burger> burgers; // список бургеров, связанных с заказом
}
