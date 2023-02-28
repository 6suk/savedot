function checkNickname(){
    var nickname = $('#nickname').val(); 					// nickname값이 "nickname"인 입력란의 값을 저장
    $.ajax({
        url:'/user/join/nicknameCheck', 					// Controller에서 요청 받을 주소
        type:'post', 										// POST 방식으로 전달
        data:{nickname:nickname},
        success:function(c){ 								// 컨트롤러에서 넘어온 cnt값을 받는다 
            if(c == '0'){ 									// c가 1이 아니면(=0일 경우) -> 사용 가능한 닉네임 
                $('.nickname_ok').css("display","inline-block"); 
                $('.nickname_already').css("display", "none");
            } else { 										// c가 1일 경우 -> 이미 존재하는 닉네임
                $('.nickname_already').css("display","inline-block");
                $('.nickname_ok').css("display", "none");
            }
        },
        error:function(){
            alert("에러입니다");
        }
    });
 };