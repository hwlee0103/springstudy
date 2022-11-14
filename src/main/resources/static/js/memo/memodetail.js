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

                var _param = {};
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

            jQuery("button[name='commentModifySaveBtn']").off("click.add").on("click", function () {
                // Q. 이렇게 화면에서 데이터를 일일히 가져오는 것 vs commentSeq가지고 조회해와서 조회 결과를 가지고 save해주는 것?
                var _$commentSeq = $(this).parent().find(">span[name='commentSeq']");
                var _$memoSeq = $("#memoSeq");
                var _$commentDepth = $(this).parent().find(">span[name='commentDepth']");
                var _$commentGroup = $(this).parent().find(">span[name='commentGroup']");
                var _$commentContent = $(this).parent().find(">input");
                var _$commentWriter = $(this).parent().find(">span[name='commentWriter']");
                var _$commentModifyBtn = $(this);
                var _$commentMoifySaveBtn = $(this).parent().find(">button[name='commentModifySaveBtn']");

                var _param = {
                    commentSeq: parseInt(_$commentSeq.html()),
                    memoSeq: _$memoSeq.data("id"),
                    commentDepth: parseInt(_$commentDepth.html()),
                    commentGroup: parseInt(_$commentGroup.html()),
                    commentContent: _$commentContent.val(),
                    commentWriter: _$commentWriter.html()
                };

                __COMMON.ajax.ajaxLoad("/comment/modify", _param, function (p_data) {
                    if(p_data == true) {
                        //alert("수정되었습니다.");
                        location.reload();
                    } else {
                        //alert("수정되지 않았습니다.");
                    }

                    _$commentContent.prop("readonly", true);
                    _$commentModifyBtn.prop("hidden", false);
                    _$commentMoifySaveBtn.prop("hidden", true);
                });

                // _$commentContent.prop("readonly", true);
                // _$commentModifyBtn.prop("hidden", false);
                // _$commentMoifySaveBtn.prop("hidden", true);
            });

            jQuery("button[name='commentModifyBtn']").off("click.add").on("click", function(){
                var _$commentContent = $(this).parent().find(">input");
                var _$commentModifyBtn = $(this);
                var _$commentMoifySaveBtn = $(this).parent().find(">button[name='commentModifySaveBtn']");
                _$commentContent.prop("readonly", false);
                _$commentModifyBtn.prop("hidden", true);
                _$commentMoifySaveBtn.prop("hidden", false);
            });

            jQuery("button[name='commentDeleteBtn']").off("click.add").on("click", function() {
                var _param = {};
                //댓글 대댓글 삭제 로직
                _param.commentSeq = parseInt($(this).parent().find("span[name=commentSeq]").html());
                _param.commentDepth = parseInt($(this).parent().find("span[name=commentDepth]").html());
                _param.commentGroup = parseInt($(this).parent().find("span[name=commentGroup]").html());
                _param.memoSeq = $("#memoSeq").data("id");
                _param.commentWriter = $(this).parent().find("span[name=commentWriter]").html();

                if($(this).parent().children("div").html() === undefined) {
                    _param.isChild = false;
                } else {
                    _param.isChild = true;
                }

                __COMMON.ajax.ajaxLoad("/comment/delete", _param, function(p_data) {
                    if(p_data == true) {
                        location.reload();
                    }
                });
            });

            jQuery("button[name='commentReplyBtn']").off("click.add").on("click", function() {
                
                // TODO : 대댓글 입력창 만들기
                var _$comment = $(this); // 이거아닌거같은데.....

                var inputHtml = "<input id='replyContent' placeholder='대댓글 내용' type='text' />"
                                + "<input id='replyWriter' placeholder='대댓글 작성자' type='text' />";

                _$comment.append(inputHtml);
                
            });

            jQuery("button[name='replySave']").off("click.add").on("click", function() {
                var _param = {};

                _param.commentDepth = parseInt($(this).data("depth") + 1);
                _param.commentContent = $(this).parent().find("input[name='replyContent']").val();
                _param.commentWriter = $(this).parent().find("input[name='replyWriter']").val();
                _param.commentGroup = $(this).data("id");
                _param.memoSeq = $("#memoSeq").data("id");

                __COMMON.ajax.ajaxLoad('/comment/saveReply', _param, function(p_data) {
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
