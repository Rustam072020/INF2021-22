<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Спасибо за бронирование ${name} </title>
    <style>
        h1 {
            color: #fa001d;
            text-align: center;
        }
        h2 {
            color: #00ff60;
            text-align: center;
        }
    </style>
</head>
<body>
<h1 class="center">Уважаемый, ${name}! Для подтверждения бронирование пройдите по ссылке ниже</h1>
<h2 class="center"><a href="localhost/confirm?code=${confirm_code}">Ссылка</a></h2>
</body>
</html>
