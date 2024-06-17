package com.jaemin.backboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaemin.backboard.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
    
}
