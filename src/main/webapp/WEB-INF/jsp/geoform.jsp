<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Geoform</title>
    <link rel="stylesheet" href="/src/main/webapp/css/styles.css">
</head>
<body>
    <h1>Geoform</h1>

    <div id="principalContainer">
        <canvas height="400" width="400" id="canvas1"></canvas>
        <ul id="list"></ul>
    </div>
    <p></p>
    <form action="index.html" method="post">
        <input type="button" id="clean" value="Clean"></input>
        <button id="send">Send</button>
        <input type="button" id="draw" value="Draw"></input>
    </form>
    <p></p>
    <p>Name: <input type="text" id="name" value="."></p> 
    <select name="figure" id="figure">
        <option value="triangle">Triangle</option>
        <option value="square">Square</option>
        <option value="circle">Circle</option>
        <option value="star">Star</option>
    </select>
    <p></p>
    <p></p>
    <p>Color: <input type="color" name="color" id="color"></p>
    <label for="fill">Fill: <input type="checkbox" name="fill" id="fill"></label>
    <p></p>
    <p>Size: <input type="range" name="size" id="size"></p>

    <input type="hidden" id="JSON">

<script src="/prueba2.js"></script>
</body>
</html>

