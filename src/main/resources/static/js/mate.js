function success({ coords, timestamp }) {
  const latitude = coords.latitude; // 위도
  const longitude = coords.longitude; // 경도

  $.ajax({
    type: 'post',
    url: 'rege',
    data: { coords: longitude + ',' + latitude },
  });

  alert(`위도: ${latitude}, 경도: ${longitude}, 위치 반환 시간: ${timestamp}`);
}

function getUserLocation() {
  if (!navigator.geolocation) {
    throw '위치 정보가 지원되지 않습니다.';
  }
  navigator.geolocation.getCurrentPosition(success);
}

$(document).ready(function () {
  $('.form-select').on('change', function () {
    $(this).removeClass('empty');
  });
});
