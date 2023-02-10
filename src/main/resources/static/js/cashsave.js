const columns = document.querySelectorAll('.preview');
let sendfile;

var input = document.getElementById('input');
var initLabel = document.getElementById('label');
let inner = document.getElementById('inner');

input.addEventListener('change', (event) => {
  const files = changeEvent(event);
  handleUpdate(files);
});

document.addEventListener('mouseover', (event) => {
  event.preventDefault();
  if (event.target.className === 'inner') {
    const label = document.getElementById('label');
    label?.classList.add('label--hover');
  }
});

document.addEventListener('mouseout', (event) => {
  event.preventDefault();

  if (event.target.className === 'inner') {
    const label = document.getElementById('label');
    label?.classList.remove('label--hover');
  } else if (event.target.className === 'allContainer') {
    event.target.querySelector('.container-img').style.opacity = '1';
    event.target.querySelector('.remove').style.opacity = '0';
  } else if (event.target.className === 'remove') {
    event.target.style.opacity = '0';
  }
});

document.addEventListener('dragenter', (event) => {
  event.preventDefault();
  console.log('dragenter');
  if ($(event.target).hasClass('inner')) {
    $(event.target).css('opacity', '70%');
  }
});

document.addEventListener('dragover', (event) => {
  event.preventDefault();
});

document.addEventListener('dragleave', (event) => {
  event.preventDefault();
  console.log('dragleave');
  if ($(event.target).hasClass('inner')) {
    $(event.target).css('opacity', '100%');
  }
});

document.addEventListener('drop', (event) => {
  event.preventDefault();
  console.log('drop');

  if ($(event.target).hasClass('embed-img')) {
    console.log('실행');
    const embedimg = document.querySelector('.embed-img');
    if (embedimg !== null) {
      embedimg.remove();
    }
    const files = event.dataTransfer?.files;
    $(event.target).css('opacity', '100%');
    $('.inner').removeClass('empty');
    handleUpdate([...files]);
  } else if ($(event.target).hasClass('inner')) {
    const files = event.dataTransfer?.files;
    $(event.target).css('opacity', '100%');
    $('.inner').removeClass('empty');
    handleUpdate([...files]);
  }
});

function changeEvent(event) {
  const { target } = event;
  return [...target.files];
}

function handleUpdate(fileList) {
  const preview = document.getElementById('preview');
  const label = document.getElementById('label');
  const embedimg = document.querySelector('.embed-img');
  preview.hidden = false;
  label.hidden = true;

  if (embedimg !== null) {
    embedimg.remove();
  }

  const reader = new FileReader();
  reader.addEventListener('load', (event) => {
    const img = el('img', {
      src: event.target?.result,
      draggables: 'true',
    });

    const imgContainer = el('div', { className: 'embed-img' }, img);
    preview.append(imgContainer);
  });
  reader.readAsDataURL(fileList[0]);

  sendimg(fileList[0]);
}




function el(nodeName, attributes, ...children) {
  const node = nodeName === 'fragment' ? document.createDocumentFragment() : document.createElement(nodeName);

  Object.entries(attributes).forEach(([key, value]) => {
    if (key === 'events') {
      Object.entries(value).forEach(([type, listener]) => {
        node.addEventListener(type, listener);
      });
    } else if (key in node) {
      try {
        node[key] = value;
      } catch (err) {
        node.setAttribute(key, value);
      }
    } else {
      node.setAttribute(key, value);
    }
  });

  children.forEach((childNode) => {
    if (typeof childNode === 'string') {
      node.appendChild(document.createTextNode(childNode));
    } else {
      node.appendChild(childNode);
    }
  });
  return node;
}

// require 검사 + SUBMIT
$('#sendbtn').click(function () {
  let inner = $('#inner');

  //input value
  let category = $('input[name=category]:checked');
  let category_control = $('label#category');
  let title = $('input[name=title]');
  let area = $('#area2 option:selected');
  let area_control = $('.input-btn');
  let price1 = $('input[name=price1]');
  let price2 = $('input[name=price2]');

  //control
  let check = [category, title, area, price1, price2];
  let control = [category_control, title, area_control, price1, price2];
  let check_ = 0;

  for (let i = 0; i < check.length; i++) {
    if (check[i].val() === '' || check[i].val() === null || check[i].val() === undefined) {
      control[i].addClass('empty');
    } else {
      check_++;
    }
  }

  if (sendfile === null) {
    inner.addClass('empty');
  } else {
    check_++;
  }

  if (check_ === check.length + 1) {
    cashsubmit();
  }
});

// require 값 채워질 때
$(function () {
  let category_control = $('label#category');
  let img_control = $('#inner');

  $('input[name=title], input[name=area], input[name=price1], input[name=price2]').on('input', function () {
    $(this).removeClass('empty');
  });

  $('input[name=category]').change(function () {
    if ($(this).length > 0) {
      category_control.removeClass('empty');
    }
  });
});

var ele = document.getElementById('date');
ele.onchange = function () {
  if (el.value === '') {
    el.classList.add('empty-text');
  } else {
    el.classList.remove('empty-text');
  }
};

$(document).ready(function () {
  $("input[name = 'category']").change(function () {
    let category = $('input[name=category]:checked').val();
    let label = $('#label');
    let preview = $('#preview');
    let inputText = $('.input-text');

    switch (category) {
      case '0': //영수증
        label.removeClass('none');
        $('input.input-text').val('');
        inputText.attr('disabled', true);
        console.log(0);
        break;

      case '1': //지출 직접 기입
        $('.embed-img').remove();
        preview.attr('hidden', true);
        label.attr('hidden', false);
        label.addClass('none');
        inputText.attr('disabled', false);
        console.log(1);
        break;

      case '2': //수입 직접 기입
        preview.attr('hidden', true);
        label.attr('hidden', false);
        label.addClass('none');
        inputText.attr('disabled', false);
        console.log(2);
        break;
    }
  });
});

function cashsubmit() {
  let category = $('input[name=category]:checked').val();
  switch (category) {
    case 0: // 영수증
      break;
    case 1: // 지출 직접 기입
      break;
    case 2: // 수입 직접 기입
      break;
  }
}

function test() {
  // console.log('sendfile');
  // console.log(sendfile);
}


function sendimg(file) {
  let formdata = new FormData()
  formdata.append('data', file)
  
  $.ajax({
    type: 'post',
    url: '/ocr',
    data: formdata,
    contentType: false,
    processData: false,
    success: function (res) {

      let data = JSON.parse(res);

      let resultMemo = "";

      for(let i=0; i<data.length; i++){
        if(data[i].time != null){
          $("#date").val(data[i].time);
        }
        if(data[i].price != null){
          $("#amount").val(data[i].price);
        }
        if(data[i].inferText != null){
          resultMemo += data[i].inferText + " ";
        }
      }

      $("#memo").val(resultMemo);
      console.log(data);
    },
  });
}