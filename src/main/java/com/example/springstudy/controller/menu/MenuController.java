package com.example.springstudy.controller.menu;

import com.example.springstudy.entity.menu.;
import com.example.springstudy.model.menu.MenuDTO;
import com.example.springstudy.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        // TODO:  to MenuDTO
        //List<MenuDTO> menuList = menuService.readMenuList();
        List<MenuEntity> menuList = menuService.readMenuList();

        return mav;
    }

    /**
     * 메뉴 상세 페이지
     *
     * @param mav
     * @param menuId
     * @return
     */
    @GetMapping(value = "/detail/{menuId}")
    @ResponseBody
    public ModelAndView readMenuDetail(ModelAndView mav, @PathVariable(name="menuId") String menuId) {
        mav.setViewName("menu/detail");
        return mav;
    }

    @PostMapping(value = "/detaildata")
    @ResponseBody
    public MenuDTO readMenuById(@RequestBody MenuDTO menuDTO) {
        MenuDTO menuItem = menuService.readMenuById(menuDTO.getMenuId());
        return menuItem;
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
