$('#applybtn, #applybtn-o').click(function () {
  let container = $('#container');
  let applybtn = $('#applybtn');
  let check = container.hasClass('container-nonaside-sm') ? 1 : 0;

  $('#section-apply').fadeToggle(280);
  $('#applybtn-o').toggle();
  $('#applybtn').toggle();
});

$('#sendbtn').click(function () {
  console.log('실행');
  $('#applyform').submit();
});
