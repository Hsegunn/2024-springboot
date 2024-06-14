package com.jaemin.spring02.domain;

import java.time.LocalDateTime;

import lombok.Data;
// import lombok.Getter;
// import lombok.Setter;
// import lombok.ToString;

// Getter, Setter, ToString등등 쓰거면 @Data를 쓰는게 편함
@Data

// @Getter
// @Setter
// @ToString
public class Todo {
    private int tno;
    private String title;
    private LocalDateTime duedate; 
    private String writer; 
    private int isdone; 

}

