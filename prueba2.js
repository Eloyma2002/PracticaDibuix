const canvas = document.querySelector('#canvas');
const context = canvas.getContext('2d');
const select = document.querySelector('#figure');
const colorInput = document.querySelector("#color");
const checkbox = document.querySelector("#fill");
const rangeInput = document.querySelector("#size");
const nameImage = document.querySelector('#name');
const buttonClean = document.querySelector('#clean');
const buttonSend = document.querySelector('#send');
const inputJSON = document.querySelector('#JSON');
const list = document.querySelector('#list');
const buttonDraw = document.querySelector('#draw');

canvas.width = 400;
canvas.height = 400;

let figureSelected = select.value;
let color = colorInput.value;
let checked = checkbox.checked;
let size = rangeInput.value;
let x, y;
let cont = 0;
let content = [];
let isDrawing = false;
let isDrawingEnabled = false;
let drawPath = [];

buttonDraw.addEventListener('click', function() {
    isDrawingEnabled = true;
});

canvas.addEventListener('mousedown', function(event) {
    if (isDrawingEnabled) {
        isDrawing = true;
        var rect = canvas.getBoundingClientRect();
        var xStart = event.clientX - rect.left;
        var yStart = event.clientY - rect.top;
        context.beginPath();
        context.moveTo(xStart, yStart);
        context.lineWidth = size;
        context.strokeStyle = color;
        drawPath = [{ x: xStart, y: yStart, color, size }];
    }
});

canvas.addEventListener('mousemove', function(event) {
    if (isDrawing && isDrawingEnabled) {
        var rect = canvas.getBoundingClientRect();
        var currentX = event.clientX - rect.left;
        var currentY = event.clientY - rect.top;
        context.lineTo(currentX, currentY);
        context.stroke();
        drawPath.push({ x: currentX, y: currentY, color, size });
    }
});

canvas.addEventListener('mouseup', function() {
    if (isDrawing && isDrawingEnabled) {
        isDrawing = false;
        isDrawingEnabled = false;
        content.push({ id: cont, path: drawPath, color, size });
        drawPath = [];
        cont++;
    }
});

function addFigureToList(id, figureSelected, x, y, size) {
    content.push({
        "id": id,
        "type": figureSelected,
        "x": x,
        "y": y,
        "color": color,
        "fill": checked,
        "size": size,
    });
}

nameImage.value = randomName();

function randomName() {
    return "image" + Math.floor(Math.random() * 9999 + 1);
}

function drawFigureSelected(x, y, figureSelected) {
    if (!isDrawingEnabled && !isDrawing) {
        context.strokeStyle = color;
        context.fillStyle = checked ? color : "transparent";

        switch (figureSelected) {
            case 'circle':
                context.beginPath();
                context.arc(x, y, size, 0, 2 * Math.PI);
                context.fill();
                context.stroke();
                addFigureToList(cont, figureSelected, x, y, size);
                write("Circle");
                break;

            case 'square':
                context.beginPath();
                context.fillRect(x - (size / 2), y - (size / 2),( size * 2), (size * 2));
                context.strokeRect(x - (size / 2), y - (size / 2), (size * 2), (size * 2));
                addFigureToList(cont, figureSelected, x, y, size);
                write("Square");
                break;

            case 'triangle':
                context.beginPath();
                context.moveTo(x, y - Number(size));
                context.lineTo(x - Number(size), y + Number(size));
                context.lineTo(x + Number(size), y + Number(size));
                context.closePath();
                context.fill();
                addFigureToList(cont, figureSelected, x, y, size);
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
                addFigureToList(cont, figureSelected, x, y, size);
                write("Star");
                break;
        }
        cont++;
    }
}

function write(text) {
    list.innerHTML += "<li id='li_" + cont + "'> <button onclick='removeElement(" + cont + ")'>Remove</button> " + text + "</li>";
}

select.addEventListener("change", function() {
    figureSelected = select.value;
});

buttonSend.addEventListener("click", function() {
    inputJSON.value = JSON.stringify(content);
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
    var rect = canvas.getBoundingClientRect();
    x = event.clientX - rect.left;
    y = event.clientY - rect.top;
    drawFigureSelected(x, y, figureSelected);
});

buttonClean.addEventListener('click', function() {
    cont = 0;
    content = [];
    list.innerHTML = "";
    context.clearRect(0, 0, canvas.width, canvas.height);
});

function reDoCanvasDrawing(id, path, color, size) {
    context.fillStyle = checked ? color : "transparent";

    path.forEach(point => {
        context.lineTo(point.x, point.y);
        context.stroke();
    });

    // Draw the figure based on the path, color, and size
    context.fill();
    context.stroke();

    write("Redraw " + id);
}

function removeElement(id) {
    const elementToRemove = document.getElementById("li_" + id);
    if (elementToRemove) {
        elementToRemove.remove();

        // Eliminar la figura del canvas
        const indexToRemove = content.findIndex(item => item.id === id);
        if (indexToRemove !== -1) {
            content.splice(indexToRemove, 1);
            refreshCanvas();
        }

        // Actualizar IDs en la lista y en el contenido
        for (let i = indexToRemove; i < content.length; i++) {
            const element = document.getElementById("li_" + content[i].id);
            if (element) {
                element.id = "li_" + content[i].id;
            }
        }
    }
}

function refreshCanvas() {
    context.clearRect(0, 0, canvas.width, canvas.height);
    content.forEach(item => {
        reDoCanvasDrawing(item.id, item.path, item.color, item.size);
    });
}
