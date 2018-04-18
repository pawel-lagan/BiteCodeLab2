package com.psk.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface Transaction {
    Long getId();
    String getDstAccountNumber();
    BigDecimal getValue();
    Boolean isPending();
    LocalDateTime getCreationTs();
    LocalDateTime getOperationTs();
    Account getAccount();
}
