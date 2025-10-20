package com.rosetim.shopping_cart_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

//@RedisHash("ShoppingCart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {

    @Id
    private String userId;
    private List<CartItem> items = new ArrayList<>();

    public ShoppingCart(String userId) {
        this.userId = userId;
    }

    public void addItem(CartItem item) {
        // Lógica para verificar se o item já existe e atualizar a quantidade, ou adicionar novo
        for (CartItem existingItem : items) {
            if (existingItem.getProductId().equals(item.getProductId())) {
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }
}
