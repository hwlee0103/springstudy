package com.example.springstudy.controller.board;

import com.example.springstudy.entity.board.BoardEntity;
import com.example.springstudy.service.board.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //#region - 조회

    /**
     * 게시글 목록 조회
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView boardList(ModelAndView modelAndView) {
        modelAndView.setViewName("board/list");
        modelAndView.addObject("boardList", boardService.findBoardList());
        return modelAndView;
    }

    /**
     * 게시글 상세 조회
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/detail/{boardSeq}", method = RequestMethod.GET)
    public ModelAndView boardDetail(ModelAndView modelAndView, @PathVariable(name = "boardSeq") Integer boardSeq) {
        modelAndView.setViewName("board/detail");
        BoardEntity test = boardService.findBoardById(boardSeq);
        modelAndView.addObject("boardItem", test);
        return modelAndView;
    }

    //#endregion

    //#region - 저장

    /**
     * 게시글 저장 화면
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public ModelAndView boardWrite(ModelAndView modelAndView) {
        modelAndView.setViewName("board/write");
        return modelAndView;
    }

    /**
     * 게시글 저장
     * @param boardEntity
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String boardSave(BoardEntity boardEntity) {
        boolean saveResult = boardService.saveBoard(boardEntity);

        //저장 후 목록 화면으로
        return "redirect:/board/list";
    }

    /**
     * 게시글 수정
     * @param boardEntity
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String boardUpdate(BoardEntity boardEntity) {
        boolean saveResult = boardService.updateBoard(boardEntity);

        //저장 후 목록 화면으로
        return "redirect:/board/list";
    }

    //#endregion

    //#region - 삭제

    /**
     * 게시글 삭제
     * @param boardSeq
     * @return
     */
    @RequestMapping(value = "/delete/{boardSeq}", method = RequestMethod.GET)
    public String boardDelete(@PathVariable(name = "boardSeq") Integer boardSeq) {
        boardService.deleteBoard(boardSeq);
        //저장 후 목록 화면으로
        return "redirect:/board/list";
    }

    //#endregion
}
