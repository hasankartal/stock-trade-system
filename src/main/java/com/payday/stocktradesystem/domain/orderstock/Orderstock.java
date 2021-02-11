package com.payday.stocktradesystem.domain.orderstock;

import com.payday.stocktradesystem.domain.user.User;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Orderstock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="orderstock_id")
    private long ordersId;

    private BigDecimal cash;

    private String stackSymbol;

    private long stackLot;

    private String orderType;

    private boolean active;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
}