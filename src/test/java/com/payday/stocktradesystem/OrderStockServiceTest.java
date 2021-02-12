package com.payday.stocktradesystem;


import com.payday.stocktradesystem.domain.orderstock.Orderstock;
import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.repository.order.OrderstockRepository;
import com.payday.stocktradesystem.service.orderstock.OrderstockService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class OrderStockServiceTest {
    @InjectMocks
    OrderstockService orderstockService;

    @Mock
    private OrderstockRepository orderstockRepository;

    @Test
    public void sellStock() {
        User user = new User();
        user.setEmail("hasankartal18@gmail.com");
        user.setPassword("mugiwara");

        Orderstock orderstock = new Orderstock();
        orderstock.setUser(user);
        orderstock.setStockSymbol("ACSEL");
        Mockito.when(orderstockService.sellStock("ACSEL", "SELL",new BigDecimal("10")))
                .thenReturn(Collections.singletonList(orderstock));
        List<Orderstock> orderstockListTest = orderstockService.sellStock("ACSEL", "SELL",new BigDecimal("10"));

        Assertions.assertEquals(orderstockListTest.size(), 1);

    }

    @Test
    public void buyStock() {
        User user = new User();
        user.setEmail("hasankartal18@gmail.com");
        user.setPassword("mugiwara");

        Orderstock orderstock = new Orderstock();
        orderstock.setUser(user);
        orderstock.setStockSymbol("ACSEL");
        Mockito.when(orderstockService.buyStock("ACSEL", "SELL",new BigDecimal("10")))
                .thenReturn(Collections.singletonList(orderstock));
        List<Orderstock> orderstockListTest = orderstockService.buyStock("ACSEL", "SELL",new BigDecimal("10"));

        Assertions.assertEquals(orderstockListTest.size(), 1);

    }
}
