package com.rosetim.shopping_cart_service.repository;

import com.rosetim.shopping_cart_service.model.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class ShoppingCartRepository {

    // Define um prefixo para manter nossas chaves de carrinho organizadas
    public static final String KEY_PREFIX = "cart:";
    // Define o tempo de vida do carrinho para 30 dias
    public static final Duration CART_TTL = Duration.ofDays(30);

    // A chave do nosso Hash no Redis, definida na anotação @RedisHash("ShoppingCart")
    //private static final String HASH_KEY = "ShoppingCart";

    // Injetamos o template reativo para interagir com o Redis
    private final ReactiveRedisTemplate<String, ShoppingCart> reactiveRedisTemplate;

    // Helper para gerar a chave completa no Redis (ex: "cart:123")
    private String getKey(String userId) {
        return KEY_PREFIX + userId;
    }

    /**
     * Salva ou atualiza um carrinho de compras no Redis.
     */
    public Mono<ShoppingCart> save(ShoppingCart cart) {
        String key = getKey(cart.getUserId());
        return reactiveRedisTemplate.opsForValue()
                .set(key,cart)
                .then(reactiveRedisTemplate.expire(key, CART_TTL)) // Define o TTL para expiração automática
                .thenReturn(cart);
    }

    /**
     * Busca um carrinho de compras pelo ID do usuário.
     */
    public Mono<ShoppingCart> findById(String userId) {
        // .get() busca o valor (carrinho) associado à chave (userId) dentro do Hash (HASH_KEY)
        return reactiveRedisTemplate.opsForValue()
                .get(getKey(userId));
    }

    /**
     * Deleta um carrinho de compras pelo ID do usuário.
     */
    public Mono<Void> deleteById(String userId) {
        // .remove() deleta a chave (userId) e seu valor do Hash
        return reactiveRedisTemplate.opsForValue()
                .delete(getKey(userId))
                .then();
    }
}
