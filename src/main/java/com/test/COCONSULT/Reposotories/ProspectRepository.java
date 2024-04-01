package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Prospect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProspectRepository extends JpaRepository<Prospect,Long> {
}
