package com.example.springstudy.repository.menu;

import com.example.springstudy.entity.menu.MenuEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {

    //#region - 조회
    @Override
    List<MenuEntity> findAll(Sort sort);


    //Optional<MenuDTO> findById(String menuId);

    //#endregion

    @Override
    <S extends MenuEntity> S saveAndFlush(S entity);

    @Override
    void delete(MenuEntity entity);
}
