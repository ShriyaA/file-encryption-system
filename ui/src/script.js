const encryptionUrl = "http://localhost:8080/encrypt";
const decryptionUrl = "http://localhost:8080/decrypt";

function fileType(){
  var fileType = document.getElementById("fileType");
  fileType.addEventListener('change', changeAcceptType, false);

  function changeAcceptType(e) {
    fileTypeStr = e.target.value;
    acceptType = "";
    switch(fileTypeStr){
      case "TEXT":
        acceptType = ".doc,.pdf,.txt";
        break;
      case "IMAGE":
        acceptType = "image/*";
        break;
      case "VIDEO":
        acceptType = "video/*";
        break;
      case "AUDIO":
        acceptType = "audio/*";
        break;
    }
    fileUploader = document.getElementById('file-upload');
    fileUploader.setAttribute("accept", acceptType);
  }
}

// File Upload
// 
function ekUpload(){
  var files;
  function Init() {

    console.log("Upload Initialised");

    var fileSelect    = document.getElementById('file-upload'),
        fileDrag      = document.getElementById('file-drag'),
        submitButton  = document.getElementById('submit-button');

    fileSelect.addEventListener('change', fileSelectHandler, false);

    // Is XHR2 available?
    var xhr = new XMLHttpRequest();
    if (xhr.upload) {
      // File Drop
      fileDrag.addEventListener('dragover', fileDragHover, false);
      fileDrag.addEventListener('dragleave', fileDragHover, false);
      fileDrag.addEventListener('drop', fileSelectHandler, false);
    }

    var startButton = document.getElementById('startButton')
    startButton.addEventListener('click', uploadFiles);
  }

  function fileDragHover(e) {
    var fileDrag = document.getElementById('file-drag');

    e.stopPropagation();
    e.preventDefault();

    fileDrag.className = (e.type === 'dragover' ? 'hover' : 'modal-body file-upload');
  }

  function fileSelectHandler(e) {
    // Fetch FileList object
    files = e.target.files || e.dataTransfer.files;

    // Cancel event and hover styling
    fileDragHover(e);

    // Process all File objects
    for (var i = 0, f; f = files[i]; i++) {
      parseFile(f);
    }
  }

  // Output
  function output(msg) {
    // Response
    var m = document.getElementById('messages');
    m.innerHTML = msg;
  }

  function parseFile(file) {

    console.log(file.name);
    output(
      '<strong>' + encodeURI(file.name) + '</strong>'
    );
    
    // var fileType = file.type;
    // console.log(fileType);
    var fileName = file.name;

    document.getElementById('start').classList.add("hidden");
    document.getElementById('response').classList.remove("hidden");
  }

  function setProgressMaxValue(e) {
    var pBar = document.getElementById('file-progress');

    if (e.lengthComputable) {
      pBar.max = e.total;
    }
  }

  function updateFileProgress(e) {
    var pBar = document.getElementById('file-progress');

    if (e.lengthComputable) {
      pBar.value = e.loaded;
    }
  }

  function uploadFiles() {
    for (var i = 0, f; f = files[i]; i++) {
      uploadFile(f);
    }
  }

  function uploadFile(file) {

    var xhr = new XMLHttpRequest(),
      pBar = document.getElementById('file-progress'),
      fileSizeLimit = 1024; // In MB
    if (xhr.upload) {
      // Check if file is less than x MB
      if (file.size <= fileSizeLimit * 1024 * 1024) {
        // Progress bar
        pBar.style.display = 'inline';
        xhr.upload.addEventListener('loadstart', setProgressMaxValue, false);
        xhr.upload.addEventListener('progress', updateFileProgress, false);

        // File received / failed
        xhr.onreadystatechange = function(e) {
          if (xhr.readyState == 4) {
            downloadFile(xhr.response)
          }
        };
        pwd = document.getElementById("pwd").value;
        var radios = document.getElementsByName("selectMode");
        var selected = Array.from(radios).find(radio => radio.checked);
        var mode = selected.value;
        var fileTypeSelected = document.getElementById('fileType').value

        if (mode == "Encrypt"){
          reqUrl = encryptionUrl; 
        }
        else {
          reqUrl = decryptionUrl;
        }
        var req = {
          fileType: fileTypeSelected,
          key: pwd,
          encryptionLevel: 'STRONG'
        }
        var formData = new FormData();
        formData.append("file", file)
        formData.append("encryptionRequest", new Blob([JSON.stringify(req)], {type: "application/json"}))
        // Start upload
        xhr.open('POST', reqUrl, true);
        //xhr.setRequestHeader()
        xhr.responseType = 'text';
        xhr.send(formData);
      } else {
        output('Please upload a smaller file (< ' + fileSizeLimit + ' MB).');
      }
    }
  }

  function downloadFile(response) {
    var contentDispo = response.getResponseHeader('Content-Disposition');
    var fileName = contentDispo.match(/filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/)[1];
    saveBlob(response, fileName);
  }

  function saveBlob(blob, fileName) {
    var a = document.createElement('a');
    a.href = window.URL.createObjectURL(blob);
    a.download = fileName;
    a.dispatchEvent(new MouseEvent('click'));
  }

  // Check for the various File API support.
  if (window.File && window.FileList && window.FileReader) {
    Init();
  } else {
    document.getElementById('file-drag').style.display = 'none';
  }
}
ekUpload();
fileType();