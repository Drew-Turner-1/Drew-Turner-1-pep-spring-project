package com.example.controller;

import org.springframework.http.ResponseEntity;
import com.example.entity.*;
//import com.example.entity.Message;
import com.example.service.*;
//import com.example.service.MessageService;
import org.springframework.web.bind.annotation.*;
import java.util.*;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
 @ResponseBody
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;    
    }

    @GetMapping("/messages")
    ResponseEntity getAllMessages(){
        List<Message> allMessages = new ArrayList<Message>(messageService.getAllMessages());
        return ResponseEntity.status(200).body(allMessages);
    }

    @GetMapping("/messages/{message_id}")
    ResponseEntity getMessagesById(@PathVariable ("message_id") int messageIdOnly){
        Message messagesById = messageService.getMessageById(messageIdOnly);
        return ResponseEntity.status(200).body(messagesById);
    }

    @GetMapping("accounts/{account_id}")
    ResponseEntity getMessagesById(@PathVariable ("account_id") int accountIdOnly){
        Message messagesByAccountId = messageService.getMessageByAccountId(accountIdOnly);
        return ResponseEntity.status(200).body(messagesByAccountId);
    }


    @GetMapping("accounts/{account_id}")
    ResponseEntity loginAccount(@PathVariable ("account_id") int accountId){
        
        Account accountIdOnly = new Account();
        accountIdOnly.setAccountId(accountId);
        Account validAccount = accountService.loginAccount(accountIdOnly);
        if((validAccount != null)){
            return ResponseEntity.status(200).body(validAccount);
        }
        else{
            return ResponseEntity.status(401).build();
        }    
    }

    @PostMapping("/register")
    ResponseEntity addAccount(@RequestBody Account account){
        Account newAccount = accountService.addAccount(account);
        if((newAccount != null)){
            return ResponseEntity.status(200).body(newAccount);
        }
        else{
            return ResponseEntity.status(409).build();
        }       
    }

    @PostMapping("/login")
    ResponseEntity loginAccount(@RequestBody Account account){
        Account validAccount = accountService.loginAccount(account);
        if((validAccount != null)){
            return ResponseEntity.status(200).body(validAccount);
        }
        else{
            return ResponseEntity.status(401).build();
        }      
    }

    @PostMapping("/messages")
    ResponseEntity createMessage(@RequestBody Message message){
        Message newMessage = messageService.createMessage(message);
        if((newMessage != null)){
            return ResponseEntity.status(200).body(newMessage);
        }
        else{
            return ResponseEntity.status(400).build();
        }         
    }

    @PatchMapping("/messages/{message_id}")
    ResponseEntity editMessageById(@PathVariable ("message_id") int messageId){
        Message updatedMessage = messageService.editMessageById(messageId);
        return ResponseEntity.status(200).body(updatedMessage);
    }

    @DeleteMapping("/messages/{message_id}")
    ResponseEntity deleteMessageById(@PathVariable ("message_id") int messageId){
        Message deletedMessage = messageService.deleteMessageById(messageId);
        return ResponseEntity.status(200).body(deletedMessage);
    }

}
