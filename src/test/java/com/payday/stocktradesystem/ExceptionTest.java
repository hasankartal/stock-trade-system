package com.payday.stocktradesystem;

import com.payday.stocktradesystem.exception.DataIntegrityViolationDbException;
import com.payday.stocktradesystem.model.stock.StockResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ExceptionTest {
    public static final String EXPECTED_MESSAGE = "Couldn't create";
    private DataIntegrityViolationDbException dataIntegrityViolationDbException;

    @Test
    public void setStockResponseDto() {
        dataIntegrityViolationDbException = new DataIntegrityViolationDbException("Couldn't create");

        Assertions.assertEquals(EXPECTED_MESSAGE, dataIntegrityViolationDbException.getMessage());
    }
}
