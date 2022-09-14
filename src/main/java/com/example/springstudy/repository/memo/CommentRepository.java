package com.example.springstudy.repository.memo;

import com.example.springstudy.entity.memo.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    // TODO: 게시글 번호 Column으로 조회해오는 로직
    // TODO: JpaRepository 에서 findby컬럼명 하면 그 컬럼 명으로 다 조회?
    // TODO: 다음주 발표: prefix/postfix로 써서 JpaRepository 활용하는법 발표
    List<CommentEntity> findByMemoSeq(Integer memoSeq);

    // TODO: countBy로 댓글 개수 가져와서 list, detail에서 댓글 수량 표시해주기

    @Override
    <S extends CommentEntity> S saveAndFlush(S entity);
}
