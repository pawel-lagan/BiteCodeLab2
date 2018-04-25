package com.psk.bank.resources;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psk.bank.model.Account;
import com.psk.bank.repository.AccountRepository;

@RequestMapping(value = "/accounts")
@RestController
public class AccountsResource {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/{accountNumber}")
    public Account getAccount(@PathVariable String accountNumber) {
        return accountRepository.findOne(accountNumber);
    }

    @GetMapping("/added-since/{since}")
    public String showAddedSince(@PathVariable("since") LocalDate since) {
        return "added-since " + since.toString();
    }

    @RequestMapping("/added-since-ts/{since}")
    public String showAddedSinceTS(@PathVariable("since") LocalDateTime since) {
        return "added-since-ts " + since.toString();
    }


    @Component
    public static class StringToLocalDateConverter implements Converter<String, LocalDate> {

        @Override
        public LocalDate convert(String source) {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyyMMdd"));
        }
    }

    @Component
    public static class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

        @Override
        public LocalDateTime convert(String source) {
            return LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"));
        }
    }

}
