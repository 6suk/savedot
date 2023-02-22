// 현재 날짜 가져오기
let date_ = new Date();
let year = date_.getFullYear();
let month = date_.getMonth() + 1;
let date = date_.getDate();

// left 버튼 클릭 시
$('#left-btn').click(function () {
  let input_year__ = $('#Y').val();
  let input_month__ = $('#N').val();
  let input_year = Number(input_year__);
  let input_month = Number(input_month__);
  let output_year = 0;
  let output_month = 0;

  if (input_month - 1 === 0) {
    output_year = input_year - 1;
    output_month = 12;
  } else {
    output_year = input_year;
    output_month = input_month - 1;
  }
  location.href = '/mypage/main?Y=' + output_year + '&M=' + output_month;
});

$('#year-left-btn').click(function () {
  let input_year__ = $('#Y').val();
  let input_month__ = $('#N').val();
  let input_year = Number(input_year__);
  let input_month = Number(input_month__);
  let output_year = 0;
  let output_month = 0;

  output_year = input_year - 1;
  output_month = input_month;

  location.href = '/mypage/main?Y=' + output_year + '&M=' + output_month;
});

// right 버튼 클릭 시
$('#right-btn').click(function () {
  let input_year__ = $('#Y').val();
  let input_month__ = $('#N').val();
  let input_year = Number(input_year__);
  let input_month = Number(input_month__);
  let output_year = 0;
  let output_month = 0;

  if (input_month + 1 === 13) {
    output_year = input_year + 1;
    output_month = 1;
  } else {
    output_year = input_year;
    output_month = input_month + 1;
  }
  location.href = '/mypage/main?Y=' + output_year + '&M=' + output_month;
});

$('#year-right-btn').click(function () {
  let input_year__ = $('#Y').val();
  let input_month__ = $('#N').val();
  let input_year = Number(input_year__);
  let input_month = Number(input_month__);
  let output_year = 0;
  let output_month = 0;

  output_year = input_year + 1;
  output_month = input_month;

  location.href = '/mypage/main?Y=' + output_year + '&M=' + output_month;
});

// Success Count 아이콘 변경
$('[SaveSuccess]').each(function (index, item) {
  let icon_name = '';
  let success_value = $(item).attr('SaveSuccess');
  let Num = Number(success_value);

  switch (Num) {
    case 0:
      break;
    case 1:
      icon_name = 'fire-front-clay';
      $(item).append(
        '<object type="image/svg+xml" data="/emoji/' +
          icon_name +
          '.svg"><img src="/emoji/' +
          icon_name +
          '.png" alt="" /></object>'
      );
      break;
    case 2:
      icon_name = 'fire-front-color';
      $(item).append(
        '<object type="image/svg+xml" data="/emoji/' +
          icon_name +
          '.svg"><img src="/emoji/' +
          icon_name +
          '.png" alt="" /></object>'
      );
      break;
    case 3:
      icon_name = 'fire-front-premium';
      $(item).append(
        '<object type="image/svg+xml" data="/emoji/' +
          icon_name +
          '.svg"><img src="/emoji/' +
          icon_name +
          '.png" alt="" /></object>'
      );
      break;
  }

  if (Num > 3) {
    icon_name = 'fire-front-premium';
    $(item).append(
      '<object type="image/svg+xml" data="/emoji/' +
        icon_name +
        '.svg"><img src="/emoji/' +
        icon_name +
        '.png" alt="" /></object>'
    );
  }
});
