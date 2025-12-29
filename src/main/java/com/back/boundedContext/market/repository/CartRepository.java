package com.back.boundedContext.market.repository;

import com.back.boundedContext.market.domain.Cart;
import com.back.boundedContext.market.domain.MarketMember;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Integer> {
    Optional<Cart> findByBuyer(MarketMember buyer);
}
