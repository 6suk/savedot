$('[reply]').click(function () {
  // action url
  let grp = $(this).attr('reply');
  let write_area = $(`[grp=${grp}]`);

  write_area.toggle();
  let inner = write_area.find('.m-re-wrap.re-comment-item');

  if (write_area.css('display') !== 'none') {
    let addWriteBox = $('#reply_write').outerHTML();
    inner.append(addWriteBox);
    var offset = $(write_area).offset();
    $('html').animate({ scrollTop: offset.top - $(window).height() / 2 }, 200);
  } else {
    inner.html('');
  }
});

$.fn.outerHTML = function () {
  var el = $(this);
  if (!el[0]) return '';

  if (el[0].outerHTML) {
    return el[0].outerHTML;
  } else {
    var content = el.wrap('<p/>').parent().html();
    el.unwrap();
    return content;
  }
};

let index = 1;

$('[update]').click(function () {
  let rid = $(this).attr('update');
  let input_area = $(`#${rid}`); // 기존 댓글
  let parent = $(this).closest('.re-comment-box');
  let inner = parent.find('.re-comment-item.update'); // 수정창
  let addWriteBox = $('.m-re-wrap #reply_write').outerHTML();
  inner.append(addWriteBox);

  let before_content = input_area.find('.re-desc').text();

  inner.find('textarea').text(before_content);
  inner.find('#secret-tag').attr('id', 'secret-tag' + index);
  inner.find('label').attr('for', 'secret-tag' + index);
  index++;

  let addinput = inner.find('#reply_write');
  addinput.prepend('<input type="hidden" name="rid" value="' + rid + '" />');

  inner.find('[cancel]').show();
  input_area.toggle();
  inner.toggle();

  $('[cancel]').click(function (event) {
    inner.html('');
    input_area.toggle();
    inner.toggle();
  });
});
