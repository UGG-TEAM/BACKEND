package com.example.template.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.MonthDay;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "checkList")
@AllArgsConstructor
public class CheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private Program program;

    @ColumnDefault("False")
    private boolean isCheck;

    private MonthDay finishDate;
}
