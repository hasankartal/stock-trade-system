package com.payday.stocktradesystem.service.orderstock;

import com.payday.stocktradesystem.domain.orderstock.Orderstock;
import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.model.orderstock.OrderstockDto;

import java.math.BigDecimal;
import java.util.List;

public interface OrderstockService {
    List<Orderstock> buyStock(String stockSymbol, String orderType, BigDecimal cash);
    List<Orderstock> sellStock(String stockSymbol, String orderType, BigDecimal cash);
    Orderstock updateOrderstock(Orderstock orderstock);
    Orderstock orderstock(OrderstockDto orderstockDto, User user);
}
