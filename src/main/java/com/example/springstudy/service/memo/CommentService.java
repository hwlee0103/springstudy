package com.example.springstudy.service.memo;

import com.example.springstudy.entity.memo.CommentEntity;
import com.example.springstudy.model.comment.CommentDTO;
import com.example.springstudy.repository.memo.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    //#region - 조회

    public List<CommentEntity> findCommentList(Integer memoSeq) {
        List<CommentEntity> commentList = this.commentRepository.findByMemoSeq(memoSeq);

        //재귀,,,,,?
        //commentDTO 생성해서 그 안에서 List를 가지고 있고 List 자료형이 commentDTO라면?
        List<CommentDTO> commentDtoList = new ArrayList<CommentDTO>();
        //map으로 하면 ?..?
        for(CommentEntity commentItem : commentList) {

        }

        return commentList;
    }


    //#endregion

    //#region - 저장

    /**
     * 댓글 저장
     * @param commentEntity
     */
    public Boolean saveComment(CommentEntity commentEntity) {
        Boolean result = false;

        commentEntity.setCommentDepth(1);
        commentEntity.setCommentGroup(0); // 1Depth
        commentEntity.setCreatedBy(commentEntity.getCommentWriter());
        commentEntity.setModifiedBy(commentEntity.getCommentWriter());

        CommentEntity saved = this.commentRepository.saveAndFlush(commentEntity);
        if(ObjectUtils.isEmpty(saved) == false) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    //#endregion
}
