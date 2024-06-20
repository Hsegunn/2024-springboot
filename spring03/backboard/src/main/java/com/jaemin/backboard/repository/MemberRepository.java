package com.jaemin.backboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jaemin.backboard.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
    
}
