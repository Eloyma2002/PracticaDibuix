<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Geoform</title>
</head>

<body>
    <h1>Geoform</h1>
    <a href="/allLists">List all drawings | </a>
    <a href="http://">List my drawings | </a>
    <a href="/geoform">Draw</a>
    <p></p>


    <canvas height="400" width="400" id="canvas1" style="border: 2px solid black;"></canvas>
    <p></p>
    <form action="/geoform" method="post">
        <input type="button" id="clean" value="Clean"></input>
        <button type="submit" id="send">Send</button>
        <input type="button" id="draw" value="Draw"></button>
    <ul id="list"></ul>

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

    <input type="hidden" id="JSON" name="JSON" value="" readonly>
    </form>

    <script src="/JS/script.js"></script>
</body>

</html>