package com.example.springstudy.springstart.controller.memo;

import com.example.springstudy.springstart.entity.memo.CommentEntity;
import com.example.springstudy.springstart.model.comment.CommentDTO;
import com.example.springstudy.springstart.service.memo.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //#region - 조회

    /**
     * 댓글 조회
     * @param commentEntity
     * @return
     */
    @PostMapping(value = "/listdata")
    @ResponseBody
    //public List<CommentDTO> commentList(@RequestBody CommentEntity commentEntity) {
    public String commentList(@RequestBody CommentEntity commentEntity) {
        List<CommentDTO> commentList = this.commentService.findCommentList(commentEntity.getMemoSeq());

        //Service 단에서 html setting해서 넘겨주면?
        //String commentHtml = "{\"commentHtml\":\"<div><span>CommentList</span></div>\"}";
        String commentHtml = "{\"commentHtml\":\"<div>" + commentService.makeCommentHtml(commentList) + "</div>\"}";

        return commentHtml;
        //return commentList;
    }
    /*
    + 댓글 보여주는 html
    -- Detail 페이지 load시 댓글 가져오기
    -- js에서 댓글 조회해오는 ajax 로직
    -- 조회해와서 service단에서 재귀로 탐색, 정렬
    -- List<T>로 가져와서 Stream으로 정렬 및 필터 가능?
    -- 그리고 그대로 화면으로 가져가서 setting
    -- html - 댓글 더보기 버튼
    */

    //#endregion


    //#region - 저장

    /*
    + 댓글 작성 칸 Memo Detail 페이지에 생성
    -- 기존 댓글 있으면 맨 위에 댓글 작성 칸 만들기
    -- 작성 클릭 시 ajax로 save 보내고
    -- 작성 끝나면 페이지 reload(?)
    */

    /**
     * 댓글 저장
     * @param commentEntity
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public Boolean commentSave(@RequestBody CommentEntity commentEntity) {
        Boolean result = false;
        if(ObjectUtils.isEmpty(commentEntity) == false){
            result = this.commentService.saveComment(commentEntity);
        } else {
            result = false;
        }

        return result;
    }

    /**
     * 대댓글 저장
     * @param commentEntity
     * @return
     */
    @PostMapping(value = "/saveReply")
    @ResponseBody
    public Boolean saveReply(@RequestBody CommentEntity commentEntity) {
        Boolean res = false;

        if(ObjectUtils.isEmpty(commentEntity) == false) {
            res = this.commentService.saveReply(commentEntity);
        }

        return true;
    }

    //#endregion

    //#region - 수정

    @RequestMapping(value = "/modify")
    @ResponseBody
    public Boolean commentModify(@RequestBody CommentEntity commentEntity) {
        Boolean saved = false;

        if(ObjectUtils.isEmpty(commentEntity) == false) {
            saved = this.commentService.modifyComment(commentEntity);
            this.commentService.modifyComment(commentEntity);
        }

        return saved;
    }

    //#endregion

    //#region - 삭제

    @PostMapping(value = "/delete")
    @ResponseBody
    public Boolean commentDelete(@RequestBody CommentDTO commentDto) {
        Boolean result = false;

        if(ObjectUtils.isEmpty(commentDto) == false) {
            result = this.commentService.deleteComment(commentDto);
        }

        return result;
    }
    /*
    + 댓글 삭제하기
    -- 댓글 삭제 - 삭제된 댓글일 시 [삭제된 댓글입니다] --?
    -- 대댓글이 아닌 일반 댓글은 DelYN으로 삭제여부 체크해야하나?
    -- 삭제 시 Delyn = y
    -- 내용은 [삭제된 댓글입니다.] ?
    */

    //#endregion

}
