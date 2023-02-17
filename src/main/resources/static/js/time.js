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

// 현재 날짜
function locale() {

    let dayStr = new Date().toString();
    let week = dayStr.slice(0, 3);  // 오늘 요일 영어로
    let date = dayStr.slice(4, 15); // 일, 월, 년

    return week + ", " + date;
}
document.getElementById( 'today1' ).innerHTML = locale (); 
document.getElementById( 'today2' ).innerHTML = locale (); 
document.getElementById( 'today3' ).innerHTML = locale (); 