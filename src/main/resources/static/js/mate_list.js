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

// 카드 클릭 시 디테일 페이지 이동
$('.mate-info-group').click(function () {
  let mid = $(this).parent().attr('mid');
  location.href = `/mate/detail/${mid}`;
});

// 지역 - 전체 선택 시 다른 지역 선택 해제
$('[area-all]').click(function () {
  $('[area-ele]').each(function (index, item) {
    $(item).prop('checked', false);
  });
});

// 지역 - 지역 선택 시 전체 선택 해제
$('[area-ele]').click(function () {
  if ($('input[name="area"]:checked').length == 0) {
    $('[area-all]').prop('checked', true);
  } else {
    $('[area-all]').prop('checked', false);
  }
});

$(function () {
  // 썸네일 없을 때
  $('.thum').each(function (index, item) {
    let src = $(item).children().attr('src');

    if (src === '') {
      $(item).css('cursor', 'pointer');
      $(item).click(function () {
        let mid = $(item).closest('.mate-flex-item').attr('mid');
        location.href = `/mate/detail/${mid}`;
      });
    }
  });

  let search = location.search;
  var params = new URLSearchParams(search);

  // param에 맞춰 check
  if (search !== '') {
    let checkinput = $('.form-check-input.mate-search');
    checkinput.attr('checked', false);

    let search_category = params.get('category').split('-');
    let search_state = params.get('state');
    let search_area = params.get('area').split('-');
    let search_query = params.get('query');

    $('#query-search').val(search_query);

    for (let sc of search_category) {
      let ele_category = $('#category-search').find('.form-check-input.mate-search');
      for (let ec of ele_category) {
        if ($(ec).val() === sc) {
          $(ec).attr('checked', true);
        }
      }
    }

    for (let sa of search_area) {
      let ele_area = $('#area-search').find('.form-check-input.mate-search');
      for (let ea of ele_area) {
        if ($(ea).val() === sa) {
          $('#area--search').addClass('show');
          $(ea).attr('checked', true);
        }
      }
    }

    let ele_state = $('#state-search').find('.form-check-input.mate-search');
    for (let ec of ele_state) {
      if ($(ec).val() === search_state) {
        $(ec).attr('checked', true);
      }
    }
  }

  // param에 맞춰 타이틀 변경
  let $h4 = $('#search-title');

  if (search === '' || params.toString() === searchURL().toString()) {
    $h4.text('전체보기');
  } else {
    $h4.text('검색결과');
  }
});

// submit
$('.form-check-input.mate-search').change(function (event) {
  event.preventDefault();
  let $category_get = $('input[name="category"]:checked');
  let $area_get = $('input[name="area"]:checked');
  let $state_get = $('input[name="state"]:checked').val();

  let data = [];
  let tmp = '';
  $category_get.each(function (index, item) {
    if (index === 0) {
      tmp = '';
      tmp += $(item).val();
    } else {
      tmp += '-' + $(item).val();
    }
    data[0] = tmp;
  });

  $area_get.each(function (index, item) {
    if (index === 0) {
      tmp = '';
      tmp += $(item).val();
    } else {
      tmp += '-' + $(item).val();
    }
    data[1] = tmp;
  });

  let search = searchURL();
  search.set('state', $state_get);
  search.set('area', data[1]);
  if (search.get('query') != null) {
    search.set('query', search.get('query'));
  }

  // 카테고리가 모두 해제 되었을 때
  if ($('input[name="category"]:checked').length == 0) {
    $('input[name="category"]').each(function (index, item) {
      $(item).prop('checked', true);
    });
    location.href = '?' + search;
  } else {
    search.set('category', data[0]);
    location.href = '?' + search;
  }

  // 장소가 모두 해제 되었을 때
  if ($('input[name="area"]:checked').length == 0) {
    $('[area-all]').prop('checked', true);
    search.set('area', '00');
    location.href = '?' + search;
  }
});

$('#query-search-btn').click(function () {
  let search = location.search === '' ? searchURL() : new URLSearchParams(location.search);
  search.set('query', $('#query-search').val());
  location.href = '?' + search;
});

// 기본 URL
function searchURL() {
  let search = new URLSearchParams(location.search);
  search.set('category', '0-1');
  search.set('state', 'A');
  search.set('area', '00');
  if (search.get('query') != null) {
    search.set('query', search.get('query'));
  }
  return search;
}

$.fn.serializeObject = function () {
  'use strict';
  var result = {};
  var extend = function (i, element) {
    var node = result[element.name];
    if ('undefined' !== typeof node && node !== null) {
      if ($.isArray(node)) {
        node.push(element.value);
      } else {
        result[element.name] = [node, element.value];
      }
    } else {
      result[element.name] = element.value;
    }
  };

  $.each(this.serializeArray(), extend);
  return result;
};

$('[search-item]').click(function (event) {
  event.preventDefault();
  $(this).find('[down]').fadeToggle(300);
  $(this).find('[up]').fadeToggle(300);
});
