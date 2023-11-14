<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Drawing</title>
    <link rel="stylesheet" href="/css/styles.css">

</head>

<body>
    <h1>View Drawing</h1>
    <a href="/allLists">List all drawings | </a>
    <a href="/myList">List my drawings | </a>
    <a href="/geoform">Draw</a>
    <p></p>


    <canvas height="400" width="400" id="canvas"></canvas>
    <input type="hidden" id="JSON" name="JSON" value="${json}" readonly>


    <script src="/JS/script_view.js"></script>
</body>

</html>