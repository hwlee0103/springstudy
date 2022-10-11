var __COMMON = com.example.springstudy.common;

//--------------------------------------------------------------------------------------------------------------------------------------------------------
//전역 변수
//--------------------------------------------------------------------------------------------------------------------------------------------------------
var __STATIC_VALUE = "";

//--------------------------------------------------------------------------------------------------------------------------------------------------------
//시작 부분
//--------------------------------------------------------------------------------------------------------------------------------------------------------
var init = function(){
    main.init();
};

//--------------------------------------------------------------------------------------------------------------------------------------------------------
//메인 함수 정의 부분
//--------------------------------------------------------------------------------------------------------------------------------------------------------
var main = (function() {
    return {
        //---------------------------------------------------------------------------------------------------------
        // 초기 이벤트 바인딩
        //---------------------------------------------------------------------------------------------------------
        init : function(){
            // TODO sample
            bind.eventBind();
            bind.dataBind();
        }
    }
}());


//--------------------------------------------------------------------------------------------------------------------------------------------------------
//바인딩 함수 정의 부분
//--------------------------------------------------------------------------------------------------------------------------------------------------------
var bind = (function() {
    return {
        //---------------------------------------------------------------------------------------------------------
        // sample 이벤트 바인딩
        //---------------------------------------------------------------------------------------------------------
        eventBind : function(){
            event.clickAddName();
        },

        //---------------------------------------------------------------------------------------------------------
        // sample 데이터 바인딩
        //---------------------------------------------------------------------------------------------------------
        dataBind : function(){
            var _params = {};
            _params = {
                memoSeq : $("#memoSeq").data("id")
            }

            //#region - data 조회

            // memo detail data 조회
            __COMMON.ajax.ajaxLoad("/memo/detaildata", _params, function (p_data) {
                var _result = p_data;

                var _html = [];

                if(p_data != null) {
                    var _$memoTitle = $("#memoTitle");
                    var _$memoContent = $("#memoContent");
                    var _$memoWriter = $("#memoWriter");

                    var _memoSeq = __COMMON.utils.isNull(_result.memoSeq, "string");
                    var _memoTitle = __COMMON.utils.isNull(_result.memoTitle, "string");
                    var _memoContent = __COMMON.utils.isNull(_result.memoContent, "string");
                    var _memoWriter = __COMMON.utils.isNull(_result.memoWriter, "string");

                    //.attr vs .prop
                    _$memoTitle.val(_memoTitle).prop("readOnly", true);
                    _$memoContent.val(_memoContent).prop("readOnly", true);
                    _$memoWriter.val(_memoWriter).prop("readOnly", true);
                } else {
                    alert("Memo Detail 데이터가 존재하지 않습니다.")
                }
            }, false
            , jQuery("#btn"));

            // comment data 조회
            __COMMON.ajax.ajaxLoad("/comment/listdata", _params, function(p_data) {
                var _result = p_data;

                var _html = p_data.commentHtml;

                if(p_data != null) {
                    // TODO: 댓글 목록 setting
                    //alert("Comment List");

                    $("#comment-body").html(_html);
                    event.clickAddAction(); //새로 생성된 애들한테 eventbind
                } else {
                    // TODO: 댓글 없음
                    alert("Comment 데이터가 존재하지 않습니다.");
                }
            }, false
            , jQuery("#btn"));

            //#endregion
        },
        //재귀형식으로 댓글 setting //????
        setCommentData: function(p_data) {

        }
    }
}());

//--------------------------------------------------------------------------------------------------------------------------------------------------------
//프로세스
//--------------------------------------------------------------------------------------------------------------------------------------------------------
var process = (function() {
    return {
        //---------------------------------------------------------------------------------------------------------
        // sample 프로세스
        //---------------------------------------------------------------------------------------------------------
        defaultProcess : function(){
            // TODO
        }
    }
}());


//--------------------------------------------------------------------------------------------------------------------------------------------------------
//이벤트
//--------------------------------------------------------------------------------------------------------------------------------------------------------
var event = (function() {
    return {
        //---------------------------------------------------------------------------------------------------------
        // sample 클릭 추가 이벤트
        //---------------------------------------------------------------------------------------------------------
        clickAddName : function(){
            // 수정 버튼
            jQuery("#updateButton").off("click.add").on("click", function(){
                var _$memoTitle = $("#memoTitle");
                var _$memoContent = $("#memoContent");
                var _$memoWriter = $("#memoWriter");
                var _$updateButton = $("#updateButton");
                var _$saveButton = $("#saveButton");

                _param = {
                    memoSeq : $("#memoSeq").data("id"),
                    memoTitle : _$memoTitle.val(),
                    memoContent : _$memoContent.val(),
                    memoWriter : _$memoWriter.val()
                }

                __COMMON.ajax.ajaxLoad("/memo/update", _param, function (p_data){
                    if(p_data == true) {
                        alert("저장되었습니다.");
                    } else {
                        alert("저장이 되지 않았습니다.");
                    }
                });

                _$memoTitle.prop("readOnly", false);
                _$memoContent.prop("readOnly", false);
                _$memoWriter.prop("readOnly", false);
                _$updateButton.prop("hidden", true);
                _$saveButton.prop("hidden", false);

            });

            // 저장 버튼
            jQuery("#saveButton").off("click.add").on("click", function(){
                var _$memoTitle = $("#memoTitle");
                var _$memoContent = $("#memoContent");
                var _$memoWriter = $("#memoWriter");
                var _$updateButton = $("#updateButton");
                var _$saveButton = $("#saveButton");

                _param = {
                    memoSeq : $("#memoSeq").data("id"),
                    memoTitle : _$memoTitle.val(),
                    memoContent : _$memoContent.val(),
                    memoWriter : _$memoWriter.val()
                }

                __COMMON.ajax.ajaxLoad("/memo/update", _param, function (p_data){
                    if(p_data == true) {
                        alert("저장되었습니다.");
                    } else {
                        alert("저장이 되지 않았습니다.");
                    }
                });

                _$memoTitle.prop("readOnly", true);
                _$memoContent.prop("readOnly", true);
                _$memoWriter.prop("readOnly", true);
                _$updateButton.prop("hidden", false);
                _$saveButton.prop("hidden", true);
            });

            jQuery("#commentSaveButton").off("click.add").on("click", function(){
               var _$newCommentContent = $("#newCommentContent");
               var _$newCommentWriter = $("#newCommentWriter");

               var _param = {
                   memoSeq : $("#memoSeq").data("id"),
                   commentContent : _$newCommentContent.val(),
                   commentWriter : _$newCommentWriter.val()
               }

               __COMMON.ajax.ajaxLoad("/comment/save", _param, function(p_data){
                    if(p_data == true) {
                        location.reload();
                        //$("#comment-body").load("${contextpath}/comment/listdata #comment-body");
                    }
               });
            });

            // 삭제 버튼
            jQuery("#deleteButton").off("click.add").on("click", function(){

                var _param = new Object();
                _param.memoSeq = $("#memoSeq").data("id");

                __COMMON.ajax.ajaxLoad("/memo/delete", _param, function(p_data){
                    if(p_data == true) {
                        alert("삭제되었습니다.");
                        location.href = "/memo/list";
                    } else {
                        alert("삭제 되지 않았습니다.");
                    }
                });
            });
        },

        clickAddAction: function() {
            jQuery("button[name='commentDeleteBtn']").off("click.add").on("click", function() {
                var _param = new Object();
                //댓글 대댓글 삭제 로직
                _param.commentSeq = parseInt($(this).parent().find("span[name=commentSeq]").html());
                _param.commentDepth = parseInt($(this).parent().find("span[name=commentDepth]").html());
                _param.commentGroup = parseInt($(this).parent().find("span[name=commentGroup]").html());
                _param.memoSeq = $("#memoSeq").data("id");
                _param.commentWriter = $(this).parent().find("span[name=commentWriter]").html();

                if($(this).parent().children("div").html() === "undefined") {
                    _param.isChild = false;
                } else {
                    _param.isChild = true;
                }
                //_param.isChild = $(this).parent().children("div").html();
                //alert(_param.isChild);
                //alert(_param.commentSeq + "  " + _param.commentDepth + "  " + _param.commentGroup);
                __COMMON.ajax.ajaxLoad("/comment/delete", _param, function(p_data) {
                    if(p_data == true) {
                        location.reload();
                    }
                });
            });
        },
    }
}());


//--------------------------------------------------------------------------------------------------------------------------------------------------------
//시작함수 호출
//--------------------------------------------------------------------------------------------------------------------------------------------------------
jQuery(document).ready(function() {
    //시작 호출
    init();
});
