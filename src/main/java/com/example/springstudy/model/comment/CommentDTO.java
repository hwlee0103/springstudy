package com.example.springstudy.model.comment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private List<CommentDTO> reply;

}
