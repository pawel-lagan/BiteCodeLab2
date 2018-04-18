package com.psk.bank.model;

import java.time.LocalDateTime;

public interface Account {
    String getAccountNumber();
    String getFirstName();
    String getLastName();
    Boolean isActive();
    
    //@JsonFormat(pattern="yyyy-MM-dd") 
    LocalDateTime getCreationTs();
}
