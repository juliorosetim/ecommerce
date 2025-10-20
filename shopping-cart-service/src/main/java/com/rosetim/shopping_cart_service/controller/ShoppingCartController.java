package com.rosetim.shopping_cart_service.controller;

import com.rosetim.shopping_cart_service.model.CartItem;
import com.rosetim.shopping_cart_service.model.ShoppingCart;
import com.rosetim.shopping_cart_service.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartRepository shoppingCartRepository;

    // Endpoint para buscar o carrinho de um usuário
    @GetMapping("/{userId}")
    public Mono<ShoppingCart> getCart(@PathVariable String userId) {
        // Tenta encontrar o carrinho. Se não existir, cria um novo e vazio.
        return shoppingCartRepository.findById(userId)
                .defaultIfEmpty(new ShoppingCart(userId));
    }

    // Endpoint para adicionar um item ao carrinho de um usuário
    @PostMapping("/{userId}/items")
    public Mono<ShoppingCart> addItemToCart(@PathVariable String userId, @RequestBody CartItem item) {
        // 1. Busca o carrinho do usuário ou cria um novo se não existir
        return shoppingCartRepository.findById(userId)
                .defaultIfEmpty(new ShoppingCart(userId))
                // 2. Adiciona o novo item
                .flatMap(cart -> {
                    cart.addItem(item);
                    // 3. Salva o carrinho atualizado de volta no Redis
                    return shoppingCartRepository.save(cart);
                });
    }

    // Endpoint para limpar o carrinho de um usuário
    @DeleteMapping("/{userId}")
    public Mono<Void> clearCart(@PathVariable String userId) {
        return shoppingCartRepository.deleteById(userId);
    }

    @GetMapping("/get-cart-items/{userId}")
    public Mono<ResponseEntity<ShoppingCart>> getCartItems(@PathVariable String userId) {
        return shoppingCartRepository.findById(userId) // 1. Tenta encontrar o carrinho
                .map(cart -> ResponseEntity.ok(cart)) // 2. Se encontrar, embrulha em uma resposta 200 OK
                .defaultIfEmpty(ResponseEntity.notFound().build()); // 3. Se não encontrar (Mono vazio), retorna um 404 Not Found
    }
}
