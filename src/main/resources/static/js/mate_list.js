// 거래 방법

$('[TradeType]').each(function (index, item) {
  switch ($(item).attr('TradeType')) {
    case '1':
      $(item).children('[Trade][ver0]').show();
      break;
    case '2':
      $(item).children('[Trade][ver1]').show();
      break;
    case '3':
      $(item).children('[Trade]').show();
      break;
  }
});
