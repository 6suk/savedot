$('.btn.fa-chevron-right').click(function () {
  let mate_card = $(this).parents('.mate_card');
  let control_box = mate_card.find('.mypage-mate-card-box');

  let scrollX = $(control_box).scrollLeft();
  $(control_box).scrollLeft(scrollX + 800);
});

$('.btn.fa-chevron-left').click(function () {
  let mate_card = $(this).parents('.mate_card');
  let control_box = mate_card.find('.mypage-mate-card-box');

  let scrollX = $(control_box).scrollLeft();
  $(control_box).scrollLeft(scrollX - 800);
});
