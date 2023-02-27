let path = window.location.pathname;
let path_box = path.split('/');

$(function () {
  // Main Nav Active
  $('.nav-link.main-nav').each(function (index, item) {
    let nav_path = $(item).attr('href').split('/');
    if (nav_path[1] === path_box[1]) {
      $(item).addClass('active');
    }
  });

  // Mypage Nav Active
  let mate = $('#mate_sub_category');
  let chart = $('#chart_sub_category');
  $('.nav-link.mypage').each(function (index, item) {
    let mypage_nav_path = $(item).attr('href').split('/');
    if (path_box[1] == mypage_nav_path[1] && mypage_nav_path[2] === path_box[2]) {
      $(item).addClass('active');
      if (path_box[2] == 'mate') {
        $(mate).show();
      } else if (path_box[2] == 'chart') {
        $(chart).show();
      }
    }
  });

  // Mypage Sub Nav Active
  $('.nav-link.mypage-sub').each(function (index, item) {
    if (path === $(item).attr('href')) {
      $(item).addClass('active');
    }
  });
});
