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

        return mav;
    }

    /**
     * 게시글 리스트 조회
     * @return
     */
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

    /**
     * 게시글 상세 페이지
     * @param memoSeq
     * @return
     */
    @RequestMapping(value = "/detail/{memoSeq}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView memoDetail(ModelAndView mav, @PathVariable(name = "memoSeq") Integer memoSeq) {
        mav.setViewName("memo/detail");
        return mav;
    }

    /**
     * 게시글 단건 조회
     * @param memoEntity
     * @return
     */
    @RequestMapping(value = "/detaildata", method = RequestMethod.POST)
    @ResponseBody
    public MemoEntity memoDetailData(@RequestBody MemoEntity memoEntity, ModelAndView modelAndView) {
        // @RequestParam vs @RequestBody
        MemoEntity memoItem = memoService.findMemo(memoEntity.getMemoSeq());
        return memoItem;
    }

    // TODO: 댓글
    /*
    1. 댓글 Table 설계
    - 게시글 seq를 foreign key로 해서 어떤 게시글에 달린 댓글인지 column으로 가지고 있기
    - 나중에 depth를 늘릴 걸 생각하면 (대댓글) depth 컬럼도 가지고 있어야하고
    - 대댓글 순서도 필요한데 대댓글 seq 기준으로 출력하면 되니까 seq로 충분할 것 같고,
    - 대댓글은 어떤 댓글에 달린 애들인지 알아야 하니까 윗 Depth 의 id를 가지고 있어야한다. comment group
    - 대댓글의 depth가 2 이상일 경우도 필요할까?
    - 그러면 depth가 2이상일 경우에는 depth가 한 개 상위인 애들을 모두 scan해서 comment group을 찾아야하나
    - 나중에 depth가 한 5정도 되면 table scan이 비효율적일까?
    
    - 공통적인 사항은 BaseEntity로 빼볼가ㅏㅏㅏ

    2. 댓글 작성 - html

    3. 댓글 조회 - js에서 조회해오기

    */

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

    /**
     * 게시글 수정
     * @param memoEntity
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateMemo(@RequestBody MemoEntity memoEntity){
        Boolean result = false;
        if(ObjectUtils.isEmpty(memoEntity) == false) {
            result = memoService.updateMemo(memoEntity);
        } else {
            result = false;
        }
        return result;
    }


    //#endregion

    //#region - 삭제

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteMemo(@RequestBody MemoEntity memoEntity) {

        if(ObjectUtils.isEmpty(memoEntity.getMemoSeq()) == false){
            memoService.deleteMemo(memoEntity.getMemoSeq());
        } else {

        }
        return true;
    }

    //#endregion

}
