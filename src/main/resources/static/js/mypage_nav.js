let path = window.location.pathname;
let mate_path = '/mypage/mate';
let path_box = path.split('/');

$(function () {
  let mate = $('#mate_sub_category');

  // sub Active
  $('.nav-link.mypage-sub').each(function (index, item) {
    if ($(item).attr('href') === path) {
      $(item).addClass('active');
    }
  });

  switch (path_box[2]) {
    case 'main':
      console.log('main');
      $('#nav_main').addClass('active');
      break;
    case 'mate':
      console.log('mate');
      $('#nav_mate').addClass('active');
      $(mate).show();
      break;
  }
});
