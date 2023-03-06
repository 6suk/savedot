$(function () {
  $('[LIKE]').each(function (index, item) {
    let like = $(item).attr('LIKE');
    if (like === '0') {
      $(item).attr('class', 'like fa-regular fa-heart');
    } else {
      $(item).attr('class', 'like fa-solid fa-heart');
    }
  });
});

$('[LIKE]').click(function (event) {
  // event.preventDefault();
  // event.stopPropagation();
  let like = $(this).attr('LIKE');
  let mid = $(this).attr('id');
  let like_index = '#like' + mid;
  let cnt = $(like_index).next().text();

  if (like === '0') {
    $(this).attr('class', 'like fa-solid fa-heart');
    $(like_index)
      .next()
      .text(Number(cnt) + 1);
    likePress(mid);
  }
  if (like === '1') {
    $(this).attr('class', 'like fa-regular fa-heart');
    $(like_index)
      .next()
      .text(Number(cnt) - 1);
    likeDel(mid);
  }
});

function likePress(mid) {
  $.ajax({
    type: 'GET',
    url: '/mate/like/' + mid,
    success: function (data) {
      if (data) {
        $('#' + mid).attr('LIKE', 1);
      }
    },
  });
}

function likeDel(mid) {
  $.ajax({
    type: 'GET',
    url: '/mate/delLike/' + mid,
    success: function (data) {
      if (data) {
        $('#' + mid).attr('LIKE', 0);
      }
    },
  });
}
