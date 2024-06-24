package com.mtr.ApiGateway_springboot.repository;

import com.mtr.ApiGateway_springboot.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderRepo extends JpaRepository<UserOrder, Integer> {
}
