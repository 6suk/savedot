let today = new Date();
const cash_ctx = $('#CashChart');

// 테스트 데이터
// let labels_main = [09, 10, 11, 12, 01, 02];
// let cashMonth_Data = [891400, 1081700, 1240200, 1162000, 977700, 505900];
// let cashToday_Data = [587500, 820200, 1089600, 1061400, 747000, 505900];
// let cashMonthAll_Data = [744000, 859600, 834700, 939700, 916800, 422900];
// let ageGroup = 20;

let labels_main = [];
let cashMonth_Data = [];
let cashToday_Data = [];
let cashMonthAll_Data = [];
let ageGroup = 0;
let inputdata = '';

let saveDotChart = '';
const $addAllUser = $('#addAllUser');
const $addOneUser = $('#addOneUser');

$(function () {
  $.ajax({
    type: 'post',
    url: '/mypage/chart/cash',
    success: function (data) {
      inputdata = data;
      ageGroup = data.ageGroup;
      delete data.ageGroup;

      for (let i = 0; i < Object.keys(data).length; i++) {
        let dateArr = Object.keys(data)[i].split('-');
        let index = Object.values(data)[i].index;

        labels_main[index] = dateArr[0] + '년 ' + dateArr[1] + '월';
        cashMonth_Data[index] = Object.values(data)[i].data1;
        cashToday_Data[index] = Object.values(data)[i].data2;
        cashMonthAll_Data[index] = Object.values(data)[i].data3;
      }

      config.data.datasets.push(cashMonth);
      config.data.datasets.push(cashToday);
      saveDotChart = new Chart(cash_ctx, config);
      setCard();
    },
  });
});

Chart.defaults.font.family =
  "'Pretendard Variable', Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto, 'Helvetica Neue','Segoe UI', 'Apple SD Gothic Neo', 'Noto Sans KR', 'Malgun Gothic', 'Apple Color Emoji', 'Segoe UI Emoji','Segoe UI Symbol', sans-serif";
Chart.defaults.font.size = 14;

let cashMonth = {
  id: 0,
  type: 'bar',
  label: '나의 총 지출',
  data: cashMonth_Data,
  borderColor: '#e8f0fd',
  backgroundColor: '#e8f0fd',
  order: 1,
};

let cashToday = {
  id: 1,
  type: 'line',
  label: 'n개월 전 나는?',
  data: cashToday_Data,
  pointRadius: [3, 3, 3, 3, 3, 3],
  borderWidth: 2,
  borderColor: 'rgb(54, 162, 235)',
  backgroundColor: 'rgb(54, 162, 235)',
  order: 0,
  tension: 0.4,
};

let cashTodayAll = {
  id: 2,
  type: 'bar',
  label: '내 또래 다른 유저들은?',
  data: cashMonthAll_Data,
  borderColor: '#FFE37D',
  backgroundColor: '#FFE37D',
  order: 2,
};

let config = {
  data: {
    labels: labels_main,
    datasets: [],
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
          if (context.dataset.id === 0) {
            return '#666';
          } else if (context.dataset.id === 1) {
            return 'rgb(54, 162, 235)';
          } else if (context.dataset.id === 2) {
            return '#666';
          }
        },
        anchor: 'end',
        clamp: true,
        clip: true,
        align: 'start',
        offset: function (context) {
          if (context.dataset.id === 0) {
            return -20;
          } else if (context.dataset.id === 1) {
            return 15;
          } else if (context.dataset.id === 2) {
            return 20;
          }
        },
        formatter: function (value, context) {
          return value.toLocaleString('ko-KR') + '원';
        },
        display: function (context) {
          if (context.dataset.id === 1 && context.dataIndex === context.dataset.data.length - 1) {
            return 0;
          } else {
            return 1;
          }
        },
      },
    },
  },
};

$addOneUser.click(function () {
  $addAllUser.toggle();
  $addOneUser.toggle();
  config.data.datasets.pop();
  // config.data.datasets.push(cashMonth);
  config.data.datasets.push(cashToday);
  saveDotChart.update();
});

$addAllUser.click(function () {
  $addAllUser.toggle();
  $addOneUser.toggle();
  config.data.datasets.pop();
  config.data.datasets.push(cashTodayAll);
  saveDotChart.update();
});

function setCard() {
  // 기준 날짜 세팅
  let now = new Date();
  var oneMonthAgo = new Date(now.setMonth(now.getMonth() - 1));
  $('#all-date').text(DateToString(today) + ' 기준');
  $('#user-date').text(DateToString(oneMonthAgo) + ' 기준');
  $('#all-top').text(ageGroup + '대 또래 유저들보다');

  // 지난달 유저 데이터
  let lastMonth_user = cashToday_Data[cashToday_Data.length - 2];

  // 이번달 데이터
  let thisMonth_user = cashToday_Data[cashToday_Data.length - 1];
  let calc = lastMonth_user - thisMonth_user;

  // ver2 : 덜 썼어요!
  if (calc > 0) {
    $('#user-ver2').show();
    $('#user-price').text(calc.toLocaleString() + '원 덜 썼어요!');
    $('#user-bottom').text('비법이 뭔가요? 정말 대단해요!');
  }
  // ver1 : 더 썼어요!
  else {
    $('#user-ver1').show();
    $('#user-price').text(Math.abs(calc).toLocaleString() + '원 더 썼어요!');
    $('#user-bottom').text('남은 시간동안 조금만 더 아껴보아요!');
  }

  // 이번달 전체 데이터
  let thisMonth_All = cashMonthAll_Data[cashMonthAll_Data.length - 1];
  let All_calc = thisMonth_All - thisMonth_user;

  // ver2 : 덜 썼어요!
  if (All_calc > 0) {
    $('#all-ver2').show();
    $('#all-price').text(All_calc.toLocaleString() + '원 덜 썼어요!');
    $('#all-bottom').text('다른 유저들보다 적게 쓰는 편이에요! 대단해요!');
  }
  // ver1 : 더 썼어요!
  else {
    $('#all-ver2').show();
    $('#all-price').text(Math.abs(All_calc).toLocaleString() + '원 더 썼어요!');
    $('#all-bottom').text('다른 유저들보다 많이 쓰는 편이에요!');
  }
}

function DateToString(inputdate) {
  let inputdateArr = inputdate.toLocaleDateString().replace(/\./g, '').replace(/\s/g, '-').split('-');
  inputdateArr[1] = inputdateArr[1].length < 2 ? '0' + inputdateArr[1] : inputdateArr[1];
  inputdateArr[2] = inputdateArr[2].length < 2 ? '0' + inputdateArr[2] : inputdateArr[2];
  inputdate = inputdateArr.join('-');
  return inputdate;
}
