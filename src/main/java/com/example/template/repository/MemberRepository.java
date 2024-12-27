package com.example.template.repository;

import com.example.template.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Long, Member> {
    Optional<Object> findById(Long memberId);
}
