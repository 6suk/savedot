$('[data-val]').each(function (index, item) {
  let val = $(item).attr('data-val');

  if (val < 0) {
    val = val.replace('-', '');
    $(item).addClass('ex-minus ex-type');
  } else {
    $(item).addClass('ex-plus ex-type');
  }

  $(item).prepend(val);
});

$('[city]').each(function (index, item) {
  let img = ['hamburger_1f354.png', 'sushi_1f363.png', 'pretzel_1f968.png'];
  let val = $(item).attr('city');
  let src = $(item).find('img').attr('src');

  switch (val) {
    case 'USD':
      $(item)
        .find('img')
        .attr('src', src + img[0]);
      $(item).find('h3').text('미국');
      break;
    case 'JPY':
      $(item)
        .find('img')
        .attr('src', src + img[1]);
      $(item).find('h3').text('일본');
      break;
    case 'EUR':
      $(item)
        .find('img')
        .attr('src', src + img[2]);
      $(item).find('h3').text('유럽');
      break;
  }
});

$(function () {
  window.setTimeout('window.location.reload()', 300000);
});
