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
@Transactional
public class MessageService {

AccountRepository accountRepository;
MessageRepository messageRepository;

@Autowired
public MessageService(AccountRepository accountRepository, MessageRepository messageRepository){
this.accountRepository = accountRepository;
this.messageRepository = messageRepository;
}

}
