package com.example.springstudy.service.menu;

import com.example.springstudy.entity.menu.MenuEntity;
import com.example.springstudy.repository.menu.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // TODO: Optional? Builder?와 관련있나?
    public void readMenuById(Integer menuId) {
        Optional<MenuEntity> menuItem = this.menuRepository.findById(menuId);
        MenuEntity resultMenuEntity = menuItem.get();

//        MenuDTO menuDTO = new MenuDTO.MenuDTOBuilder().builder(
//                resultMenuEntity.getMenuId(),
//                resultMenuEntity.getParentMenuId(),
//                resultMenuEntity.getTopParentMenuId(),
//                resultMenuEntity.getDepth(),
//                resultMenuEntity.getMenuName(),
//                resultMenuEntity.getMenuType(),
//                resultMenuEntity.getLink(),
//                resultMenuEntity.getLinkOpenType(),
//                resultMenuEntity.getPcExposureTf(),
//                resultMenuEntity.getMobileExposureTf(),
//                resultMenuEntity.getExposureTf(),
//                resultMenuEntity.getSort(),
//                resultMenuEntity.getDescription()
//        );
//        // TODO: menuEntity to DTO?
//        return ;
    }

    //#endregion

    //#region - 수정

    //#endregion

    //#region - 삭제

    //#endregion

}
