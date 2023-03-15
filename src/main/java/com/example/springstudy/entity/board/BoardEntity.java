package com.example.springstudy.springstart.entity.board;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Data
@EqualsAndHashCode(callSuper = false)
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_seq", updatable = false)
    private Integer boardSeq;

    @Column(name = "board_title")
    private String boardTitle = "";

    @Column(name = "board_content")
    private String boardContent = "";

    @Column(name = "board_writer")
    private String boardWriter = "";

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
