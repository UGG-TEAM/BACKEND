package com.example.template.repository;

import com.example.template.entity.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    @Query("SELECT p FROM Program p WHERE p.postDate IS NOT NULL AND p.postDate <> 'NULL' ORDER BY p.postDate DESC")
    Page<Program> findAllByOrderByPostDateDesc(Pageable pageable);
}
