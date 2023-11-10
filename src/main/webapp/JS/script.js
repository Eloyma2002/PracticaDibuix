const canvas = document.querySelector('canvas');
const context = canvas.getContext('2d');
const select = document.querySelector('#figure');
const colorInput = document.querySelector("#color");
const checkbox = document.querySelector("#fill");
const rangeInput = document.querySelector("#size");
const nameImage = document.querySelector('#name');
const buttonClean = document.querySelector('#clean');
const inputJSON = document.querySelector('#JSON');
const list = document.querySelector('#list');
const buttonDraw = document.querySelector('#draw');


canvas.width = 400;
canvas.height = 400;

let draw = false;
let figureSelected = select.value;
let color = colorInput.value;
let checked = checkbox.checked;
let size = rangeInput.value;
let x, y;
let cont = 0;
let numFigures = 0;
let content = [];


buttonDraw.addEventListener("click", function () {
    draw = !draw;
});



nameImage.value = randomName();
function randomName() {
    return "image" + Math.floor(Math.random() * 9999 + 1);
}



function drawFigureSelected(x, y, figureSelected) {
    context.strokeStyle = color;
    context.fillStyle = checked ? color : "transparent";

    switch (figureSelected) {
        case 'circle':
            context.beginPath();
            context.arc(x, y, size, 0, 2 * Math.PI);
            context.fill();
            context.stroke();
            addFigureToList(figureSelected, x, y, size);
            write("Circle");
            break;

        case 'square':
            context.beginPath();
            context.fillRect(x - size / 2, y - size / 2, size * 2, size * 2);
            context.strokeRect(x - size / 2, y - size / 2, size * 2, size * 2);
            addFigureToList(figureSelected, x, y, size);
            write("Square");
            break;

        case 'triangle':
            context.beginPath();
            context.moveTo(x, y - Number(size));
            context.lineTo(x - Number(size), y + Number(size));
            context.lineTo(x + Number(size), y + Number(size));
            context.closePath();
            context.fill();
            addFigureToList(figureSelected, x, y, size);
            context.stroke();
            write("Triangle");
            break;

        case 'star':
            context.beginPath();
            for (let i = 0; i < 14; i++) {
                const angle = (Math.PI * 2 * i) / 14;
                const radius = i % 2 === 0 ? size : size / 2;
                context.lineTo(x + radius * Math.cos(angle), y + radius * Math.sin(angle));
            }
            context.closePath();
            context.fill();
            context.stroke();
            addFigureToList(figureSelected, x, y, size);
            write("Star");
            break;
    }
}

function addFigureToList(figureSelected, x, y, size) {
    cont++;
    content.push(JSON.stringify({
        "id":cont,
        "type":figureSelected,
        "x":x,
        "y":y,
        "color":color,
        "fill":checkbox.checked,
        "size":size,
    }));    
    inputJSON.value = JSON.stringify("[" + content + "]");
    console.log(content);
}

function write(text){
    list.innerHTML+="<li> <button>Remove</button> " + text + "</li>";
    inputJSON.value = JSON.stringify("[" + content + "]");
}

select.addEventListener("change", function() {
    figureSelected = select.value;
    inputJSON.value = JSON.stringify("[" + content + "]");
});

colorInput.addEventListener("input", function() {
    color = colorInput.value;
    inputJSON.value = JSON.stringify("[" + content + "]");
});

checkbox.addEventListener("change", function() {
    checked = checkbox.checked;
    inputJSON.value = JSON.stringify("[" + content + "]");
});

rangeInput.addEventListener("input", function() {
    size = rangeInput.value;
    inputJSON.value = JSON.stringify(strings);
});

canvas.addEventListener('click', function(event) {
    x = event.clientX - canvas.offsetLeft;
    y = event.clientY - canvas.offsetTop;
    drawFigureSelected(x, y, figureSelected);
    inputJSON.value = JSON.stringify("[" + content + "]");
    console.log("x, y: " + x + ", " + y);
});

buttonClean.addEventListener('click', function() {
    cont = 0;
    content = [];
    list.innerHTML="";
    context.clearRect(0, 0, canvas.width, canvas.height);
});


