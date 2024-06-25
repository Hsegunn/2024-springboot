package com.jaemin.backboard.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;   // 복합쿼리 생성용
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jaemin.backboard.common.NotFoundException;
import com.jaemin.backboard.entity.Board;
import com.jaemin.backboard.entity.Category;
import com.jaemin.backboard.entity.Member;
import com.jaemin.backboard.entity.Reply;
import com.jaemin.backboard.repository.BoardRepository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {        
    private final BoardRepository boardRepository;

    public List<Board> getList() {
        return this.boardRepository.findAll();
    }

    // 페이징 되는 리스트 메서드
    public Page<Board> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); // pageSize를 동적으로도 변경할 수 있음.나중에...
        return this.boardRepository.findAll(pageable);
    }

    // 24.06.24 검색추가 메서드
    public Page<Board> getList(int page, String keyword) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); // pageSize를 동적으로도 변경할 수 있음.나중에...

        // Specification<Board> spec = searchBoard(keyword);    // Specification 인터페이스로 쿼리 생성로직을 만듬
        // return this.boardRepository.findAll(spec, pageable);
        return this.boardRepository.findAllByKeyword(keyword, pageable);
    }

    // 24.06.25 카테고리 추가
    public Page<Board> getList(int page, String keyword, Category category) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); // pageSize를 동적으로도 변경할 수 있음.나중에...

        Specification<Board> spec = searchBoard(keyword, category.getId());    // Specification 인터페이스로 쿼리 생성로직을 만듬
        return this.boardRepository.findAll(spec, pageable);
        // return this.boardRepository.findAllByKeyword(keyword, pageable);
    }

    public Board getBoard(Long bno) {
        Optional<Board> board = this.boardRepository.findById(bno);
        if (board.isPresent()) { // 데이터가 존재하면
            return board.get();
        } else {
            throw new NotFoundException("board not found");
        }
    }

    // 24.06.18. setBoard 작성(hugo83)
    // 24.06.21. Member 추가
    public void setBoard(String title, String content, Member writer) {
        // 빌더로 생성한 객체
        Board board = Board.builder().title(title).content(content)
                        .createDate(LocalDateTime.now()).build();
        
        board.setWriter(writer);
        this.boardRepository.save(board); // PK가 없으면 INSERT
    }

    // 24.06.25. category 저장 추가
    public void setBoard(String title, String content, Member writer, Category category) {
        // 빌더로 생성한 객체
        Board board = Board.builder().title(title).content(content)
                        .createDate(LocalDateTime.now()).build();
        board.setCategory(category);
        board.setWriter(writer);
        
        this.boardRepository.save(board); // PK가 없으면 INSERT
    }

    // 24.06.24. modBoard 추가작성
    public void modBoard(Board board, String title, String content) {
        board.setTitle(title);
        board.setContent(content);
        board.setModifyDate(LocalDateTime.now()); // 수정된 일시 추가하려면

        this.boardRepository.save(board); // PK가 있으면 UPDATE
    }
    public void remBoard(Board board){
        boardRepository.delete(board);
    }

    // 검색 쿼리대신 검색기능
    public Specification<Board> searchBoard(String keyword){
        return new Specification<Board>() {
            private static final long serialVersionID = 1L; // 필요한 값이라서 추가

            @SuppressWarnings("null")
            @Override
            public Predicate toPredicate(Root<Board> b, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // query를 JPA로 생성
                query.distinct(true);   // 중복제거
                Join<Board, Reply> r = b.join("replyList", JoinType.LEFT);

                return cb.or(cb.like(b.get("title"), "%" + keyword + "%"),  // 게시물 제목에서 검색
                             cb.like(b.get("content"), "%" + keyword + "%"),    // 게시물 내용에서 검색
                             cb.like(r.get("content"), "%" + keyword + "%")     // 댓글 내용에서 검색
                             );
            }
        };
    }

        // 카테고리 추가된 메서드
        public Specification<Board> searchBoard(String keyword, Integer cateId){
            return new Specification<Board>() {
                private static final long serialVersionID = 1L; // 필요한 값이라서 추가
    
                @SuppressWarnings("null")
                @Override
                public Predicate toPredicate(Root<Board> b, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    // query를 JPA로 생성
                    query.distinct(true);   // 중복제거
                    Join<Board, Reply> r = b.join("replyList", JoinType.LEFT);
    
                    return cb.and(cb.equal(b.get("category").get("id"), cateId),
                                  cb.or(cb.like(b.get("title"), "%" + keyword + "%"),  // 게시물 제목에서 검색
                                        cb.like(b.get("content"), "%" + keyword + "%"),    // 게시물 내용에서 검색
                                        cb.like(r.get("content"), "%" + keyword + "%"))     // 댓글 내용에서 검색
                                );
                }
            };
        }
}