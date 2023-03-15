package com.example.springstudy.springstart.entity.memo;

import com.example.springstudy.springstart.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "memo")
@Data
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class MemoEntity extends BaseEntity {

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

}
