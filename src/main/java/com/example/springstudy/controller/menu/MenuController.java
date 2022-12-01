package com.example.springstudy.controller.menu;

import com.example.springstudy.entity.menu.MenuEntity;
import com.example.springstudy.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    //#region - 조회

    /**
     * Menu List 조회
     *
     * @param mav
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView readMenuList(ModelAndView mav) {
        mav.setViewName("menu/list");
        // TODO: MenuEntity to MenuDTO
        //List<MenuDTO> menuList = menuService.readMenuList();
        List<MenuEntity> menuList = menuService.readMenuList();

        return mav;
    }

    //#endregion

    //#region - 생성

    // 메뉴 생성

    /**
     * Menu 생성 Page
     * @param mav
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public ModelAndView createMenuPage(ModelAndView mav) {
        mav.setViewName("menu/insert");

        return mav;
    }

    /**
     * Menu 생성(저장)
     * @param menuEntity
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createMenu(@RequestBody MenuEntity menuEntity) {
        String str = "";


        return str;
    }


    //#endregion

    //#region - 수정
    //#endregion

    //#region - 삭제
    //#endregion

}
