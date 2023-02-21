// file
const columns = document.querySelectorAll('.preview');
let sortable;
let filelist = [];

// required 검사 + SUBMIT
$('#sendbtn').click(function () {
  let check = requiredCheck(filelist);
  console.log(check);
  if (check) {
    matesubmit();
  }
});

// SUBMIT!
function matesubmit() {
  let price = $('input:text[price]');
  Array.from(price).forEach((x) => {
    $(x).val(removeCommas($(x).val()));
  });

  let formData = new FormData($('#mateform')[0]);

  //content <p>태그 추가
  let txt = formData.get('content');
  var txttostore = '<p>' + txt.replace(/\n/g, '</p>\n<p>') + '</p>';
  formData.set('content', txttostore);
  formData.append('category', $('input[name=category]:checked').val());

  if (filelist.length > 0) {
    filelist.forEach((x) => {
      formData.append('reqimgs', x);
    });
  }

  fetch('write', {
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
