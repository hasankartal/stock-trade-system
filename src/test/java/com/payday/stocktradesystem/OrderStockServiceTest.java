package com.payday.stocktradesystem;

import com.payday.stocktradesystem.domain.orderstock.Orderstock;
import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.model.orderstock.OrderstockDto;
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

    public static final BigDecimal EXPECTED_CASH = new BigDecimal(120);
    public static final long EXPECTED_USER_ID = 30;
    public static final long EXPECTED_STOCK_LOT = 10;
    public static final String EXPECTED_STOCK_SYMBOL = "ACSEL";
    public static final String EXPECTED_ORDER_TYPE = "BUY";

    private OrderstockDto orderstockDto;

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

    @Test
    public void setAccountDto() {
        orderstockDto = new OrderstockDto();
        orderstockDto.setCash(new BigDecimal(120));
        orderstockDto.setOrderType("BUY");
        orderstockDto.setStockLot(10);
        orderstockDto.setUserId(30);
        orderstockDto.setStockSymbol("ACSEL");

        Assertions.assertEquals(EXPECTED_CASH, orderstockDto.getCash());
        Assertions.assertEquals(EXPECTED_ORDER_TYPE, orderstockDto.getOrderType());
        Assertions.assertEquals(EXPECTED_STOCK_LOT, orderstockDto.getStockLot());
        Assertions.assertEquals(EXPECTED_USER_ID, orderstockDto.getUserId());
        Assertions.assertEquals(EXPECTED_STOCK_SYMBOL, orderstockDto.getStockSymbol());
    }
}
