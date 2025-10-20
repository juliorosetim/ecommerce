package com.rosetim.shopping_cart_service.config;

import com.rosetim.shopping_cart_service.model.ShoppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public ReactiveRedisTemplate<String, ShoppingCart> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        // Serializador para as chaves (que são Strings)
        StringRedisSerializer keySerializer = new StringRedisSerializer();

        // Serializador para os valores (nossos objetos ShoppingCart), usando JSON
        Jackson2JsonRedisSerializer<ShoppingCart> valueSerializer = new Jackson2JsonRedisSerializer<>(ShoppingCart.class);

        // Configurando o contexto de serialização
        RedisSerializationContext.RedisSerializationContextBuilder<String, ShoppingCart> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);

        RedisSerializationContext<String, ShoppingCart> context = builder
                .value(valueSerializer)       // Para valores normais
                .hashValue(valueSerializer)   // Para valores dentro de um Hash
                .hashKey(keySerializer)       // Para chaves dentro de um Hash
                .build();

        // Criando e retornando o template com a configuração aplicada
        return new ReactiveRedisTemplate<>(factory, context);
    }

}
