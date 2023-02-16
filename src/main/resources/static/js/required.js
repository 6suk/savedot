function requiredCheck(filelist) {
  required = $('.required');

  Array.from(required).forEach((x) => {});
  let no_val = [];

  // 1. radio 타입일 때
  let radio_selected = new Array(0); // 선택된 Value Array
  let radio = Array.from($('.required:radio'));
  let tmp = '';
  radio.forEach((x) => {
    let name = $(x).attr('name');
    if (tmp !== name) {
      let radio_val = $('input[name=' + name + ']:checked');
      tmp = name;
      if (radio_val.length === 0) {
        // value가 없을 경우
        check = false;
        no_val.push($(x));
      } else {
        // value가 있을 경우
        radio_selected.push(radio_val); // 선택값 넣기
        check = true;
      }
    }
  });

  // 2. 나머지 타입일 때
  Array.from(required).forEach((x) => {
    let input = $(x);
    let type_ck = input.attr('type');

    if (type_ck !== 'radio' && type_ck !== 'checkbox' && type_ck !== 'file') {
      if (input.val() == null || input.val() == undefined || input.val() == '') {
        // value가 없을 경우
        no_val.push(input);
      }
    } else if (type_ck === 'file') {
      if (filelist.length === 0) {
        no_val.push(input);
      }
    }
  });

  // 결과
  if (no_val.length > 0) {
    // false. empty 추가
    no_val.forEach((x) => {
      let input = $(x);
      if (
        input.attr('type') === 'radio' ||
        input.attr('type') === 'checkbox' ||
        input.attr('type') === undefined ||
        input.attr('type') === 'file'
      ) {
        input.parent().addClass('empty');
      } else {
        $(x).addClass('empty');
      }
    });

    // require 값 채워질 때 (이미지는 handleUpdate() 함수에)
    $(function () {
      Array.from(required).forEach((x) => {
        let input = $(x);
        input.on({
          input: function () {
            $(this).removeClass('empty');
          },
          change: function () {
            $(this).parent().removeClass('empty');
          },
        });
      });
    });
    return false;
  } else {
    // true. submit
    return true;
  }
}
