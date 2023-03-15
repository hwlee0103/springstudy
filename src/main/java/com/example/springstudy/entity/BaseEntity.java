package com.example.springstudy.springstart.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class BaseEntity {

    @NotNull
    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();

    @NotNull
    @Column(name = "created_by", updatable = false)
    @CreatedBy
    private String createdBy = "";

    @NotNull
    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate = LocalDateTime.now();

    @NotNull
    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy = "";

}
