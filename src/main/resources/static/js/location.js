let area1 = $('#area1');
let area2 = $('#area2');

//[위치 찾기]
function success({ coords, timestamp }) {
  const latitude = coords.latitude; // 위도
  const longitude = coords.longitude; // 경도
  let areainput = $('input[name=area]');

  $.ajax({
    type: 'post',
    url: 'write/location',
    data: { lat: latitude, lon: longitude, timestamp: timestamp },
    success: function (data) {
      area1.removeClass('empty-select');
      area2.removeClass('empty-select');
      area1.append(`<option value='${data['area1']}' id="area1_location_value" selected>${data['area1']}</option>`);
      area2.append(
        `<option value="${data['area1']} ${data['area2']}" id="area2_location_value" selected>${data['area2']}</option>`
      );
    },
  });
}

$('#getLocation').click(function () {
  $('.input-btn').removeClass('empty');
  area1.attr('disabled', true);
  area2.attr('disabled', true);
  $('#location_value').remove;

  if (!navigator.geolocation) {
    throw '위치 정보가 지원되지 않습니다.';
  }
  navigator.geolocation.getCurrentPosition(success);
});

// [직접기입] : 전체 시 가져오기
$('#self_write').click(function () {
  area1.attr('disabled', false);
  area2.attr('disabled', false);
  $('.input-btn').removeClass('empty');
  $('#area1_location_value').remove();
  $('#area2_location_value').remove();
  area1.children('option').remove();
  area1.append('<option value="" selected disabled id="area1_title">시</option>');
  area2.append('<option value="" disabled selected id="area2_title">군/구</option>');

  var request = new XMLHttpRequest();
  request.open('GET', 'https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=*00000000');
  request.send();
  request.onload = function () {
    let json = JSON.parse(request.response);
    let arr = json.regcodes;

    for (let i = 0; i < arr.length; i++) {
      let areas = arr[i];
      area1.append(`<option value=${areas.code.slice(0, 2)}>${areas.name}</option>`);
    }
  };
});

// [직접기입] : 시 선택 시 -> 구/군 출력
area1.change(function () {
  area1.removeClass('empty-select');
  $('#area2 option:eq(1)').remove();
  let code = $('#area1 option:selected').val();
  area2.children('option').remove();
  area2.append('<option value="" disabled selected id="area2_title">군/구</option>');

  var request = new XMLHttpRequest();
  request.open('GET', `https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=${code}*00000`);
  request.send();
  request.onload = function () {
    let json = JSON.parse(request.response);
    let arr = json.regcodes;

    for (let i = 1; i < arr.length; i++) {
      let areas = arr[i];
      let end_name = areas.name.split(' ');
      let result = end_name[1];

      if (end_name.length > 2) {
        result = end_name[1] + ' ' + end_name[2];
      }

      area2.append(`<option value="${areas.name}">${result}</option>`);
    }
  };
});

area2.change(function () {
  area2.removeClass('empty-select');
});
