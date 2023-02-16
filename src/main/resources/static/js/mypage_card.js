let challenge = [];
let mate = [];
let challenge_ver = 0;
let mate_ver = 0;

// 1. 데이터 받아오기
$(function () {
  $.ajax({
    type: 'post',
    url: '/mypage/main',
    success: function (data) {
      challenge.push(data.challengeToday);
      challenge.push(data.challengeWeek);
      challenge.push(data.challengeMonth);
      mate.push(data.mateToday);
      mate.push(data.mateWeek);
      mate.push(data.mateMonth);
      challenge_change(challenge_ver++);
      mate_change(mate_ver++);
    },
  });

  $('#card1').click(function () {
    challenge_change(challenge_ver++);
    if (challenge_ver === 3) {
      challenge_ver = 0;
    }
  });
  $('#card2').click(function () {
    mate_change(mate_ver++);
    if (mate_ver === 3) {
      mate_ver = 0;
    }
    console.log(mate_ver);
  });
});

// 2. 데이터 가공 (챌린지)
const DATE = ['오늘', '주간', '월간'];
const RIGHT = ['주간', '월간', '일간'];
const C_EMOJI = ['/emoji/beer-mug.png', '/emoji/poultry-leg.png', '/emoji/red-apple.png'];
const C_MAIN_SUM = [2500, 18000, 1000000]; // 금액 기준
const C_MAIN_LEFT = ['맥주 ', '치킨 ', ''];
const C_MAIN_RIGHT = ['캔', '마리', ' 아이패드'];

function challenge_change(i) {
  let top = '챌린지로 ' + DATE[i] + ' 아낀 비용';
  let calc = Math.round((challenge[i] / C_MAIN_SUM[i]) * 100) / 100;
  let c_main_final = C_MAIN_LEFT[i] + calc + C_MAIN_RIGHT[i]; // main text 최종
  let bottom = '약 ' + challenge[i].toLocaleString('en-US') + '원을 아꼈어요!';

  // 3. 데이터 세팅
  $('#card1-top').text(top);
  $('img#card1-emoji').attr('src', C_EMOJI[i]);
  $('#card1-emoji-txt').text(c_main_final);
  $('#card1-bottom').text(bottom);
  $('#card1-right').text(RIGHT[i]);
}

// 2. 데이터 가공 (메이트)
const M_EMOJI = ['/emoji/hot-beverage_2615.png', '/emoji/pizza_1f355.png', '/emoji/laptop_1f4bb.png'];
const M_MAIN_SUM = [4500, 20000, 1400000]; // 금액 기준
const M_MAIN_LEFT = ['커피 ', '피자 ', ''];
const M_MAIN_RIGHT = ['잔', '판', ' 맥북 Air'];

function mate_change(i) {
  let top = '조각 메이트로 ' + DATE[i] + ' 아낀 비용';
  let calc = Math.round((mate[i] / M_MAIN_SUM[i]) * 100) / 100;
  let c_main_final = M_MAIN_LEFT[i] + calc + M_MAIN_RIGHT[i]; // main text 최종
  let bottom = '약 ' + mate[i].toLocaleString('en-US') + '원을 아꼈어요!';

  // 3. 데이터 세팅
  $('#card2-top').text(top);
  $('img#card2-emoji').attr('src', M_EMOJI[i]);
  $('#card2-emoji-txt').text(c_main_final);
  $('#card2-bottom').text(bottom);
  $('#card2-right').text(RIGHT[i]);
}
