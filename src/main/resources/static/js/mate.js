let price1 = $('#price1');
let price2 = $('#price2');
let position_num = $('#position_num');
let trade_type = $('input[name = tradetype]');
let direct_show = $('#direct_show');
let parcel_show = $('#parcel_show');
let parcel_type = $('#parcel_type');
let parcel_price = $('#parcel_price');
let place_name_mate = $('#place_name');

$('#calc_price').click(function () {
  let calc_num = Number(position_num.val()) + 1;
  let calc_price = price1.val() / calc_num;
  price2.val(calc_price);
  if (price2.hasClass('empty')) {
    price2.removeClass('empty');
  }
});

trade_type.change(function () {
  let checked = $('input[name = tradetype]:checked');
  switch (checked.val()) {
    case '0': // 직접거래
      direct_show.show();
      parcel_show.hide();
      if (parcel_price.hasClass('required')) {
        parcel_price.removeClass('required empty');
      }
      place_name_mate.addClass('required');
      break;
    case '1': // 택배거래
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
    case '2': // all
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
    case '0': // 선불
      parcel_price.attr('disabled', false);
      parcel_price.attr('placeholder', '*택배비를 입력해주세요.');
      parcel_price.addClass('required');
      break;
    case '1':
      parcel_price.attr('disabled', true);
      parcel_price.val('');
      parcel_price.attr('placeholder', '착불 선택');
      parcel_price.removeClass('required empty');
      break;
  }
});
