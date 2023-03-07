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
      mate.push(data.mateSavePrice);
      mate.push(data.mateSum);
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
    if (mate_ver === 2) {
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
const M_DATE = ['지금까지 아낀 비용', '지금까지 거래 완료한 건'];
const M_RIGHT = ['몇건', '비용'];
const M_EMOJI = ['/emoji/laptop_1f4bb.png', '/emoji/trophy_1f3c6.png'];
const M_MAIN_SUM = [1400000, 4500, 20000]; // 금액 기준
const M_MAIN_RIGHT = [' 맥북 Air', '건'];

function mate_change(i) {
  let top = '조각 메이트로 ';
  let calc = '';
  let c_main_final = '';
  let bottom = mate[0].toLocaleString('en-US') + '원을 아꼈어요!';

  switch (i) {
    case 0:
      top += M_DATE[i];
      calc = Math.round((mate[i] / M_MAIN_SUM[i]) * 100) / 100;
      c_main_final = calc + M_MAIN_RIGHT[i]; // main text 최종
      break;
    case 1:
      top += M_DATE[i];
      c_main_final = mate[i] + M_MAIN_RIGHT[i]; // main text 최종
      break;
  }

  // 3. 데이터 세팅
  $('#card2-top').text(top);
  $('img#card2-emoji').attr('src', M_EMOJI[i]);
  $('#card2-emoji-txt').text(c_main_final);
  $('#card2-bottom').text(bottom);
  $('#card2-right').text(M_RIGHT[i]);
}
