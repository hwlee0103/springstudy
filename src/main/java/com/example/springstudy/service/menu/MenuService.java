package com.example.springstudy.service.menu;

import com.example.springstudy.entity.menu.MenuEntity;
import com.example.springstudy.repository.menu.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }
    //#region - 저장

    //#endregion

    //#region - 조회

    public List<MenuEntity> readMenuList() {
        return menuRepository.findAll();
    }
    //#endregion

    //#region - 수정

    //#endregion

    //#region - 삭제

    //#endregion

}
