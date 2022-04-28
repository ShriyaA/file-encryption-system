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
    startButton.addEventListener('click', uploadFile);
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
      document.getElementById('response').classList.remove("hidden");
      document.getElementById('file-progress').style.display = 'none';
      document.getElementById('start').classList.add("hidden");
      
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
    
    function uploadFile() {
      
      var xhr = new XMLHttpRequest(),
      pBar = document.getElementById('file-progress')
      
      if (xhr.upload) {
        // Check if file is less than x MB
        // Progress bar
        pBar.style.display = 'inline';
        xhr.upload.addEventListener('loadstart', setProgressMaxValue, false);
        xhr.upload.addEventListener('progress', updateFileProgress, false);
        
        // File received / failed
        xhr.onreadystatechange = function(e) {
          if (xhr.readyState == 4) {
            var headers = xhr.getAllResponseHeaders();
            
            // Convert the header string into an array
            // of individual headers
            var arr = headers.trim().split(/[\r\n]+/);
            
            // Create a map of header names to values
            var headerMap = {};
            arr.forEach(function (line) {
              var parts = line.split(': ');
              var header = parts.shift();
              var value = parts.join(': ');
              headerMap[header] = value;
            });
            if (xhr.status == 200){
              downloadFile(xhr.response, headerMap)
            }
            else if (xhr.status == 400){
              alert("Encryption Strength or Password incorrect");
            }
          }
        };
        pwd = document.getElementById("pwd").value;
        var radios = document.getElementsByName("selectMode");
        var selected = Array.from(radios).find(radio => radio.checked);
        if (selected == null){
          alert("Mode must be selected.")
        }
        if (pwd == null){
          alert("Password required.")
        }
        var mode = selected.value;
        var fileTypeSelected = document.getElementById('fileType').value
        
        if (mode == "Encrypt"){
          reqUrl = encryptionUrl;
          requestName = "encryptionRequest"; 
        }
        else {
          reqUrl = decryptionUrl;
          requestName = "decryptionRequest";
        }
        var req = {
          fileType: fileTypeSelected,
          key: pwd,
          encryptionLevel: 'STRONG'
        }
        var formData = new FormData();
        for (var i = 0, f; f = files[i]; i++) {
          formData.append("file", files[i])
        }
        formData.append(requestName, new Blob([JSON.stringify(req)], {type: "application/json"}))
        // Start upload
        xhr.open('POST', reqUrl, true);
        xhr.responseType = "blob";
        xhr.send(formData);

        //$("#myModal").modal()
      }
    }
    
    function downloadFile(response, headerMap) {
      //$("#myModal").modal({ show: false})
      var blobUrl = URL.createObjectURL(response);
      var link = document.createElement("a"); // Or maybe get it from the current document
      link.href = blobUrl;
      link.download = "encrypted_file";
      link.innerHTML = "Click here to download the file";
      link.style.display = 'none';
      document.body.appendChild(link); // Or append it whereever you want
      document.querySelector('a').click()
      resetPage();
    }

    function resetPage(){
      document.getElementById('start').classList.remove("hidden");
      output('');
      pBar = document.getElementById('file-progress');
      pBar.value = 0;
      pBar.style.display = 'none';
      document.getElementById('pwd').value = "";
    } 

    // Check for the various File API support.
    if (window.File && window.FileList && window.FileReader) {
      Init();
    } else {
      document.getElementById('file-drag').style.display = 'none';
    }
  };
  
  function passwordToggle(){
    const togglePassword = document.getElementById('togglePassword');
    const password = document.getElementById('pwd');
    togglePassword.addEventListener('click', function (e) {
      // toggle the type attribute
      const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
      password.setAttribute('type', type);
      // toggle the eye / eye slash icon
      this.classList.toggle('bi-eye');
    });
  }

  ekUpload();
  fileType();
  passwordToggle();
