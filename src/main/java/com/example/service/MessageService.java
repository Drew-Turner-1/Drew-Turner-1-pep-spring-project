package com.example.service;

import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.*;
import com.example.repository.*;

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
            Message newMessage = messageRepository.save(message);
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

    public List<Message> getAllUserMessages(int postingUserId){
        try{
            List<Message> allMessagesById = messageRepository.getAllUserMessages(postingUserId);
            return allMessagesById;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Message getMessageById(Integer messageIdOnly){
        try{
            Optional<Message> messageById = messageRepository.findById(messageIdOnly);
            if(messageById.isPresent()){
                return messageById.get();
            }
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int editMessageById(String messageText, int messageId){
        try{
            validateMessageExists(messageId);
            validateMessageLength(messageText);
            validateMessageNotEmpty(messageText);
            messageRepository.editMessageById(messageText, messageId);
            return 1;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    public int deleteMessageById(int messageIdOnly){
        try{
            validateMessageExists(messageIdOnly);
            int rowsAffected = messageRepository.deleteMessageById(messageIdOnly);
            return rowsAffected;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void validateNewMessage(Message message){

        if(Objects.isNull(message)){
            throw new IllegalArgumentException("Message can't be empty. ");
        }
        if(message.getMessageText().length() >= 255){
            throw new IllegalArgumentException("Message must be under 255 characters. ");
        }
        if(message.getMessageText().equals("")){
            throw new IllegalArgumentException("Message text can't be empty. ");
        }
        if(! accountRepository.findAccountByAccountId(message.getPostedBy()).getAccountId().equals(message.getPostedBy())){
            throw new IllegalArgumentException("User doesn't exist. ");
        }
    }


    public void validateMessageExists(int messageId){

        if(Objects.isNull(messageRepository.findMessageByMessageId(messageId))){
            throw new IllegalArgumentException("Message doesn't exist. ");
        }
    }

    public void validateMessageLength(String messageText){

        if(messageText.length() >= 255){
            throw new IllegalArgumentException("Message Can't be over 255 characters. ");
        }
    }
    public void validateMessageNotEmpty(String messageText){

        if(Objects.isNull(messageText) || messageText.equals("")){
            throw new IllegalArgumentException("Message can't be empty. ");
        }
    }
}
