package com.example.repository;
import com.example.entity.*;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.transaction.annotation.*;
//import org.springframework.stereotype.Repository;


public interface MessageRepository  extends JpaRepository<Message, Integer>{

    Message findMessageByMessageId(int messageId);

    @Query(value = "SELECT * FROM Message", nativeQuery = true)
    List<Message> getAllMessages();


    @Query(value = "SELECT * FROM Message WHERE Message.postedBy = :accountId", nativeQuery = true)
    List<Message> getAllUserMessages(@Param("accountId") int accountId);
    

    @Modifying
    @Query(value = "INSERT INTO Message (postedBy, messageText, timePostedEpoch) VALUES (messageText, )", nativeQuery = true)
    Message addMessage(@Param("messageText") String messageText);
    

    @Modifying
    @Query(value = "UPDATE Message SET Message.messageText = :messageText WHERE Message.messageId = :messageId", nativeQuery = true)
    int editMessageById(@Param("messageText") String messageText, @Param("messageId") int messageIdOnly);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Message WHERE Message.messageId = :messageId", nativeQuery = true)
    int deleteMessageById(@Param("messageId") int messageId);


}
