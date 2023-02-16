// 갱신 시간 -> 경제 뉴스
window.onload = function getTime() {
    var d = new Date();	// 현재 날짜와 시간
    var year = d.getFullYear();
    var month = d.getMonth() + 1;
    var day = d.getDate();
    var hur = d.getHours();	
    var min = String(d.getMinutes()).padStart(2, "0");

    var timeBoard = document.getElementById("renewalTime"); // 값이 입력될 공간

    var time = year + ". " + month + ". " + day + ". " + hur + ":" + min + " 갱신";
    
    timeBoard.innerHTML = time;	// 출력
    
    setTimeout(getTime, 300000);	// 5분마다 갱신
}

// 현재 시간 -> 환율 (사용 X)
// function locale (){
//     return new Date().toLocaleString();
// } 
// document.getElementById( 'usingFunction' ).innerHTML = locale(); 

// // 추가로, 실시간 타이머 표시 방법 ㅡ 1000 밀리초(=1초)에 한번씩 함수 실행하기 
// setInterval ( function() {
//     document.getElementById("usingFunction").innerHTML = locale(); 
// } , 1000 );