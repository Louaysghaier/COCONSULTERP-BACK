package com.test.COCONSULT.Reposotories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.test.COCONSULT.Entity.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

}
