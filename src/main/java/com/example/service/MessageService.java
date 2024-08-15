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

public Message createMessage(Message message){
    try{
        validateNewMessage(message);
        Message newMessage = messageRepository.addMessage(message);
        return newMessage;
    }
    catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

public List<Message> getAllMessages(){
    try{
        List<Message> allMessages = messageRepository.getAllMessages();
        return allMessages;
    }
    catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

public List<Message> getAllUserMessages(Message postingUser){
    try{
        List<Message> allMessagesById = messageRepository.getAllUserMessages(postingUser);
        return allMessagesById;
    }
    catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

public Message getMessageById(Message messageIdOnly){
    try{
        Message messageById = messageRepository.getMessageById(messageIdOnly);
        return messageById;
    }
    catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

public Message editMessageById(Message messageIdOnly){
    try{
        validateMessageById(messageIdOnly);
        Message updatedMessage = messageRepository.editMessageById(messageIdOnly);
        return updatedMessage;
    }
    catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}


public Message deleteMessageById(Message messageIdOnly){
    try{
        Message deletedMessage = messageRepository.deleteMessageById(messageIdOnly);
        return deletedMessage;
    }
    catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

public void validateNewMessage(Message message){

    if(Objects.isNull(message)){
        throw new IllegalArgumentException("Message can't be empty. ");
    }
    if(message.getMessage_text().length() >= 255){
        throw new IllegalArgumentException("Message must be under 255 characters. ");
    }
    if(! accountRepository.getAllAccountIds().contains(message.getPosted_by())){
        throw new IllegalArgumentException("User doesn't exist. ");
    }
}

public void validateMessageById(Message message){

    if(Objects.isNull(message)){
        throw new IllegalArgumentException("Message can't be empty. ");
    }
    if(! messageRepository.getAllMessageIds(message).contains(message.getMessage_id())){
        throw new IllegalArgumentException("Message doesn't exist. ");
    }
    if(message.getMessage_text().length() >= 255){
        throw new IllegalArgumentException("Message must be under 255 characters. ");
    }
    if(! accountRepository.getAllAccountIds().contains(message.getPosted_by())){
        throw new IllegalArgumentException("User doesn't exist. ");
    }
}



}
