<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>FIRST SITE</title>
    <style>
        h1 {
            font-size: 40px;
        }
        .textField {
            display: block;
            font-size: 30px;
            border: 1px solid #f60606;
            border-radius: 10px;
            padding: 5px 10px;
            margin: 5px;
        }
        .button {
            font-size: 30px;
        }
    </style>
</head>
<body>
    <h1>Registration form</h1>
    <form method="post" action="/signUp">
        <input class="textField" name = "firstname" type="text">
        <input class="textField" name = "lastname" type="text">
        <input class="textField" name = "email" type="text">
        <input class="textField" name = "password" type="password">
        <input class="button" type="submit">
    </form>

    <h1>Sign In</h1>
    <form method="post" action="/signIn">
        <input class="text" name = "email" type="text">
        <input class="text" name = "password" type="password">
        <input class="button" name = "Sign in" type="submit">
    </form>

    <h1>Profile</h1>
    <form method="post" action="/profile">
        <input class="text" name = "firstname" type="text">
        <input class="text" name = "lastname" type="text">
        <input class="text" name = "email" type="text">
    </form>

</body>
</html>