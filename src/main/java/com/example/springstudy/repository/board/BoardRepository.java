package com.example.springstudy.repository.board;

import com.example.springstudy.entity.board.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    //#region - 조회

    /**
     * 게시글 목록 조회
     * @return
     */
    @Override
    List<BoardEntity> findAll();

    /**
     * 게시글 조회 by id
     * @param integer must not be {@literal null}.
     * @return
     */
    @Override
    BoardEntity getReferenceById(Integer integer);

    @Override
    Optional<BoardEntity> findById(Integer integer);
//#endregion


    //#region - 저장

    /**
     * 게시글 저장 / 수정
     * @param entity entity to be saved. Must not be {@literal null}.
     * @return
     * @param <S>
     */
    @Override
    <S extends BoardEntity> S saveAndFlush(S entity);

    //#endregion

    //#region - 삭제

    /**
     * 게시글 삭제
     * @param integer must not be {@literal null}.
     */
    @Override
    void deleteById(Integer integer);

    //#endrigion

}
