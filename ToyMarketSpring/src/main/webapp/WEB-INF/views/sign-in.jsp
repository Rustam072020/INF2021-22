<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ToyShop-SignIn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body style="background-color: #bdf8f8">
<div class="rounded d-flex justify-content-center">
    <div class="col-md-4 col-sm-12 col-mt-3 mt-5 shadow-lg p-5 bg-light">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="card" style="width: 32rem;">
                <div class="card-body">
                    <h3 class="text-uppercase text-center mb-5">Войти</h3>
                    <form action="/signIn" method="post">
                        <div class="form-outline mb-1">
                            <label class="form-label" for="email">Почта</label>
                            <input type="email" id="email" name="email" class="form-control form-control-lg" />
                        </div>
                        <div class="form-outline mb-4">
                            <label class="form-label" for="password">Пароль</label>
                            <input type="password" id="password" name="password" class="form-control form-control-lg" />
                        </div>
                        <div class="d-grid gap-2 col-6 mx-auto">
                            <button type="submit" class="btn btn-primary">Войти</button>
                        </div>
                        <p class="text-center text-muted mt-5 mb-0">
                            <a href="/signUp" class="fw-bold text-body"><u>Зарегистрироваться</u></a>
                        </p>
                    </form>
                </div>
            </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>