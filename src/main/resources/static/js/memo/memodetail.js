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
                memoSeq: $("#memoSeq").val()
            }
            // TODO: data 조회
            __COMMON.ajax.ajaxLoad("/memo/detaildata", _params, function (p_data) {
                var _result = p_data;

                var _html = [];

                if(p_data != null) {
                    var _memoSeq = "<td>" + __COMMON.utils.isNull(_result.memoSeq, "string") + "</td>";
                    var _memoTitle = "<td>" + __COMMON.utils.isNull(_result.memoTitle, "string") + "</td>";
                    var _memoContent = "<tr><td>" + __COMMON.utils.isNull(_result.memoContent, "string") + "</td></tr>";
                    var _memoWriter = "<tr><td>" + __COMMON.utils.isNull(_result.memoWriter, "string") + "</td></tr>";
                    _html.push("<tr>" + _memoSeq + _memoTitle + "</tr>" + _memoContent + _memoWriter);
                    jQuery("#tbody").html(_html.join(""));
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
            // TODO: 삭제 버튼 기능 추가
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
