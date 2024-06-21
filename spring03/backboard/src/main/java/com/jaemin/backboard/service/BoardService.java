package com.jaemin.backboard.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jaemin.backboard.common.NotFoundException;
import com.jaemin.backboard.entity.Board;
import com.jaemin.backboard.entity.Member;
import com.jaemin.backboard.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> getList(){
        return this.boardRepository.findAll();
    }

    // 페이징 되는 리스트 메서드
    public Page<Board> getList(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.boardRepository.findAll(pageable);
    }

    public Board getBoard(Long bno) {
        Optional<Board> board = this.boardRepository.findById(bno);
        if(board.isPresent()){      // 데이터가 존재하면
            return board.get();
        }else{
            throw new NotFoundException("board not found");
        }
    }
    
    public void setBoard(String title, String content, Member writer){
        // 빌더로 생성한 객체
        Board board = Board.builder().title(title).content(content).createDate(LocalDateTime.now()).build();
        board.setWriter(writer);
        this.boardRepository.save(board);

    }
}
