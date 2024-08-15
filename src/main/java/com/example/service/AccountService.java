package com.example.service;

import java.util.*;
import javax.transaction.Transactional;
import org.springframework.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.*;
//import com.example.entity.Message;
import com.example.repository.*;
//import com.example.service.MessageService;

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
            Account newAccount = accountRepository.addAccount(account);
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
            if(accountRepository.getAllAccountUsernames().contains(account.getUsername().trim())){
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
                Account validAccount = accountRepository.LoginAccount(account);
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
        }
        catch(IllegalArgumentException e){
            e.printStackTrace();
            throw new IllegalArgumentException("Login failed :/ ");
        }
    }
}
