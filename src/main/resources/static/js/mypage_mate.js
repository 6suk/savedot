$('.btn.fa-chevron-right').click(function () {
  let mate_card = $(this).parents('.mate_card');
  let control_box = mate_card.find('.mypage-mate-card-box');

  let scrollX = $(control_box).scrollLeft();
  $(control_box).scrollLeft(scrollX + 600);
});

$('.btn.fa-chevron-left').click(function () {
  let mate_card = $(this).parents('.mate_card');
  let control_box = mate_card.find('.mypage-mate-card-box');

  let scrollX = $(control_box).scrollLeft();
  $(control_box).scrollLeft(scrollX - 600);
});

$(function () {
  /* 셀렉트 박스 - selected */
  let $select_tag = $('.is_apply_select_tag');

  for (let i = 0; i < $select_tag.length; i++) {
    let tmp = $select_tag[i];
    if ($(tmp).hasClass('ver0')) {
      $(tmp).children('.tag_0').attr({ selected: true, disabled: true });
    }
    if ($(tmp).hasClass('ver1')) {
      $(tmp).children('.tag_1').attr({ selected: true, disabled: true });
    }
    if ($(tmp).hasClass('ver2')) {
      $(tmp).children('.tag_2').attr({ selected: true, disabled: true });
    }
  }

  /* NEW! Notify */
});

/* 상태 변경 버튼을 눌렀을 때 */
$('.is_apply_mod').click(function (event) {
  event.stopPropagation();
  event.preventDefault();
  edit(event);
});

/* 취소 버튼을 눌렀을 때 */
$('.is-apply-edit-btn').click(function (event) {
  event.stopPropagation();
  event.preventDefault();
  edit(event);
});

/* 셀렉트 박스를 눌렀을때, 페이지 넘어가는거 차단 */
$('.is_apply_select_tag').click(function (event) {
  event.stopPropagation();
});

/* 변경 버튼을 눌렀을 때 -> 데이터 전송 */
$('.is-apply-save-btn').click(function (event) {
  event.stopPropagation();
  event.preventDefault();
  let $form = $(event.target).parents('#apply_edit_form');
  let $group = $(event.target).parents('.mate-card-top');
  let $value = $form.serializeObject();

  $.ajax({
    type: 'POST',
    url: '/mate/apply/state-edit',
    contentType: 'application/json',
    data: JSON.stringify($value),
    success: function (data) {
      location.reload();
      // $group.children('.is_apply_tag').attr('class', `is_apply_tag mate-card-tag ver${data.isApply}`);
      // $group.children('.is_apply_select_tag').attr('class', `is_apply_select_tag mate-card-tag ver${data.isApply}`);

      // let $title = $(`#ul_title_aid_${data.aid}`);
      // let $desc = $(`#ul_desc_aid_${data.aid}`);
      // // 거래 완료 일 때 //
      // if (data.isApply == 2) {
      //   // 거래 완료일 변경
      //   if ($title.children().length == 4) {
      //     $(`#ul_desc_aid_${data.aid} :last-child`).html(data.modDate.replace('T', ' '));
      //   }
      //   // 거래 완료일 추가
      //   else {
      //     $title.append('<li>거래완료일</li>');
      //     $desc.append(`<li>${data.modDate.replace('T', ' ')}</li>`);
      //   }
      // }
      // // 거래 완료가 아닌데, 거래 완료일이 있을 때 거래 완료일 삭제 //
      // else {
      //   if ($title.children().length == 4) {
      //     $(`#ul_title_aid_${data.aid} :last-child`).remove();
      //     $(`#ul_desc_aid_${data.aid} :last-child`).remove();
      //   }
      // }
      // edit(event);
    },
  });
});

function edit(event) {
  let $group = $(event.target).parents('.mate-card-top');
  $group.children('.is_apply_edit').toggle();
  $group.children('.is_apply_mod').toggle();
  $group.children('.is_apply_tag').toggle();
  $group.children('.is_apply_select_tag').toggle();
}

$.fn.serializeObject = function () {
  'use strict';
  var result = {};
  var extend = function (i, element) {
    var node = result[element.name];
    if ('undefined' !== typeof node && node !== null) {
      if ($.isArray(node)) {
        node.push(element.value);
      } else {
        result[element.name] = [node, element.value];
      }
    } else {
      result[element.name] = element.value;
    }
  };

  $.each(this.serializeArray(), extend);
  return result;
};
