package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
// import Model.Account;
// import Model.Message;
// import Service.AccountService;
// import Service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    @GetMapping("/messages")
    ResponseEntity getAllMessages(){
        List<Message> allMessages = new ArrayList<Message>(messageService.getAllMessages());
        return ResponseEntity.status(200).body(allMessages);
    }

    @GetMapping("/messages/{message_id}")
    ResponseEntity getMessagesById(@PathVariable int messageIdOnly){
        List<Message> messagesById = new ArrayList<Message>(messageService.getMessageById(messageIdOnly));
        return ResponseEntity.status(200).body(messagesById);
    }

    @GetMapping("accounts/{account_id}")
    ResponseEntity loginAccount(@PathVariable int accountId){
        Account validAccount = accountService.loginAccount(accountId);
        if((validAccount != null)){
            ResponseEntity.status(200).body(validAccount);
        }
        else{
            ResponseEntity.status(401);
        }    
    }

    @PostMapping("/register")
    ResponseEntity addAccount(@PathVariable Account account){
        Account newAccount = accountService.addAccount(account);
        if((newAccount != null)){
            ResponseEntity.status(200).body(newAccount);
        }
        else{
            ResponseEntity.status(400);
        }       
    }

    @PostMapping("/login")
    ResponseEntity loginAccount(@PathVariable Account account){
        Account validAccount = accountService.loginAccount(account);
        if((validAccount != null)){
            ResponseEntity.status(200).body(validAccount);
        }
        else{
            ResponseEntity.status(401);
        }      
    }

    @PostMapping("/messages")
    ResponseEntity createMessage(@PathVariable Message message){
        Message newMessage = messageService.createMessage(message);
        if((newMessage != null)){
            ResponseEntity.status(200).body(newMessage);
        }
        else{
            ResponseEntity.status(400);
        }         
    }

    @PatchMapping("/messages/{message_id}")
    ResponseEntity editMessageById(@PathVariable int messageId){
        Message updatedMessage = messageService.editMessageById(messageId);
        return ResponseEntity.status(200).body(updatedMessage);
    }

    @DeleteMapping("/messages/{message_id}")
    ResponseEntity deleteMessageById(@PathVariable int messageId){
        Message deletedMessage = messageService.deleteMessageById(messageId);
        return ResponseEntity.status(200).body(deletedMessage);
    }

}
