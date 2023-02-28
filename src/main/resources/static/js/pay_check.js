let now = new Date();

// 현재 시간
var year = now.getFullYear(); // 연도
var month = now.getMonth() + 1; // 월(+1해줘야됨)
var day = now.getDate();
var hours = now.getHours();
var minutes = now.getMinutes();

// 출근 시간
var sttHours = 09;
var sttMinutes = 00;

var date1 = new Date(year, month, day, hours, minutes);
var date2 = new Date(year, month, day, sttHours, sttMinutes);

var elapsedMSec = date1.getTime() - date2.getTime();
var elapsedMin = elapsedMSec / 1000 / 60;
elapsedMin = parseInt(elapsedMin);
console.log(date1);
console.log(date2);
console.log(elapsedMin / 60);

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
