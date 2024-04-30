package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Meetings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingsRepository extends JpaRepository<Meetings,Long> {
    List<Meetings> findByProjetsIdProjet(Long projectId);
    //List<Meetings> findByQuoteId(Long quoteId);


}