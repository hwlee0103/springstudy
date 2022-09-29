package com.example.springstudy.service.memo;

import com.example.springstudy.entity.memo.CommentEntity;
import com.example.springstudy.model.comment.CommentDTO;
import com.example.springstudy.repository.memo.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    //#region - 조회

    public List<CommentDTO> findCommentList(Integer memoSeq) {
        List<CommentEntity> commentList = this.commentRepository.findByMemoSeqOrderByCommentGroup(memoSeq);

        //commentDTO 생성해서 그 안에서 List를 가지고 있고 List 자료형이 commentDTO라면?
        List<CommentDTO> commentDtoList = new ArrayList<CommentDTO>();
        Map<Integer, CommentDTO> map = new HashMap<>();

        String test = "";

        for(CommentEntity commentItem : commentList) {
            CommentDTO dto = CommentDTO.convertCommentToDto(commentItem);
            map.put(dto.getCommentSeq(), dto);
            //commentGroup이 0이면 1depth 댓글이므로 list에 넣어주고
            if(commentItem.getCommentGroup() != 0) {
                //commentGroup이 0이 아니면 (부모 댓글이 있는 경우) 부모댓글에 add
                map.get(commentItem.getCommentGroup()).getReply().add(dto);
                //test +=
            } else {
                // 최상위 댓글 그룹
                commentDtoList.add(dto);
                test += "<div>" + dto.getCommentContent() + "</div>";
            }
        }

        return commentDtoList;
    }

    //commentList - 댓글 대댓글 재귀형태로 조회
    public String makeCommentHtml(List<CommentDTO> commentList) {
        String result = "<div>";

        //for로 전체 탐색
        for(CommentDTO commentItem : commentList) {
            result += makeCommentHtmlItem(commentItem);
//            if(ObjectUtils.isEmpty(commentItem.getReply()) == false) {
//                //자식 노드가 있을 경우
//                result += makeCommentHtmlItem(commentItem.getReply());
//            } else {
//
//            }
        }
        result += "</div>";
        return result;
    }

    public String makeCommentHtmlItem(CommentDTO commentItem) {
        String str = "";

        if(ObjectUtils.isEmpty(commentItem.getReply()) == false) {
            //str += makeCommentHtmlItem()
        }

        return str;
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
