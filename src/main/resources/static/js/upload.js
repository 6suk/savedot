const columns = document.querySelectorAll('.preview');
let sortable;
let filelist = [];

columns.forEach((column) => {
  sortable = new Sortable(column, {
    group: 'shared',
    animation: 150,
    ghostClass: 'blue-background-class',
    filter: '.remove',
    onFilter: function (evt) {
      var item = evt.item,
        ctrl = evt.target;

      if (Sortable.utils.is(ctrl, '.remove')) {
        item.parentNode.removeChild(item);
        filelist.splice(evt.oldIndex, 1); // 파일 리스트에서 삭제
      }
    },

    // 리스트 순서 변경
    onEnd: function (evt) {
      let oldindex = evt.oldIndex;
      let newindex = evt.newIndex;
      let tmp = filelist.splice(oldindex, 1)[0];
      filelist.splice(newindex, 0, tmp);
      console.log(filelist);
    },
  });
});

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
  // sort 썸네일 이미지 마우스 오버 시 opacity
  else if (event.target.className === 'allContainer') {
    event.target.querySelector('.container-img').style.opacity = '0.7';
    event.target.querySelector('.remove').style.opacity = '1';
  }
  // sort 삭제버튼 마우스 오버 시 opacity
  else if (event.target.className === 'remove') {
    event.target.parentNode.querySelector('.container-img').style.opacity = '0.7';
    event.target.style.opacity = '0.5';
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

  if ($(event.target).hasClass('inner')) {
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
  preview.removeAttribute('hidden');

  fileList.forEach((file) => {
    const reader = new FileReader();
    reader.addEventListener('load', (event) => {
      const img = el('img', {
        className: 'embed-img',
        src: event.target?.result,
        draggables: 'true',
      });

      const i = el('i', {
        className: 'fa-solid fa-trash-can',
      });

      const remove = el('div', { className: 'remove', id: 'remove' }, i);
      const imgContainer = el('div', { className: 'container-img', id: 'inputimg' }, img);
      const allContainer = el('div', { className: 'allContainer' }, imgContainer, remove);
      preview.append(allContainer);
    });
    reader.readAsDataURL(file);
    filelist.push(file);
  });
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
  let title = $('input[name=title]');
  let category = $('input[name=category]:checked');
  let category_control = $('label#category');
  let area = $('input[name=area]');
  let price1 = $('input[name=price1]');
  let price2 = $('input[name=price2]');

  //control
  let check = [category, title, area, price1, price2];
  let check_ = 0;

  for (let i = 0; i < check.length; i++) {
    let control = i === 0 ? category_control : check[i];
    if (check[i].val() === '' || check[i].val() === null || check[i].val() === undefined) {
      control.addClass('empty');
    } else {
      check_++;
    }
  }

  if (filelist.length < 1) {
    inner.addClass('empty');
  } else {
    check_++;
  }

  if (check_ === check.length + 1) {
    matesubmit();
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

function matesubmit() {
  console.log('실행');
  let formData = new FormData($('#mateform')[0]);
  formData.append('category', $('input[name=category]:checked').val());
  filelist.forEach((x) => formData.append('imgs', x));
  formData.append('img', filelist[0]);
  console.log(filelist);

  fetch('/mate', {
    method: 'POST',
    cache: 'no-cache',
    body: formData,
  });
}
