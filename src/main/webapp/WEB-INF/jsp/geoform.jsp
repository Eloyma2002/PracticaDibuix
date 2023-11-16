<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Geoform</title>
    <link rel="stylesheet" href="/css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <h2>Geoform</h2>
            <button class="navbar-toggler" type="button" data-mdb-toggle="collapse"
                data-mdb-target="#navbarCenteredExample" aria-controls="navbarCenteredExample" aria-expanded="false"
                aria-label="Toggle navigation">
                <i class="fas fa-bars"></i>
            </button>

            <div class="collapse navbar-collapse justify-content-center" id="navbarCenteredExample">
                <ul class="navbar-nav mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/allLists">All
                            lists</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/geoform">Draw</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/myList">My list</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>


    <canvas height="400" width="400" id="canvas"></canvas>
    <p></p>
    <form action="/geoform" method="post">
        <input type="button" id="clean" value="Clean"></input>
        <button type="submit" id="send">Send</button>
        <input type="button" id="draw" value="Draw"></button>
        <ul id="list"></ul>

        <p></p>
        <p>Name: <input type="text" id="name" value="." name="name"></p>
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

        <input type="hidden" id="JSON" name="JSON" value readonly>
    </form>

    <script src="/JS/script.js"></script>
</body>

</html>