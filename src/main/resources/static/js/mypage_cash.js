$('[cash-img]').click(function (event) {
  event.preventDefault();
  event.stopPropagation();
  // Modal 컨트롤
  let modal_bg = $('.modalBg.receipt');
  let $receipt = $('#receipt-img');
  let $img_src = $(this).attr('receipt-src');
  modal_bg.fadeToggle(250);
  $receipt.attr('src', $img_src);
});

$('.cash-item').click(function () {
  let modal_bg = $('.modalBg.edit');
  modal_bg.fadeToggle(250);

  // edit 모달 채워넣기
  // 지출
  if ($(this).hasClass('ver0')) {
    $('#e-category-ver0').attr('selected', true);
  }
  // 수입
  else {
    $('#e-category-ver1').attr('selected', true);
  }

  $('#date').val($(this).parent().attr('id').replace('date-', ''));
  $('#amount').val($(this).find('[cash-amount]').text());
  $('#shopName').val($(this).find('[cash-name]').text());
  $('#memo').val($(this).find('[cash-memo]').text());
  $('#edit-cid').val($(this).attr('id'));
});

$('#editbtn_edit').click(function () {
  $('#editbtn_edit').hide();
  $('#submitbtn_edit').show();
  $('#closebtn_edit').attr('class', 'btn btn-sub mdi');

  let input = $('.inputbox').children();
  $(input).each(function (index, item) {
    $(item).attr('disabled', false);
  });
});

$('#deletebtn_edit').click(function () {
  let check = confirm('삭제하시겠습니까?');
  if (check) {
    location.href = '/cash/delete/' + $('#edit-cid').val();
  }
});

$('#submitbtn_edit').click(function () {
  let check = requiredCheckModal();
  if (check) {
    let amount_ = removeCommas($('#amount').val());
    $('#amount').val(amount_);
    $('#cash-edit-form').submit();
  }
});

$('[cash-item]').click(function () {
  $(this).find('[down]').fadeToggle(300);
  $(this).find('[up]').fadeToggle(300);
});

$('#closebtn').click(function () {
  $('.modalBg.receipt').fadeToggle(250);
});

$('#closebtn_edit').click(function () {
  $('#editbtn_edit').show();
  $('#submitbtn_edit').hide();
  $('#closebtn_edit').attr('class', 'btn btn-main mdi');
  let input = $('.inputbox').children();
  $(input).each(function (index, item) {
    $(item).attr('disabled', true);
  });
  $('.modalBg.edit').fadeToggle(250);
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
