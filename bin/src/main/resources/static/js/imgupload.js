// 드래그앤드롭
var sec9 = document.querySelector('#ex9');
var btnUpload = sec9.querySelector('.btn-upload');
var inputFile = sec9.querySelector('input[type="file"]');
var uploadBox = sec9.querySelector('.upload-box');

uploadBox.addEventListener('dragenter', function (e) {
  console.log('dragenter');
});

uploadBox.addEventListener('dragover', function (e) {
  e.preventDefault();

  var vaild = e.dataTransfer.types.indexOf('Files') >= 0;

  if (!vaild) {
    this.style.backgroundColor = 'red';
  } else {
    this.style.backgroundColor = 'green';
  }
});

uploadBox.addEventListener('dragleave', function (e) {
  console.log('dragleave');

  this.style.backgroundColor = 'white';
});

uploadBox.addEventListener('drop', function (e) {
  e.preventDefault();

  console.log('drop');
  this.style.backgroundColor = 'white';

  console.dir(e.dataTransfer);

  var data = e.dataTransfer.files[0];
  console.dir(data);
});
