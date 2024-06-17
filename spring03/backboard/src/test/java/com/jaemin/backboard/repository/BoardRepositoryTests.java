package com.jaemin.backboard.repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jaemin.backboard.entity.Board;

@SpringBootTest
public class BoardRepositoryTests {
    // JUnit 테스트
    @Autowired
    private BoardRepository boardRepository;
    
    @Test
    void testInsertBoard(){
        Board board1 = new Board();
        board1.setTitle("첫번째테스트");
        board1.setContent("ㅇㅇㅇㅇ");
        board1.setCreateDate(LocalDateTime.now());
        this.boardRepository.save(board1);

        // builder를 사용한 객체 생성방식
        Board board2 = Board.builder().title("두번째 테스트").content("ㄴㄴㄴㄴ")
                                      .createDate(LocalDateTime.now()).build();
        this.boardRepository.save(board2);
        System.out.println("테스트완료");
    }
}
