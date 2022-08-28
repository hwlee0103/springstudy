package com.example.springstudy.entity.memo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "memo")
@Data
@EqualsAndHashCode(callSuper = false)
public class MemoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memo_seq", updatable = false)
    private Integer memoSeq;

    @Column(name = "memo_title")
    private String memoTitle = "";

    @Column(name = "memo_content")
    private String memoContent = "";

    @Column(name = "memo_writer")
    private String memoWriter = "";

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "created_by", updatable = false)
    @CreatedBy
    private String createdBy = "";

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate = LocalDateTime.now();

    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy = "";
}
