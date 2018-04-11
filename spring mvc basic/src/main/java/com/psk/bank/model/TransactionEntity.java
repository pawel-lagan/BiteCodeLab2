package com.psk.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

public class TransactionEntity implements Transaction {

    private Long id;
    private String dstAccountNumber;
    private BigDecimal value;
    private Boolean pending;
    private LocalDateTime creationTs;
    private LocalDateTime operationTs;
    @JsonTypeInfo(defaultImpl = AccountEntity.class, use = Id.NAME)
    private Account account;

    public static TransactionEntity newInstance(Long id, String dstAccountNumber, BigDecimal value, Boolean pending,
            LocalDateTime creationTs, LocalDateTime operationTs, Account account) {
        TransactionEntity entity = new TransactionEntity();
        entity.setAccount(account);
        entity.setCreationTs(creationTs);
        entity.setOperationTs(operationTs);
        entity.setPending(pending);
        entity.setId(id);
        entity.setDstAccountNumber(dstAccountNumber);
        entity.setValue(value);
        return entity;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getDstAccountNumber() {
        return dstAccountNumber;
    }

    public void setDstAccountNumber(String dstAccountNumber) {
        this.dstAccountNumber = dstAccountNumber;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public Boolean isPending() {
        return pending;
    }

    public void setPending(Boolean pending) {
        this.pending = pending;
    }

    @Override
    public LocalDateTime getCreationTs() {
        return creationTs;
    }

    public void setCreationTs(LocalDateTime creationTs) {
        this.creationTs = creationTs;
    }

    @Override
    public LocalDateTime getOperationTs() {
        return operationTs;
    }

    public void setOperationTs(LocalDateTime operationTs) {
        this.operationTs = operationTs;
    }

    @Override
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
