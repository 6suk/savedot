let $mate_img = $('[MATEIMG]');

if ($mate_img.length > 0) {
  $mate_img.each(function (index, item) {
    let url = 'http://localhost:8080/savedot/display/' + $(item).attr('MATEIMG');
    let fileName = index;
    fetch(url).then(async (response) => {
      const contentType = response.headers.get('content-type');
      const blob = await response.blob();
      const file = new File([blob], fileName, { contentType });
      // access file here
      handleUpdateVer(file);
    });
  });
}

function handleUpdateVer(file) {
  const preview = document.getElementById('preview');
  preview.removeAttribute('hidden');

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
}

// required 검사 + SUBMIT
$('#updatebtn').click(function () {
  let check = requiredCheck(filelist);
  console.log(filelist);
  if (check) {
    mate_update_submit();
  }
});

// SUBMIT!
function mate_update_submit() {
  let price = $('input:text[price]');
  Array.from(price).forEach((x) => {
    $(x).val(removeCommas($(x).val()));
  });

  let formData = new FormData($('#mate-update-form')[0]);

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

  let path = window.location.pathname;

  fetch(path, {
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
