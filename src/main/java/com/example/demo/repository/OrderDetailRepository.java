package com.example.demo.repository;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query("""
                SELECT od FROM OrderDetail od
                JOIN FETCH od.order o
                JOIN FETCH od.product p
                WHERE o.user.id = :userId AND od.order.status = 0
            """)
    List<OrderDetail> getCart(@Param("userId") Integer userId);

    @Query("""
        SELECT od FROM OrderDetail od
        JOIN FETCH od.order o
        JOIN FETCH od.product p
        WHERE o.user.id = :userId
    """)
    List<OrderDetail> findOrderDetailByUserId(Integer userId);

    OrderDetail findOrderDetailByOrderIdAndProductId(Integer orderId, Integer productId);
}
