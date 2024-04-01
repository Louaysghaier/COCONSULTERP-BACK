package com.test.COCONSULT.Reposotories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.test.COCONSULT.Entity.GroupChat;

@Repository
public interface GroupChatRepository extends JpaRepository<GroupChat, Long> {
    // You can define custom query methods here if needed
}
