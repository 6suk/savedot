var idchk = false; // 아이디 검사
var $id = $("#id");
// 아이디 정규식
		$id.on("keyup", function() { // 키보드에서 손을 땠을 때 실행
			var regExp = /^[a-z]+[a-z0-9]{5,15}$/g;

			if (!regExp.test($id.val())) { // id 가 공백인 경우 체크
				idchk = false;
				$id.html("<span id='check'>사용할 수 없는 아이디입니다.</span>");
				$("#check").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
					"font-size" : "10px"
				})
			} else { // 공백아니면 중복체크
				$.ajax({
					type : "POST", // http 방식 
					url : "/user/join/checkid", // ajax 통신 url
					data : { // ajax 내용 => 파라미터 : 값 이라고 생각해도 무방
						"type" : "user",
						"id" : $id.val()
					},
					success : function(data) {
						if (data == 1) { // 1이면 중복
							idchk = false;
							$id.html("<span id='check'>이미 존재하는 아이디입니다</span>")
							$("#check").css({
								"color" : "#FA3E3E",
								"font-weight" : "bold",
								"font-size" : "10px"

							})
							//console.log("중복아이디");
						} else { // 아니면 중복아님
							idchk = true;
							$id.html("<span id='check'>사용가능한 아이디입니다</span>")

							$("#check").css({
								"color" : "#0D6EFD",
								"font-weight" : "bold",
								"font-size" : "10px"

							})
							//console.log("중복아닌 아이디");
						}
					}
				})
			}
		});