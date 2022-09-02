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
            // TODO sample 이벤트 바인딩
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
            // TODO: data 조회
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
                    alert("Detail 데이터가 존재하지 않습니다.")
                }
            }, false
                , jQuery("#btn") );
        },

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
            // TODO: 수정 버튼 기능 추가
            jQuery("#updateButton").off("click.add").on("click", function(){
                var _$memoTitle = $("#memoTitle");
                var _$memoContent = $("#memoContent");
                var _$memoWriter = $("#memoWriter");
                var _$updateButton = $("#updateButton");
                var _$saveButton = $("#saveButton");

                _$memoTitle.prop("readOnly", false);
                _$memoContent.prop("readOnly", false);
                _$memoWriter.prop("readOnly", false);
                _$updateButton.prop("hidden", true);
                _$saveButton.prop("hidden", false);

            });

            // TODO: 저장 버튼 기능 추가
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

            // TODO: 삭제 버튼 기능 추가
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
            // TODO: 목록가기 버튼 기능 추가
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
