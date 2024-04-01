package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Expanses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpansesRepository extends JpaRepository<Expanses, Long> {
}