const canvas = document.querySelector('canvas');
const context = canvas.getContext('2d');
const select = document.querySelector('#figure');
const colorInput = document.querySelector("#color");
const checkbox = document.querySelector("#fill");
const rangeInput = document.querySelector("#size");
const nameImage = document.querySelector('#name');
const clean = document.querySelector('#clean');
const figuresList = [];

canvas.width = 400;
canvas.height = 400;

let figureSelected = select.value;
let color = colorInput.value;
let checked = checkbox.checked;
let size = rangeInput.value;
let x, y;

class Figure {
    constructor(type, x, y, size, index) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.size = size;
        this.index = index;
    }
}

function randomName() {
    return "image" + Math.floor(Math.random() * 9999 + 1);
}

var cont = 0;


function drawFigureSelected(x, y, figureSelected) {
    context.strokeStyle = color;
    context.fillStyle = checked ? "transparent" : color;

    switch (figureSelected) {
        case 'circle':
            context.beginPath();
            context.arc(x, y, size, 0, 2 * Math.PI);
            context.fill();
            context.stroke();
            write("Circle - " + cont);
            cont++;
            break;

        case 'square':
            context.beginPath();
            context.fillRect(x - size / 2, y - size / 2, size * 2, size * 2);
            context.strokeRect(x - size / 2, y - size / 2, size * 2, size * 2);
            figuresList.push(figureSelected())
            write("Square - " + cont);
            cont++;
            break;

        case 'triangle':
            context.beginPath();
            context.moveTo(x, y - Number(size));
            context.lineTo(x - Number(size), y + Number(size));
            context.lineTo(x + Number(size), y + Number(size));
            context.closePath();
            context.fill();
            context.stroke();
            write("Triangle - " + cont);
            cont++;
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
            write("Star - " + cont);
            cont++;
            break;
    }
}

function write(text){
    document.querySelector('#list').innerHTML+="<li> <button>Remove</button> " + text + "</li>";
    }

select.addEventListener("change", function() {
    figureSelected = select.value;
});

colorInput.addEventListener("input", function() {
    color = colorInput.value;
});

checkbox.addEventListener("change", function() {
        checked = checkbox.checked;
});

rangeInput.addEventListener("input", function() {
    size = rangeInput.value;
});

canvas.addEventListener('click', function(event) {
    x = event.clientX - canvas.offsetLeft;
    y = event.clientY - canvas.offsetTop;
    drawFigureSelected(x, y, figureSelected);
});

nameImage.value = randomName();
