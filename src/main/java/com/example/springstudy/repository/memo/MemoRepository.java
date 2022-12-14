package com.example.springstudy.repository.memo;

import com.example.springstudy.entity.memo.MemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoRepository extends JpaRepository<MemoEntity, Integer> {

    @Override
    List<MemoEntity> findAll();

    @Override
    <S extends MemoEntity> S saveAndFlush(S entity);
}
