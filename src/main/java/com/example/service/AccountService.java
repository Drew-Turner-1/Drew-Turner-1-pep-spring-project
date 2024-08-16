package com.example.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.*;
import com.example.repository.*;

@Service
public class AccountService {

    AccountRepository accountRepository;
    MessageRepository messageRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, MessageRepository messageRepository){
        this.accountRepository = accountRepository;
        this.messageRepository = messageRepository;
    }

    public Account addAccount(Account account){
        
        try{
            validateAccount(account);
            Account newAccount = accountRepository.save(account);
            return newAccount;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private void validateAccount(Account account) {
        try{
            if (Objects.isNull(account)) {
                throw new IllegalArgumentException("A Username and Password are required.");
            }
            if (Objects.isNull(account.getUsername()) || account.getUsername().trim().isEmpty()) {
                throw new IllegalArgumentException("A Username is required.");
            }
            if (Objects.isNull(account.getPassword()) || account.getPassword().trim().isEmpty()) {
                throw new IllegalArgumentException("A Password is required.");
            }
            if (account.getPassword().length() < 4) {
                throw new IllegalArgumentException("A Password must be at least four characters long.");
            }
            if(accountRepository.findAccountByUsername(account.getUsername().trim()) != null){
                throw new IllegalArgumentException("This Username is already taken :(");
            }
        }

        catch(IllegalArgumentException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Login failed :/ ");
        }
    }

    public Account loginAccount(Account account){
        try{
            validateAccountForLogin(account);
                Account validAccount = accountRepository.findAccountByUsername(account.getUsername());
                return validAccount;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    

    private void validateAccountForLogin(Account account) {
        try{
            if (Objects.isNull(account)) {
                throw new IllegalArgumentException("A Username and Password are required.");
            }
            if (Objects.isNull(account.getUsername()) || account.getUsername().trim().isEmpty()) {
                throw new IllegalArgumentException("A Username is required.");
            }
            if (Objects.isNull(account.getPassword()) || account.getPassword().trim().isEmpty()) {
                throw new IllegalArgumentException("A Password is required.");
            }
            if (accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword()) == null) {
                throw new IllegalArgumentException("A Password is required.");
            }
        }
        catch(IllegalArgumentException e){
            e.printStackTrace();
            throw new IllegalArgumentException("Login failed :/ ");
        }
    }
}
