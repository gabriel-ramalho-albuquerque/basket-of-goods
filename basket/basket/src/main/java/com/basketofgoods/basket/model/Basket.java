package com.basketofgoods.basket.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Basket {
    private List<Order> orderList;
}
