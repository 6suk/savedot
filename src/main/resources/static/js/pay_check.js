const pay = 2800000;

// 현재 시간
let now = new Date();
let nowhours = 10; // 테스트 데이터
let nowMiniutes = 10; // 테스트 데이터
var year = now.getFullYear(); // 연도
var month = now.getMonth() + 1; // 월(+1해줘야됨)
var day = now.getDate();
var hours = now.getHours();
var minutes = now.getMinutes();
var nowDate = new Date(year, month, day, nowhours, nowMiniutes);

// 출근 시간
var goHours = 09;
var goMinutes = 00;
var goDate = new Date(year, month, day, goHours, goMinutes);

// 퇴근시간
var leaveHours = 06;
var leaveMinutes = 00;
var leaveDate = new Date(year, month, day, leaveHours, leaveMinutes);

var workSec = nowDate.getTime() - goDate.getTime();
var workCalcMin = workSec / 1000 / 60;

// 현재까지 근무 시간 (시간, 분)
let workHours = Math.floor(workCalcMin / 60);
let workMinutes = workCalcMin % 60;

calcMinutesPay();

// 현재까지 Pay 계산
function calcMinutesPay() {
  // 월의 마지막 날짜 (28, 30, 31)
  let lastDate = new Date(year, month, 0).getDate();

  // 근무 시간(분) 계산
  let calcSec = goDate.getTime() - leaveDate.getTime();
  let calcMinutes = calcSec / 1000 / 60;

  // 1분당 페이 계산
  let minitesPay = pay / lastDate / calcMinutes;
  let nowPay = workCalcMin * minitesPay;

  return Math.floor(nowPay);
}

// Progress Bar
function move() {
  var $bar = $('#myBar');
  var $label = $('#label');
  var $labelGroup = $('#label-group');
  var width = 0;
  var id = setInterval(frame, 40);
  let num = $label.attr('num');

  function frame() {
    if (width >= num) {
      clearInterval(id);
    } else {
      width++;
      $bar.width(width + '%');
      $labelGroup.width(width + '%');
      $label.text(width * 1 + '%');
    }
  }
}

$(function () {
  move();
});
