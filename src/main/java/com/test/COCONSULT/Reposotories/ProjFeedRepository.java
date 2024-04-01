package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.ProjFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjFeedRepository extends JpaRepository<ProjFeed, Long> {
}