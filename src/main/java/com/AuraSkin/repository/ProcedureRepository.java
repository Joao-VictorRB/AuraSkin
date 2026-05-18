package com.AuraSkin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.AuraSkin.entity.Procedure;

public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
    
}
