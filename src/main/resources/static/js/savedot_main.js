$(function () {
  let $slide = $('#main');
  let $nav_link_active = $('.nav-link.active');
  white_nav($slide);
  $nav_link_active.attr('class', 'nav-link active ver1');
  $('.logo').attr('class', 'logo main-slide-color-ver1');

  $slide.on('slid.bs.carousel', function () {
    let $item = $(this).find('.carousel-item.active').attr('NAV-COLOR');
    switch ($item) {
      case 'ver1':
        white_nav(this);
        $nav_link_active.attr('class', 'nav-link active ver1');
        $('.logo').attr('class', 'logo main-slide-color-ver1');
        break;
      case 'ver2':
        black_nav(this);
        $nav_link_active.attr('class', 'nav-link active');
        $('.logo').attr('class', 'logo');
        break;
      case 'ver0':
        black_nav(this);
        $nav_link_active.attr('class', 'nav-link active ver0');
        $('.logo').attr('class', 'logo main-slide-color-ver0');
        break;
    }
  });

  // var isVisible = false;
  let $counter_top = $('[count-top]');
  let $counter_bottom = $('[count-bottom]');

  let $cnt_top = $('#count-top');
  var isTop = false;
  let $cnt_bottom = $('#count-bottom');
  var isBottom = false;

  $(window).on('scroll', function () {
    if (checkVisible($cnt_top) && !isTop) {
      $counter_top.each(function (index, item) {
        let max = $(item).attr('count-top');
        counter($(item), max);
      });
      isTop = true;
    }
    if (checkVisible($cnt_bottom) && !isBottom) {
      $counter_bottom.each(function (index, item) {
        let max = $(item).attr('count-bottom');
        counter($(item), max);
      });
      isBottom = true;
    }
  });

  function checkVisible(elm, eval) {
    eval = eval || 'object visible';
    var viewportHeight = $(window).height(), // Viewport Height
      scrolltop = $(window).scrollTop(), // Scroll Top
      y = $(elm).offset().top,
      elementHeight = $(elm).height();

    if (eval == 'object visible') return y < viewportHeight + scrolltop && y > scrolltop - elementHeight;
    if (eval == 'above') return y < viewportHeight + scrolltop;
  }

  // 숫자 카운트업
  const counter = ($counter, max) => {
    let now = max;

    const handle = setInterval(() => {
      $counter.text(Math.ceil(max - now).toLocaleString('ko-KR'));

      // 목표수치에 도달하면 정지
      if (now < 1) {
        clearInterval(handle);
      }

      // 증가되는 값이 계속하여 작아짐
      let step__ = now.toString().length > 2 ? 4 : 10;
      const step = now / step__;

      // 값을 적용시키면서 다음 차례에 영향을 끼침
      now -= step;
    }, 80);
  };
});

function white_nav(item) {
  $('#main-nav').addClass('white');
  $(item).removeClass('carousel-dark');
}

function black_nav(item) {
  $('#main-nav').removeClass('white');
  $(item).addClass('carousel-dark');
}
