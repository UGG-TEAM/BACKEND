package com.example.template.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@Getter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "member")
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<CheckList> checkListList = new ArrayList<>();
}
