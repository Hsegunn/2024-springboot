package com.jaemin.backboard.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// 게시판 보드 테이블 엔티티

@Getter
@Setter
// 테이블화
@Entity 
// 객체 생성을 간략화
@Builder 
// 파라미터 없는 기본생성지 자동생성
@NoArgsConstructor  
// 맴버변수 모두를 파라미터로 가지는 생성자 자동생성
@AllArgsConstructor 
public class Board {
    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)     // 나중에 Oracle로 변경
    private Long bno;
    // 글 제목
    @Column(name = "title" , length = 250)
    private String title;
    // 글 내용
    @Column(name = "content" , length = 4000)
    private String content;
    // 글 생성일
    @CreatedDate
    @Column(name = "createDate" , updatable = false)
    private LocalDateTime createDate;

    // 중요
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Reply> replyList;
}
