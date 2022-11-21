package com.example.springstudy.model.menu;

// Lombok 없이 사용하는 Builder Pattern
public class MenuDTO {
    private String menuId;
    private String parentMenuId;
    private String topParentMenuId;
    private Integer depth;
    private String menuName;
    private String menuType;
    private String link;
    private String linkOpenType;
    private Character pcExposureTf;
    private Character mobileExposureTf;
    private Character exposureTf;
    private Integer sort;
    private String description;

    // get
    public String getMenuId() {
        return menuId;
    }
    public String getParentMenuId() {
        return parentMenuId;
    }
    public String getTopParentMenuId() {
        return topParentMenuId;
    }
    public Integer getDepth() {
        return depth;
    }
    public String getMenuName() {
        return menuName;
    }
    public String getMenuType() {
        return menuType;
    }
    public String getLink() {
        return link;
    }
    public String getLinkOpenType() {
        return linkOpenType;
    }
    public Character getPcExposureTf() {
        return pcExposureTf;
    }
    public Character getMobileExposureTf() {
        return mobileExposureTf;
    }
    public Character getExposureTf() {
        return exposureTf;
    }
    public Integer getSort() {
        return sort;
    }
    public String getDescription() {
        return description;
    }

    private MenuDTO(MenuDTOBuilder builder) {
        this.menuId = builder.menuId;
        this.parentMenuId = builder.parentMenuId;
        this.topParentMenuId = builder.topParentMenuId;
        this.depth = builder.depth;
//        private String menuName;
//        private String menuType;
//        private String link;
//        private String linkOpenType;
//        private Character pcExposureTf;
//        private Character mobileExposureTf;
//        private Character exposureTf;
//        private Integer sort;
//        private String description;
    }

    public static class MenuDTOBuilder {
        private String menuId;
        private String parentMenuId;
        private String topParentMenuId;
        private Integer depth;
        private String menuName;
        private String menuType;
        private String link;
        private String linkOpenType;
        private Character pcExposureTf;
        private Character mobileExposureTf;
        private Character exposureTf;
        private Integer sort;
        private String description;

        //필수 입력 필드 강제할 수 있음
        public MenuDTOBuilder builder(String menuId, Integer depth, String menuName,
                                      String menuType, Character pcExposureTf, Character mobileExposureTf,
                                      Character exposureTf, Integer sort) {
            this.menuId = menuId;
            this.depth = depth;
            this.menuName = menuName;
            this.menuType = menuType;
            this.pcExposureTf = pcExposureTf;
            this.mobileExposureTf = mobileExposureTf;
            this.exposureTf = exposureTf;
            this.sort = sort;

            return this;
        }

        // Q. 필수 입력 필드 강제하면 여기서 따로 해줄필요 없는것?
        public MenuDTOBuilder menuId(String menuId) {
            this.menuId = menuId;
            return this;
        }
        public MenuDTOBuilder parentMenuId(String parentMenuId) {
            this.parentMenuId = parentMenuId;
            return this;
        }
        public MenuDTOBuilder topParentMenuId(String topParentMenuId) {
            this.topParentMenuId = topParentMenuId;
            return this;
        }


        private Integer depth;
        private String menuName;
        private String menuType;
        private String link;
        private String linkOpenType;
        private Character pcExposureTf;
        private Character mobileExposureTf;
        private Character exposureTf;
        private Integer sort;
        private String description;
        public MenuDTO build(){
            return new MenuDTO(this);
        }
    }
}
