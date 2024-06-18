package com.jaemin.backboard.service;

import java.util.List;

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
}
