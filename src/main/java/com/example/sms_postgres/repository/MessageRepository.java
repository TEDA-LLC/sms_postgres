package com.example.sms_postgres.repository;

import com.example.sms_postgres.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByFlag(int flag);
}
