let price1 = $('#price1');
let price2 = $('#price2');
let position_num = $('#position_num');
let trade_type = $('input[name = tradeType]');
let direct_show = $('#direct_show');
let parcel_show = $('#parcel_show');
let parcel_type = $('#parcel_type');
let parcel_price = $('#parcel_price');
let place_name_mate = $('#place_name');

$('#calc_price').click(function () {
  let calc_num = 0;

  if (position_num.val() != null && position_num.val() != '') {
    calc_num = Number(position_num.val()) + 1;
  } else {
    calc_num = 2;
    position_num.val(1);
    if (price2.hasClass('empty')) {
      position_num.removeClass('empty');
    }
  }
  let calc_price = Math.round(removeCommas(price1.val()) / calc_num);
  price2.val(addComma(calc_price));
  if (price2.hasClass('empty')) {
    price2.removeClass('empty');
  }
});

// price, number_only
$(function () {
  let number_only = $('input:text[numberOnly]');
  let price = $('input:text[price]');
  price.keyup(function () {
    $(this).val(
      addComma(
        $(this)
          .val()
          .replace(/[^0-9]/g, '')
      )
    );
  });

  number_only.keyup(function () {
    $(this).val(
      $(this)
        .val()
        .replace(/[^0-9]/g, '')
    );
  });
});

//천단위마다 콤마 생성
function addComma(data) {
  return data.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

// 콤마 삭제
function removeCommas(data) {
  if (!data || data.length == 0) {
    return '';
  } else {
    return data.split(',').join('');
  }
}

trade_type.change(function () {
  let checked = $('input[name = tradeType]:checked');
  switch (checked.val()) {
    case '1': // 직접거래
      direct_show.show();
      parcel_show.hide();
      parcel_price.removeClass('required empty');
      parcel_price.attr('disabled', true);
      place_name_mate.addClass('required');
      break;
    case '2': // 택배거래
      addr_cancle();
      foldDaumPostcode();
      direct_show.hide();
      parcel_show.show();
      place_name_mate.removeClass('required empty');
      if ($('#parcel_type option:selected').val() === '0') {
        parcel_price.addClass('required');
      }
      break;
    case '3': // all
      place_name_mate.addClass('required');
      if ($('#parcel_type option:selected').val() === '0') {
        parcel_price.addClass('required');
      }
      direct_show.show();
      parcel_show.show();
      break;
  }
});

parcel_type.change(function () {
  let type = $('#parcel_type option:selected').val();
  let parcel_price = $('#parcel_price');
  switch (type) {
    case '1': // 선불
      parcel_price.attr('disabled', false);
      parcel_price.attr('placeholder', '*택배비 입력');
      parcel_price.addClass('required');
      break;
    case '2':
      parcel_price.attr('disabled', true);
      parcel_price.val('');
      parcel_price.attr('placeholder', '착불 선택');
      parcel_price.removeClass('required empty');
      break;
  }
});

let tel_type = $('#tel_type');
tel_type.change(function () {
  let type = $('#tel_type option:selected').val();
  let tel_url = $('#tel_url');

  switch (type) {
    case '1': // 오픈채팅
      tel_url.attr('disabled', false);
      tel_url.attr('placeholder', '*오픈채팅 URL 공유');
      tel_url.addClass('required');
      break;
    case '2': // 댓글
      tel_url.val('');
      tel_url.attr('disabled', true);
      tel_url.attr('placeholder', '해당 게시물 댓글 소통');
      tel_url.removeClass('required empty');
      break;
  }
});

let bank = $('#bank');

bank.change(function () {
  $('#accountNumber').addClass('required');
  bank.removeClass('required');
});
