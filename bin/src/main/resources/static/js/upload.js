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
        item.parentNode.removeChild(item); // remove sortable item
        filelist.splice(evt.oldIndex, 1); // 파일 리스트에서 삭제
      }
    },

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
  } else if (event.target.className === 'allContainer') {
    event.target.querySelector('.container-img').style.opacity = '0.7';
    event.target.querySelector('.remove').style.opacity = '1';
  } else if (event.target.className === 'remove') {
    event.target.parentNode.querySelector('.container-img').style.opacity =
      '0.7';
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
  if (event.target.className === 'inner') {
    event.target.style.opacity = '70%';
  }
});

document.addEventListener('dragover', (event) => {
  event.preventDefault();
});

document.addEventListener('dragleave', (event) => {
  event.preventDefault();
  console.log('dragleave');
  if (event.target.className === 'inner') {
    event.target.style.opacity = '100%';
  }
});

document.addEventListener('drop', (event) => {
  event.preventDefault();
  console.log('drop');
  if (event.target.className === 'inner') {
    const files = event.dataTransfer?.files;
    event.target.style.opacity = '100%';
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

      const imgContainer = el(
        'div',
        { className: 'container-img', id: 'inputimg' },
        img
      );

      const allContainer = el(
        'div',
        { className: 'allContainer' },
        imgContainer,
        remove
      );
      preview.append(allContainer);
    });
    reader.readAsDataURL(file);
    filelist.push(file);
  });
}

function el(nodeName, attributes, ...children) {
  const node =
    nodeName === 'fragment'
      ? document.createDocumentFragment()
      : document.createElement(nodeName);

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

function contentSave() {
  console.log(filelist);
}
