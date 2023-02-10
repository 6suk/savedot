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
  let calc_num = Number(position_num.val()) + 1;
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
      if (parcel_price.hasClass('required')) {
        parcel_price.removeClass('required empty');
      }
      place_name_mate.addClass('required');
      break;
    case '2': // 택배거래
      foldDaumPostcode();
      direct_show.hide();
      parcel_show.show();
      if (place_name_mate.hasClass('required')) {
        parcel_price.removeClass('required empty');
      }
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
      parcel_price.attr('placeholder', '*택배비를 입력해주세요.');
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
