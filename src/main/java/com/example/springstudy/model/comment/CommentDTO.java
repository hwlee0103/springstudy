package com.example.springstudy.model.comment;

import com.example.springstudy.entity.memo.CommentEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CommentDTO {

    private Integer commentSeq;

    private Integer memoSeq;

    private Integer commentDepth;

    private Integer commentGroup;

    private String commentContent;

    private String commentWriter;

    private LocalDateTime createdDate;

    /* 대댓글 */
    private List<CommentDTO> reply = new ArrayList<CommentDTO>();

    public CommentDTO(Integer commentSeq, Integer memoSeq,
                      Integer commentDepth, Integer commentGroup,
                      String commentContent, String commentWriter, LocalDateTime createdDate) {
        this.commentSeq = commentSeq;
        this.memoSeq = memoSeq;
        this.commentDepth = commentDepth;
        this.commentGroup = commentGroup;
        this.commentContent = commentContent;
        this.commentWriter = commentWriter;
        this.createdDate = createdDate;
    }


    //convert CommentEntity to CommentDTO
    public static CommentDTO convertCommentToDto(CommentEntity commentEntity) {
        return new CommentDTO(commentEntity.getCommentSeq(), commentEntity.getMemoSeq(),
                commentEntity.getCommentDepth(), commentEntity.getCommentGroup(),
                commentEntity.getCommentContent(), commentEntity.getCommentWriter(),
                commentEntity.getCreatedDate());
    }

}
