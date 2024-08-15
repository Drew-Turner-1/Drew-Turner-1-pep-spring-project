package com.example.repository;
import com.example.entity.*;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;


public interface AccountRepository extends JpaRepository<Account, Integer>{
Account findAccountByUsername(String username);
Account findAccountByUsernameAndPassword(String username, String password);

@Modifying
@Query("INSERT INTO account (username, password) VALUES (?1,?2)")
Account addAccount(@Param("username") String username, @Param ("password") String passowrd);

}
