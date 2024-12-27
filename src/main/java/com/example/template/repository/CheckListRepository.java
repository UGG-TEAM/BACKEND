package com.example.template.repository;

import com.example.template.entity.CheckList;
import com.example.template.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckListRepository extends JpaRepository<CheckList, Long> {

    List<CheckList> findAllByMemberAndIsCheck(Member member, boolean isCheck);
}
