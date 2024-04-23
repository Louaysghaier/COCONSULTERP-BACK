package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Projets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetsRepository extends JpaRepository<Projets, Long> {

}