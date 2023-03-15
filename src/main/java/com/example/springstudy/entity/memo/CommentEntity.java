package com.example.springstudy.entity.memo;

import com.example.springstudy.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Data
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_seq", updatable = false)
    private Integer commentSeq;
    @Column(name = "memo_seq")
    private Integer memoSeq;
    @Column(name = "comment_depth")
    private Integer commentDepth;
    @Column(name = "comment_group")
    private Integer commentGroup;
    @Column(name = "comment_content")
    private String commentContent;
    @Column(name = "comment_writer")
    private String commentWriter;

}
