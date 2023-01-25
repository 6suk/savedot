$(document).ready(function () {
  let emojiUrl =
    'https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/160/apple/325/';

  $.ajax({
    type: 'post',
    url: 'test',
    data: { ver: 0 },
    success: function (data) {
      $('#card1-top').text('챌린지로 ' + data['top'] + ' 아낀 비용');
      $('img#card1-emoji').attr('src', emojiUrl + data['emoji']);
      $('#card1-emoji-txt').text(data['etxt']);
      $('#card1-bottom').text(data['bottom']);
      $('#card1-right').text(data['right']);
    },
  });

  let ver = 1;
  $('#card1').click(function () {
    $.ajax({
      type: 'post',
      url: 'test',
      data: { ver: ver },
      success: function (data) {
        $('#card1-top').text('챌린지로 ' + data['top'] + ' 아낀 비용');
        $('img#card1-emoji').attr('src', emojiUrl + data['emoji']);
        $('#card1-emoji-txt').text(data['etxt']);
        $('#card1-bottom').text(data['bottom']);
        $('#card1-right').text(data['right']);
        if (ver == 2) {
          ver = 0;
        } else {
          ver++;
        }
      },
    });
  });
});
