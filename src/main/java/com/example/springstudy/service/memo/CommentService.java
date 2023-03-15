package com.example.springstudy.springstart.service.memo;

import com.example.springstudy.springstart.entity.memo.CommentEntity;
import com.example.springstudy.springstart.model.comment.CommentDTO;
import com.example.springstudy.springstart.repository.memo.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
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

        //commentDTO 생성해서 그 안에서 List를 가지고 있고 List 자료형이 commentDTO - Reply
        List<CommentDTO> commentDtoList = new ArrayList<CommentDTO>();
        Map<Integer, CommentDTO> map = new HashMap<>();

        for(CommentEntity commentItem : commentList) {
            CommentDTO dto = CommentDTO.convertCommentToDto(commentItem);
            map.put(dto.getCommentSeq(), dto);
            //commentGroup이 0이면 1depth 댓글이므로 list에 넣어주고
            if(commentItem.getCommentGroup() != 0) {
                //commentGroup이 0이 아니면 (부모 댓글이 있는 경우) 부모댓글에 add
                map.get(commentItem.getCommentGroup()).getReply().add(dto);
                //null check 추가
            } else {
                // 최상위 댓글 그룹
                commentDtoList.add(dto);
            }
        }

        return commentDtoList;
    }

    /**
     * 댓글 대댓글 HTML문 만들기
     * @param commentList
     * @return
     */
    //commentList - 댓글 대댓글 재귀형태로 조회
    public String makeCommentHtml(List<CommentDTO> commentList) {
        String result = "";
        result += "<div>";

        //for로 전체 탐색
        for(CommentDTO commentItem : commentList) {
            result += "<div>";
            if(commentItem.getCommentDepth() - 1 > 0) {
                for(int i = 0; i < commentItem.getCommentDepth() - 1; ++i) {
                    result += "   L";
                }
            }

            result += "<span name='commentSeq'>" + commentItem.getCommentSeq() +
                    "</span><span name='commentDepth' style='display:none;'>" + commentItem.getCommentDepth()
                    + "</span><span name='commentGroup' style='display:none;'>" + commentItem.getCommentGroup()
                    + "</span> | 댓글: <input type='text' readonly='true' value='" + commentItem.getCommentContent() +
                    "' /> | 작성자: <span name='commentWriter'>" + commentItem.getCommentWriter() + "   "
                    + "</span><button type='button' name='commentModifyBtn'> 수정 </button><button type='button' hidden='true' name='commentModifySaveBtn'> 저장 </button><button type='button' name='commentDeleteBtn'> 삭제 </button>"
                    + "<input type='text' name='replyContent' placeholder='대댓글 내용 입력'/><input type='text' name='replyWriter' placeholder='대댓글 작성자'/>"
                    + "<button type='button' name='replySave' data-id="+ commentItem.getCommentSeq() +" data-depth=" + commentItem.getCommentDepth() + ">대댓글 저장</button>" ;

            if(ObjectUtils.isEmpty(commentItem.getReply()) == false) {
                result += "<div>" + makeCommentHtml(commentItem.getReply()) + "</div>";
            }
            result += "</div>";
        }
        result += "</div>";
        return result;
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
        result = ObjectUtils.isEmpty(saved) == false;
        return result;
    }

    /**
     * 대댓글 저장
     * @param commentEntity
     * @return
     */
    public Boolean saveReply(CommentEntity commentEntity) {
        Boolean saved = false;
        commentEntity.setCreatedBy(commentEntity.getCommentWriter());
        commentEntity.setModifiedBy(commentEntity.getCommentWriter());
        CommentEntity replySaved = this.commentRepository.saveAndFlush(commentEntity);

        if(ObjectUtils.isEmpty(replySaved) == false) {
            saved = true;
        }

        return saved;
    }
    //#endregion

    //#region - 수정

    public Boolean modifyComment(CommentEntity commentEntity) {
        Boolean res = false;
        CommentEntity saved = this.commentRepository.saveAndFlush(commentEntity);

        if(ObjectUtils.isEmpty(saved) == false) {
            res = true;
        }

        return res;
    }

    //#endregion

    //#region - 삭제

    public Boolean deleteComment(CommentDTO commentDto) {
        Boolean result = false;
        CommentEntity commentEntity = new CommentEntity();
        CommentEntity saved = new CommentEntity();

        commentEntity.setCommentSeq(commentDto.getCommentSeq());
        commentEntity.setMemoSeq(commentDto.getMemoSeq());
        commentEntity.setCommentDepth(commentDto.getCommentDepth());
        commentEntity.setCommentGroup(commentDto.getCommentGroup());
        commentEntity.setCommentWriter(commentDto.getCommentWriter());
        commentEntity.setModifiedDate(LocalDateTime.now());

        if(commentDto.getIsChild() != null && commentDto.getIsChild() == true) {
            commentEntity.setCommentContent("삭제된 댓글입니다.");
            saved = this.commentRepository.saveAndFlush(commentEntity);
        } else {
            this.commentRepository.deleteById(commentEntity.getCommentSeq());
        }

        if(ObjectUtils.isEmpty(saved) == false) {
            result = true;
        }

        return result;
    }

    //#endregion
}
