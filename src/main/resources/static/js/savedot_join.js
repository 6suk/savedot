// required 회원가입
$('#join-btn').click(function (event) {
  event.preventDefault();

  let check1 = requiredJoinCheck(); // 중복 검사 및 패스워드 검사에 실패된게 없는지 먼저 체크
  let check2 = requiredCheck(); // required 검사

  if ($('#email-input').hasClass('empty') && $('#email-box').hasClass('empty')) {
    $('#email-input').removeClass('empty');
  }

  outputEmail();

  if (check1 && check2) {
    $('#join-form').submit(); // submit
  } else {
    // 입력 실패한 곳으로 돌아가기
    var offset = $('.empty').offset();
    $('html').animate({ scrollTop: offset.top - $(window).height() / 2 }, 200);
  }
});

// required 정보수정
$('#update-btn').click(function (event) {
  event.preventDefault();

  let check1 = requiredJoinCheck();
  let check2 = requiredCheck(); // required 검사

  if ($('#email-input').hasClass('empty') && $('#email-box').hasClass('empty')) {
    $('#email-input').removeClass('empty');
  }

  outputEmail();

  if (check1 && check2) {
    $('#update-form').submit(); // submit
  } else {
    // 입력 실패한 곳으로 돌아가기
    var offset = $('.empty').offset();
    $('html').animate({ scrollTop: offset.top - $(window).height() / 2 }, 200);
  }
});

// 아이디, 닉네임 중복검사 + 패스워드 동일 여부 검사
$('.j-check').on('propertychange change paste input', function () {
  let key = $(this).attr('id');
  let check = false;

  if (key === 'pwd' || key === 'pwd2') {
    let pwd1 = $('input[type="password"]')[0];
    let pwd2 = $('input[type="password"]')[1];
    check = pwdCheck($(pwd1).val(), $(pwd2).val());
    if (check && $(this).hasClass('empty')) {
      $('#pwd').removeClass('empty');
      $('#pwd2').removeClass('empty');
    }
  }

  if (key === 'id' || key === 'nickname') {
    let value = $(this).val();
    let url = { id: '/user/join/idCheck', nickname: '/user/join/nicknameCheck' };
    let data = {};
    data[key] = value;
    check = joinCheck(url[key], data);
    if (check && $(this).hasClass('empty')) {
      $(this).removeClass('empty');
    }
  }

  if (check) {
    $(this).siblings('.check-ok').show();
    $(this).siblings('.check-none').hide();
  } else {
    $(this).siblings('.check-ok').hide();
    $(this).siblings('.check-none').show();
  }
});

// 이메일 직접 입력 시
$('#email').change(function () {
  let manual = $(this).val() === '_manual';
  if (manual) {
    $(this).hide();
    $('#manual').find('input[type="text"]').addClass('required');
    $('#manual').siblings('#email').removeClass('required');
    $('#output-email').attr('email-type', 1);
    $('#manual').show();
  }
});

$('#manual-cancel').click(function () {
  $('#email option:eq(0)').prop('selected', true);
  $('#manual').find('input[type="text"]').removeClass('required');
  $('#manual').siblings('#email').addClass('required');
  $('#output-email').attr('email-type', 0);
  $('#manual').hide();
  $('#email').show();
});

function joinCheck(url, data) {
  let check = false;
  $.ajax({
    url: url, // Controller에서 요청 받을 주소
    type: 'post', // POST 방식으로 전달
    data: data,
    async: false,
    success: function (cnt) {
      // 컨트롤러에서 넘어온 cnt값을 받는다
      if (cnt == '0') {
        check = true;
      } else {
      }
    },
    error: function () {
      alert('에러입니다');
    },
  });
  return check;
}

function pwdCheck(pwd1, pwd2) {
  if (pwd1 === pwd2) {
    return true;
  } else {
    return false;
  }
}

function requiredJoinCheck() {
  let check = [];
  let tmp = 0;
  $('.check-none').each(function (index, item) {
    let display = $(item).css('display');
    if (display === 'block') {
      // 실패 시 입력을 했어도 에러 박스 추가
      $(item).siblings('.j-check').addClass('empty');
      check.push(false);
    }
    if (display === 'none') {
      check.push(true);
    }
  });

  return check.every((item) => item === true);
}

// 이메일 value 조합
function outputEmail() {
  let type = $('#output-email').attr('email-type');
  let inputval = $('#email-input').val();
  let output = inputval + '@';

  switch (type) {
    case '0':
      output += $('#email option:selected').val();
      break;
    case '1':
      output += $('#manual-input').val();
      break;
  }

  $('#output-email').val(output);
}
