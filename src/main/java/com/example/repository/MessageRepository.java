package com.example.repository;
import com.example.entity.*;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.transaction.annotation.*;
import org.springframework.stereotype.Repository;


public interface MessageRepository  extends JpaRepository<Message, Integer>{



    @Query("SELECT * FROM Message")
    List<Message> getAllMessages();


    @Query("SELECT FROM Message WHERE Message.postedBy = :accountId")
    List<Message> findMessageByAccountId(@Param("accountId") int accountId);

    @Modifying
    @Query("UPDATE Message SET Message.messageText = :messageText WHERE Message.messageId = :messageId")
    int updateMessageById(@Param("messageText") int messageText, @Param("messageId") int messageId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Message WHERE Message.messageId = :messageId")
    int deleteMessageById(@Param("messageId") int messageId);


}
