let today = new Date();
const cash_ctx = $('#CashChart');

// // 테스트 데이터
// let labels_main = [09, 10, 11, 12, 01, 02];
// let labels_sub = ['대중교통 이용하기', '커피값 줄이기', '배달 대신 집밥먹기'];

// // 챌린지로 아낀비용(User)
// let data1 = [24000, 122000, 50000, 98000, 102000, 33000];

// // 챌린지로 아낀비용 현재일자(User)
// let data2 = [18000, 32000, 68000, 21400, 57000, 33000];

// // 챌린지로 아낀비용(All)
// let data3 = [32000, 44000, 88000, 61400, 47000, 23000];

// // 많이 참여한 챌린지(User)
// let data4 = [10, 20, 12];

// // 많이 참여한 챌린지(All)
// let data5 = [12, 10, 22];

// let ageGroup = 20;

let labels_main = [];
let labels_sub = ['대중교통 이용하기', '배달 대신 집밥먹기', '커피값 줄이기'];

// 챌린지로 아낀비용(User)
let data1 = [];

// 챌린지로 아낀비용 현재일자(User)
let data2 = [];

// 챌린지로 아낀비용(All)
let data3 = [];

// 많이 참여한 챌린지(User)
let data4 = [];

// 많이 참여한 챌린지(All)
let data5 = [];

let ageGroup = 0;
let saveDotChart = '';
let inputdata = '';
let startDate = '';

$(function () {
  $.ajax({
    type: 'post',
    url: '/mypage/chart/challenge',
    success: function (data) {
      ageGroup = data.ageGroup;
      delete data.ageGroup;
      for (let i = 1; i <= labels_sub.length; i++) {
        data4[i - 1] = data.data4[i];
        data5[i - 1] = data.data5[i];
      }
      delete data.data4;
      delete data.data5;
      for (let i = 0; i < Object.keys(data).length; i++) {
        let dateArr = Object.keys(data)[i].split('-');
        let index = Object.values(data)[i].index;
        if (index === 0) {
          startDate = Object.keys(data)[i] + '-01';
        }
        labels_main[index] = dateArr[0] + '년 ' + dateArr[1] + '월';
        data1[index] = Object.values(data)[i].data1;
        data2[index] = Object.values(data)[i].data2;
        data3[index] = Object.values(data)[i].data3;
      }
      saveDotChart = new Chart(cash_ctx, config);
      setCard();
      $('#date_term').text(startDate + ' ~ ' + DateToString(today));
    },
  });
});

Chart.defaults.font.family =
  "'Pretendard Variable', Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto, 'Helvetica Neue','Segoe UI', 'Apple SD Gothic Neo', 'Noto Sans KR', 'Malgun Gothic', 'Apple Color Emoji', 'Segoe UI Emoji','Segoe UI Symbol', sans-serif";
Chart.defaults.font.size = 14;

let challenge_data1 = {
  id: 1,
  type: 'bar',
  label: '챌린지로 아낀 비용',
  data: data1,
  borderColor: 'rgba(255,227,125,0.8)',
  backgroundColor: 'rgba(255,227,125,0.8)',
  order: 1,
};

let challenge_data2 = {
  id: 2,
  type: 'line',
  label: 'n개월 전 나는?',
  data: data2,
  pointRadius: [3, 3, 3, 3, 3, 3],
  borderWidth: 2,
  // borderColor: '#ff6b00',
  // backgroundColor: '#ff6b00',
  borderColor: '#FF9800',
  backgroundColor: '#FF9800',
  order: 0,
  tension: 0.4,
};

let challenge_data3 = {
  id: 3,
  type: 'bar',
  label: '내 또래 다른 유저들은?',
  data: data3,
  borderColor: '#e8f0fd',
  backgroundColor: '#e8f0fd',
  order: 2,
};

let challenge_data4 = {
  id: 4,
  label: '내가 참여한 챌린지',
  data: data4,
  borderColor: 'rgb(54, 162, 235)',
  pointBackgroundColor: 'rgb(54, 162, 235)',
  pointRadius: 5,
  borderWidth: 2,
  backgroundColor: 'rgba(54, 162, 235, 0.2)',
  order: 2,
};

let challenge_data5 = {
  id: 5,
  label: '내 또래 다른 유저들은?',
  data: data5,
  borderColor: 'rgb(255, 227, 125)',
  pointBackgroundColor: 'rgb(255, 227, 125)',
  pointStyle: 'rect',
  pointRadius: 6,
  borderWidth: 2,
  backgroundColor: 'rgba(255, 227, 125, 0.5)',
  order: 3,
};

let config = {
  id: 'ver1',
  data: {
    labels: labels_main,
    datasets: [challenge_data1, challenge_data2],
  },
  plugins: [ChartDataLabels],
  options: {
    scales: {
      y: {
        display: true,
        type: 'linear',
        position: 'left',
        grace: '5%',
        grid: {
          display: true,
          borderWidth: 0,
        },
        beginAtZero: true,
        ticks: {
          callback: function (label, index, labels) {
            return label / 10000 + '만원';
          },
        },
      },
      x: {
        grid: {
          display: false,
        },
        ticks: {},
      },
    },
    plugins: {
      datalabels: {
        color: function (context) {
          if (context.dataset.id === 1) {
            return '#666';
          } else if (context.dataset.id === 2) {
            return '#FF8900';
          } else if (context.dataset.id === 3) {
            return '#666';
          }
        },
        anchor: 'end',
        clamp: true,
        clip: true,
        align: 'start',
        offset: function (context) {
          if (context.dataset.id === 1) {
            return -20;
          } else if (context.dataset.id === 2) {
            return 15;
          } else if (context.dataset.id === 3) {
            return 20;
          }
        },
        formatter: function (value, context) {
          return value.toLocaleString('ko-KR') + '원';
        },
        display: function (context) {
          if (context.dataset.id === 2 && context.dataIndex === context.dataset.data.length - 1) {
            return 0;
          } else {
            return 1;
          }
        },
      },
    },
  },
};

let config2 = {
  id: 'ver2',
  type: 'radar',
  data: {
    labels: labels_sub,
    datasets: [challenge_data4, challenge_data5],
  },
  options: {
    scales: {
      r: {
        angleLines: {
          display: false,
        },
        suggestedMin: 0,
        grace: '30%',
        pointLabels: {
          font: {
            size: 16,
            weight: 400,
          },
          centerPointLabels: true,
          padding: 20,
          color: '#333',
        },
      },
    },
  },
};

const $addAllUser = $('#addAllUser');
const $addOneUser = $('#addOneUser');
const $addType = $('#addType');

// 챌린지로 아낀비용 클릭 시
$addOneUser.click(function () {
  $addAllUser.toggle();
  $addOneUser.toggle();
  $('#challenge_title').text('6개월간 챌린지로 아낀 비용');

  config.data.datasets.splice(0);
  config.data.datasets.push(challenge_data1);
  config.data.datasets.push(challenge_data2);

  if (saveDotChart.config._config.id == 'ver2') {
    saveDotChart.destroy();
    saveDotChart = new Chart(cash_ctx, config);
  } else {
    saveDotChart.update();
  }
});

// 내 또래 다른 유저들은? 클릭 시
$addAllUser.click(function () {
  $addAllUser.toggle();
  $addOneUser.toggle();
  $('#challenge_title').text('6개월간 챌린지로 아낀 비용');

  config.data.datasets.splice(0);
  config.data.datasets.push(challenge_data1);
  config.data.datasets.push(challenge_data3);

  if (saveDotChart.config._config.id == 'ver2') {
    saveDotChart.destroy();
    saveDotChart = new Chart(cash_ctx, config);
  } else {
    saveDotChart.update();
  }
});

// 많이 참여한 챌린지 클릭 시
$addType.click(function () {
  $('#challenge_title').text('6개월간 많이 참여한 챌린지');

  saveDotChart.destroy();
  saveDotChart = new Chart(cash_ctx, config2);
});

function setCard() {
  // 기준 날짜 세팅
  let now = new Date();
  var oneMonthAgo = new Date(now.setMonth(now.getMonth() - 1));
  $('#all-date').text(DateToString(today) + ' 기준');
  $('#user-date').text(DateToString(oneMonthAgo) + ' 기준');
  $('#all-top').text(ageGroup + '대 또래 유저들보다');

  // 지난달 유저 데이터
  let lastMonth_user = data2[data2.length - 2];

  // 이번달 데이터
  let thisMonth_user = data2[data2.length - 1];
  let calc = thisMonth_user - lastMonth_user;

  // ver2 : 더 아꼈어요!
  if (calc > 0) {
    $('#user-ver2').show();
    $('#user-price').text(calc.toLocaleString() + '원 더 아꼈어요!');
    $('#user-bottom').text('챌린지 고수시군요?');
  }
  // ver1 : 덜 아꼈어요!!
  else {
    $('#user-ver1').show();
    $('#user-price').text(Math.abs(calc).toLocaleString() + '원 덜 아꼈어요!');
    $('#user-bottom').text('남은 시간동안 챌린지를 더 참여해볼까요?');
  }

  // 이번달 전체 데이터
  let thisMonth_All = data3[data3.length - 1];
  let All_calc = thisMonth_user - thisMonth_All;

  // ver2 : 더 아꼈어요!
  if (All_calc > 0) {
    $('#all-ver2').show();
    $('#all-price').text(All_calc.toLocaleString() + '원 더 아꼈어요!');
    $('#all-bottom').text('다른 유저들보다 챌린지 참여율이 높은 편이에요!');
  }
  // ver1 : 덜 아꼈어요!
  else {
    $('#all-ver1').show();
    $('#all-price').text(Math.abs(All_calc).toLocaleString() + '원 덜 아꼈어요!');
    $('#all-bottom').text('다른 유저들보다 챌린지 참여율이 낮은 편이에요!');
  }
}

function DateToString(inputdate) {
  let inputdateArr = inputdate.toLocaleDateString().replace(/\./g, '').replace(/\s/g, '-').split('-');
  inputdateArr[1] = inputdateArr[1].length < 2 ? '0' + inputdateArr[1] : inputdateArr[1];
  inputdateArr[2] = inputdateArr[2].length < 2 ? '0' + inputdateArr[2] : inputdateArr[2];
  inputdate = inputdateArr.join('-');
  return inputdate;
}
