// 현재 시간
let now = new Date();
var year = now.getFullYear(); // 연도
var month = now.getMonth() + 1; // 월(+1해줘야됨)
var day = now.getDate();
var hours = now.getHours();
var minutes = now.getMinutes();
var nowDate = new Date(year, month, day, hours, minutes);

function SetPay(pay, sumNowExpense, goTime, leaveTime) {
  counter($('#sumNowExpense'), sumNowExpense);

  // 출근 시간
  var goDate = new Date(year, month, day, goTime[0], goTime[1]);

  // 퇴근시간
  var leaveDate = new Date(year, month, day, leaveTime[0], leaveTime[1]);

  // 현재까지 근무 시간
  var workSec = nowDate.getTime() - goDate.getTime();
  var workCalcMin = workSec / 1000 / 60;
  let workHours = Math.floor(workCalcMin / 60); //시간
  let workMinutes = workCalcMin % 60; // 분

  // 근무 시간(분) 계산
  let calcSec = leaveDate.getTime() - goDate.getTime();
  let calcMinutes = calcSec / 1000 / 60;

  // 금액 입력
  // 월의 마지막 날짜 (28, 30, 31)
  let lastDate = new Date(year, month, 0).getDate();

  let pc = 0;
  let minitesPay = 0;
  let nowPay = 0;

  if (nowDate < leaveDate) {
    // 퍼센트
    pc = Math.floor((workCalcMin / calcMinutes) * 100);

    // 1분당 페이 계산
    minitesPay = pay / lastDate / calcMinutes;
    nowPay = Math.floor(workCalcMin * minitesPay);
  } else {
    pc = 100;
    nowPay = pay / lastDate;
  }

  $('[num]').attr('num', pc);
  counter($('#nowPay'), nowPay);
  let total = nowPay - sumNowExpense;
  counter($('#total'), total);
}

// Progress Bar
function move() {
  var $bar = $('#myBar');
  var $label = $('#label');
  var $labelGroup = $('#label-group');
  var width = 0;
  var id = setInterval(frame, 30);
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
  $.ajax({
    type: 'post',
    url: '/pay',
    success: function (data) {
      let pay = data.pay;
      let sumNowExpense = data.sumNowExpense;

      if (pay !== 0 && data.workIn !== null && data.workOut !== null) {
        let goTime = data.workIn.split(':');
        let leaveTime = data.workOut.split(':');
        SetPay(pay, sumNowExpense, goTime, leaveTime);
        move();
      } else {
        $('#modal_update').addClass('on');
        staticValue();
      }
    },
    error: function (error) {
      $('#modal_login').addClass('on');
      staticValue();
    },
  });
});

function staticValue() {
  counter($('#nowPay'), 89000);
  counter($('#sumNowExpense'), 8000);
  counter($('#total'), 89000 - 8000);
  $('[num]').attr('num', 48);
  move();
}

// 숫자 카운트업
const counter = ($counter, max) => {
  let now = max;

  const handle = setInterval(() => {
    $counter.text(Math.ceil(max - now).toLocaleString('ko-KR'));

    // 목표수치에 도달하면 정지
    if (now < 1) {
      clearInterval(handle);
    }

    // 증가되는 값이 계속하여 작아짐
    let step__ = now.toString().length > 2 ? 4 : 10;
    const step = now / step__;

    // 값을 적용시키면서 다음 차례에 영향을 끼침
    now -= step;
  }, 50);
};
