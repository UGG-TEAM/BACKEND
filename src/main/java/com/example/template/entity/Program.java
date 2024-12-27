package com.example.template.entity;

import com.example.template.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.Host;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "program")
@AllArgsConstructor
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String title;

    private String postDate;

    private String isOnce;

    private String region;

    @Column(length = 1000)  // 컬럼 길이를 1000으로 수정
    private String category;

    @Column(length = 1000)  // 컬럼 길이를 1000으로 수정
    private String targetType;

    @Column(length = 1000)  // 컬럼 길이를 1000으로 수정
    private String programType;

    @Column(length = 1000)  // 컬럼 길이를 1000으로 수정
    private String hostType;

    @Column(length = 1000)  // 컬럼 길이를 1000으로 수정
    private String target;

    @Column(length = 1000)  // 컬럼 길이를 1000으로 수정
    private String content;

    private String minAge;

    private String maxAge;

    @Column(length = 1000)  // 컬럼 길이를 1000으로 수정
    private String cost;

    private String location;

    @Column(length = 1000)  // 컬럼 길이를 1000으로 수정
    private String homePage;

    private String host;

    private String manager;

    private String managerEmail;

    private String managerPhoneNumber;

    private String sponsor;

    private String startDate;

    private String endDate;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<CheckList> checkListList = new ArrayList<>();

}
