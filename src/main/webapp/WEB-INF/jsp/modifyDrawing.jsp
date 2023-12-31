<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Modify drawing</title>
            <link rel="stylesheet" href="/css/styles.css">
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
                crossorigin="anonymous">
        </head>

        <body>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <h2>Modify Drawing</h2>
                <div class="container-fluid">
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


            <div class="container-fluid principal">
                <div class="row">
                    <div class="col principal">
                        <ul id="list"></ul>
                    </div>
                    <div class="col">
                        <canvas height="400" width="400" id="canvas" style="border: 2px solid black;"></canvas>
                        <p></p>
                        <p class="error">
                            ${error}
                        </p>
                    </div>
                    <div class="col principal">
                        <form action="/modifyDrawing" method="post">
                            <input type="button" id="clean" value="Clean"></input>
                            <button type="submit" id="send">Send</button>
                            <input type="button" id="draw" value="Draw"></button>
                            <ul id="list"></ul>

                            <p></p>
                            <p>Name: <input type="text" id="name" value=${name} name="name"></p>
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

                            <input type="hidden" name="drawingId" value=${drawingId} />
                            <input type="hidden" id="JSON" name="JSON" value=${JSON} readonly>
                        </form>
                    </div>
                </div>
            </div>

            <script src="/JS/script_modify.js"></script>
        </body>

        </html>