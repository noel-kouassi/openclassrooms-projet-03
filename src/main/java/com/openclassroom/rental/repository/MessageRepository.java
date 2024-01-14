package com.openclassroom.rental.repository;

import com.openclassroom.rental.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
