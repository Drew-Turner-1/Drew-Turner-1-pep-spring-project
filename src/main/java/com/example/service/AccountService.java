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

public class AccountService {

    AccountRepository accountRepository;
    MessageRepository messageRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, MessageRepository messageRepository){
        this.accountRepository = accountRepository;
        this.messageRepository = messageRepository;
    }

    public Account addAccount(Account account){
        return accountReopsitory.addAccount(account);
    }
}
