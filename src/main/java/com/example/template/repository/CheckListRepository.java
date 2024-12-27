package com.example.template.repository;

import com.example.template.entity.CheckList;
import com.example.template.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckListRepository extends JpaRepository<Long, CheckList> {

    List<CheckList> findAllByMemberAndIsCheck(Member member, boolean isCheck);
}
