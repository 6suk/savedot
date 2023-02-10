// file
const columns = document.querySelectorAll('.preview');
let sortable;
let filelist = [];

// SUBMIT!
function matesubmit() {
  let formData = new FormData($('#mateform')[0]);

  //content <p>태그 추가
  let txt = formData.get('content');
  var txttostore = '<p>' + txt.replace(/\n/g, '</p>\n<p>') + '</p>';
  formData.set('content', txttostore);

  formData.append('category', $('input[name=category]:checked').val());
  formData.append('area', $('#area2 option:selected').val());
  filelist.forEach((x) => {
    formData.append('reqimgs', x);
  });

  fetch('/mate', {
    method: 'POST',
    cache: 'no-cache',
    body: formData,
    redirect: 'follow',
  })
    .then((response) => {
      if (response.redirected) {
        window.location.href = response.url;
      }
    })
    .catch(function (err) {
      console.info(err + ' url: ' + url);
    });
}

function test() {
  console.log(filelist);
}

// required
let required;

// require 검사 + SUBMIT
$('#sendbtn').click(function () {
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
  } else {
    // true. submit
    matesubmit();
  }
});

// require 값 채워질 때 (이미지는 handleUpdate() 함수에)
// $(function () {
//   Array.from(required).forEach((x) => {
//     let input = $(x);
//     input.on({
//       input: function () {
//         $(this).removeClass('empty');
//       },
//       change: function () {
//         $(this).parent().removeClass('empty');
//       },
//     });
//   });
// });

// IMG UPLOAD
columns.forEach((column) => {
  sortable = new Sortable(column, {
    animation: 150,
    filter: '.remove',
    onFilter: function (evt) {
      var item = evt.item,
        ctrl = evt.target;

      if (Sortable.utils.is(ctrl, '.remove')) {
        item.parentNode.removeChild(item);
        filelist.splice(evt.oldIndex, 1); // 파일 리스트에서 삭제
        if (filelist.length === 0) {
          document.querySelector('.preview').setAttribute('hidden', true);
        }
      }
    },

    // 리스트 순서 변경
    onEnd: function (evt) {
      let oldindex = evt.oldIndex;
      let newindex = evt.newIndex;
      let tmp = filelist.splice(oldindex, 1)[0];
      filelist.splice(newindex, 0, tmp);
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
  if ($(event.target).hasClass('inner')) {
    $(event.target).css('opacity', '70%');
  }
});

document.addEventListener('dragover', (event) => {
  event.preventDefault();
});

document.addEventListener('dragleave', (event) => {
  event.preventDefault();
  if ($(event.target).hasClass('inner')) {
    $(event.target).css('opacity', '100%');
  }
});

document.addEventListener('drop', (event) => {
  event.preventDefault();
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
      // requared
      let imgbox = document.querySelector('.imgbox');
      imgbox.classList.remove('empty');

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
      filelist.push(file);
    });
    reader.readAsDataURL(file);
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
