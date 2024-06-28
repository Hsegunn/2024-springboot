package com.jaemin.backboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaemin.backboard.entity.Reset;

public interface ResetRepository extends JpaRepository<Reset, Integer> {
    
    Optional<Reset> findByUuid(String uuid);
}
