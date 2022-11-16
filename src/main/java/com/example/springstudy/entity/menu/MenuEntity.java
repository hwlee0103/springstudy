package com.example.springstudy.entity.menu;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.example.springstudy.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "menu")
@Data
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class MenuEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "menu_id")
    private String menuId;

    @Column(name = "parent_menu_id")
    private String parentMenuId;

    @Column(name = "top_parent_menu_id")
    private String topParentMenuId;

    @NotNull
    @Column(name = "depth")
    private Integer depth;

    @NotNull
    @Column(name = "menu_name")
    private String menuName;

    @NotNull
    @Column(name = "menu_type")
    private String menuType;

    @Column(name = "link")
    private String link;

    @Column(name = "link_open_type")
    private String linkOpenType;

    @NotNull
    @Column(name = "pc_exposure_tf")
    private Character pcExposureTf = 't';

    @NotNull
    @Column(name = "mobile_exposure_tf")
    private Character mobileExposureTf = 't';

    @NotNull
    @Column(name = "exposure_tf")
    private Character exposureTf = 't';

    @NotNull
    @Column(name = "sort")
    private Integer sort;

    @Column(name = "description")
    private String description;

}
