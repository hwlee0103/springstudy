package com.example.springstudy.controller.memo;

import com.example.springstudy.entity.memo.MemoEntity;
import com.example.springstudy.service.memo.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/memo")
public class MemoController {

    private final MemoService memoService;

    @Autowired
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    //#region - 조회

    /**
     * 게시글 조회 페이지
     * @param mav
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView memoList(ModelAndView mav){
        mav.setViewName("memo/list");
        // TODO memo list 불러오기
        // mav.addObject("memoList", memoService.findAll());

        return mav;
    }

    @RequestMapping(value = "/listdata", method = RequestMethod.POST)
    @ResponseBody
    public List<MemoEntity> memoList(){
        // TODO memo list 불러오기
        List<MemoEntity> memoEntityList = memoService.findAll();

        if(ObjectUtils.isEmpty(memoEntityList) == false) {
            // TODO return data 있을 때
        } else {
            // TODO exception
        }

        return memoEntityList;
    }

    //#endregion

    //#region - 저장

    /**
     * 게시글 저장 페이지
     * @param mav
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public ModelAndView insertMemo(ModelAndView mav) {
        mav.setViewName("memo/insert");
        // TODO memo insert 페이지

        return mav;
    }

    /**
     * 게시글 저장
     * @param memoEntity
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveMemo(@RequestBody MemoEntity memoEntity) {
        // TODO memo save

        if(ObjectUtils.isEmpty(memoEntity) == false) {
            memoService.saveMemo(memoEntity);
        } else {
            // TODO : exception
        }

        return "redirect:list";
    }


    //#endregion

}
