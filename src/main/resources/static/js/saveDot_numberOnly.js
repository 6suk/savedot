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
