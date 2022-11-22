package com.example.springstudy.controller.menu;

import com.example.springstudy.entity.menu.MenuEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {


    //#region - 조회

    //Q. menu는 항상 보여야하는거면 어떻게 항상 보여주지?

    //list도 조회해서 보여줘야하는건가?
    //list에는 Deth별로 디자인을 바꿔서 보여줘야할 필요가?
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView readMenuList(ModelAndView mav) {
        mav.setViewName("menu/list");

        return mav;
    }

    //메뉴 detail


    //#endregion

    //#region - 생성

    //menu를 생성할 땐, 어떤걸 기준으로 depth나 parent 를 설정해야하지?


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
