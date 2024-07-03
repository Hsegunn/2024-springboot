package com.jaemin.backboard.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private Long num;   // 게시글 번호  2024.07.03 추가
    private Long bno;
    private String title;   // 글제목
    private String content;     // 글내용
    private LocalDateTime createDate;   // 글생성일
    private LocalDateTime modifyDate;
    private Integer hit;
    private String writer;

    private List<ReplyDto> replyList;

}
