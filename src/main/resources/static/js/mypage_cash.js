$('[cash-item]').click(function () {
  $(this).find('[down]').fadeToggle(300);
  $(this).find('[up]').fadeToggle(300);
});

$('#date-search').click(function (event) {
  event.preventDefault();
  let check = requiredCheck();
  if (check) {
    $(this).parent().submit();
  }
});

$('[cash-img]').each(function (index, item) {
  console.log($(item).attr('receipt-src'));

  if ($(item).attr('receipt-src') !== '') {
    $(item).attr('ON', true);
  }
});

// Modal 컨트롤
let modal_bg = $('.modalBg');
let close_btn = $('#closebtn');
let $receipt = $('#receipt-img');

$('[cash-img][ON]').click(function () {
  let $img_src = $(this).attr('receipt-src');
  modal_bg.fadeToggle(250);
  $receipt.attr('src', $img_src);
});

close_btn.click(function () {
  modal_bg.fadeToggle(250);
});
