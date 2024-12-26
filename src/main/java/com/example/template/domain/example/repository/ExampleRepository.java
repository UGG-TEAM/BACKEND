package com.example.template.domain.example.repository;

import com.example.template.domain.example.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {
}
