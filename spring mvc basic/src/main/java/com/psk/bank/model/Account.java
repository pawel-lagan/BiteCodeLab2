package com.psk.bank.model;

import java.time.LocalDateTime;

public interface Account {
    String getAccountNumber();
    String getFirstName();
    String getLastName();
    Boolean isActive();
    
    // po odznaczeniu ten format będzie używany przez Jacksona
   // @JsonFormat(pattern="yyyy-MM-dd") 
    LocalDateTime getCreationTs();
}
