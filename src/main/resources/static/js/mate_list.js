// 거래 방법

$('[TradeType]').each(function (index, item) {
  switch ($(item).attr('TradeType')) {
    case '1':
      $(item).children('[Trade][ver1]').show();
      break;
    case '2':
      $(item).children('[Trade][ver2]').show();
      break;
    case '3':
      $(item).children('[Trade]').show();
      break;
  }
});

$('[LIKE]').click(function (event) {
  event.preventDefault();
  event.stopPropagation();
});

// 카드 클릭 시 디테일 페이지 이동
$('.mate-flex-item').click(function () {
  let mid = $(this).attr('mid');
  location.href = `/mate/detail/${mid}`;
});

$(function () {
  let area1 = $('#area-search');
  area1.attr('disabled', false);
  $('.input-btn').removeClass('empty');
  $('#area1_location_value').remove();
  $('#area2_location_value').remove();

  var request = new XMLHttpRequest();
  request.open('GET', 'https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=*00000000');
  request.send();
  request.onload = function () {
    let json = JSON.parse(request.response);
    let arr = json.regcodes;

    for (let i = 0; i < arr.length; i++) {
      let areas = arr[i];
      area1.append(`<li>${areas.name}</li>`);
    }
  };
});
