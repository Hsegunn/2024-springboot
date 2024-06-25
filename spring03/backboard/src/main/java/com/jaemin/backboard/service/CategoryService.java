package com.jaemin.backboard.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jaemin.backboard.common.NotFoundException;
import com.jaemin.backboard.entity.Category;
import com.jaemin.backboard.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;    // bean 으로 생성

    // 카테고리를 생성하는 메서드
    public Category setCategory(String title){
        Category cate = new Category();
        cate.setTitle(title);
        cate.setCreateDate(LocalDateTime.now());

        Category category = this.categoryRepository.save(cate);
        return category;
    }

    // free, QnA
    // 카테고리 가져오는 메서드
    public Category getCategory(String title){
        Optional<Category> cate = this.categoryRepository.findByTitle(title);

        if (cate.isEmpty()) {   // free 나 QnA 타이틀의 카테고리 데이터가 없으면
            cate = Optional.ofNullable(setCategory(title));     // 테이블에 해당 카테고리를 생성
        }

        if (cate.isPresent()) {
            return cate.get();      // Category 리턴
        }else {
            throw new NotFoundException("Category Not Found");
        }
    }
}
