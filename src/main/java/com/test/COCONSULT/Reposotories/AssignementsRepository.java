package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Assignements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignementsRepository extends JpaRepository<Assignements, Long> {
}