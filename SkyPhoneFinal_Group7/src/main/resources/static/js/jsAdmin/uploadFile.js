function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#blah1')
                .attr('src', e.target.result)
        };
        reader.readAsDataURL(input.files[0]);
    }

}

function readURL1(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#blah2')
                .attr('src', e.target.result)
        };
        reader.readAsDataURL(input.files[0]);
    }

}

function readURL2(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#blah3')
                .attr('src', e.target.result)
        };
        reader.readAsDataURL(input.files[0]);
    }

}

function readURL3(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#blah4')
                .attr('src', e.target.result)
        };
        reader.readAsDataURL(input.files[0]);
    }

}

function readURL4(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#blah5')
                .attr('src', e.target.result)
        };
        reader.readAsDataURL(input.files[0]);
    }

}

let inputFile1 = document.getElementById('file-input1');
let fileNameFiled1 = document.getElementById('file-name1');
inputFile1.addEventListener('change', function(event) {
    let uploadFileName1 = event.target.files[0].name;
    fileNameFiled1.textContent = "File chọn: " + uploadFileName1;
})

let inputFile2 = document.getElementById('file-input2');
let fileNameFiled2 = document.getElementById('file-name2');
inputFile2.addEventListener('change', function(event) {
    let uploadFileName2 = event.target.files[0].name;
    fileNameFiled2.textContent = uploadFileName2;
})

let inputFile3 = document.getElementById('file-input3');
let fileNameFiled3 = document.getElementById('file-name3');
inputFile3.addEventListener('change', function(event) {
    let uploadFileName3 = event.target.files[0].name;
    fileNameFiled3.textContent = uploadFileName3;
})

let inputFile4 = document.getElementById('file-input4');
let fileNameFiled4 = document.getElementById('file-name4');
inputFile4.addEventListener('change', function(event) {
    let uploadFileName4 = event.target.files[0].name;
    fileNameFiled4.textContent = uploadFileName4;
})

let inputFile5 = document.getElementById('file-input5');
let fileNameFiled5 = document.getElementById('file-name5');
inputFile5.addEventListener('change', function(event) {
    let uploadFileName5 = event.target.files[0].name;
    fileNameFiled5.textContent = uploadFileName5;
})