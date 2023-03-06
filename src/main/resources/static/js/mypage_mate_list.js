// 카드 클릭 시 디테일 페이지 이동
$('.mate-info-group').click(function () {
  let mid = $(this).parent().attr('mid');
  location.href = `/mate/detail/${mid}`;
});

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

$('input[name="state"]').change(function (event) {
  event.preventDefault();
  let $state_get = $('input[name="state"]:checked').val();

  let search = searchURL();
  search.set('state', $state_get);
  if (search.get('query') != null) {
    search.set('query', search.get('query'));
  }

  location.href = '?' + search;
});

$('#query-search-btn').click(function () {
  let search = location.search === '' ? searchURL() : new URLSearchParams(location.search);
  search.set('query', $('#query-search').val());

  location.href = '?' + search;
});

// 기본 URL
function searchURL() {
  let search = new URLSearchParams(location.search);
  search.set('state', 'A');
  if (search.get('query') != null) {
    search.set('query', search.get('query'));
  }
  return search;
}

$(function () {
  let search = new URLSearchParams(location.search);
  let search_state = search.get('state');

  switch (search_state) {
    case 'A':
      $('#all').attr('checked', true);
      break;
    case '0':
      $('#ing').attr('checked', true);
      break;
    case '1':
      $('#end').attr('checked', true);
      break;
  }

  let search_q = search.get('query');
  $('#query-search').val(search_q);
});
