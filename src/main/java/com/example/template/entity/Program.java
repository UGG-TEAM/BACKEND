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

    private LocalDate postDate;

    private boolean isOnce;

    @Enumerated(EnumType.STRING)
    private Region region;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private TargetType targetType;

    @Enumerated(EnumType.STRING)
    private ProgramType programType;

    @Enumerated(EnumType.STRING)
    private HostType hostType;

    private String target;

    private String content;

    private int minAge;

    private int maxAge;

    private int cost;

    private String location;

    private String homePage;

    private String host;

    private String manager;

    private String managerEmail;

    private String managerPhoneNumber;

    private String sponsor;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<CheckList> checkListList = new ArrayList<>();

}
