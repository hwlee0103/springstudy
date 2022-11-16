package com.example.springstudy.controller.menu;

import com.example.springstudy.entity.menu.MenuEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/menu")
// Q. menu는 항상 보여야하는거면 어떻게 항상 보여주지?
public class MenuController {


    //#region - 조회
    //#endregion

    //#region - 생성

    // 메뉴 생성

    /**
     * Menu 생성 Page
     * @param mav
     * @return
     */
    @RequestMapping(value = "/createPage", method = RequestMethod.GET)
    public ModelAndView createMenuPage(ModelAndView mav) {
        mav.setViewName("menu/create");

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
