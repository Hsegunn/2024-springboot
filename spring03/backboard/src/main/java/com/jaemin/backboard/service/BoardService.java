package com.jaemin.backboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jaemin.backboard.entity.Board;
import com.jaemin.backboard.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> getList(){
        return this.boardRepository.findAll();
    }

    public Board getBoard(Long bno) throws Exception{
        Optional<Board> board = this.boardRepository.findById(bno);
        if(board.isPresent()){      // 데이터가 존재하면
            return board.get();
        }else{
            throw new Exception("board not found");
        }
    }
}
