<%@ page import="ru.itis.dto.ProductsForm" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ToyShop-Order</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body style="background-color: #bdf8f8">
<div class="container">
    <div class="py-5 text-center">
        <h1 class="h2">Оформление заказа</h1>
    </div>

    <div class="row g-3">
        <div class="col-md-5 col-lg-4 order-md-last">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-muted">Ваша корзина</span>
            </h4>
            <ul class="list-group mb-3">
                <%
                    List<ProductsForm> products = (List<ProductsForm>) request.getAttribute("products");
                    for (ProductsForm product : products) {
                %>
                <li class="list-group-item d-flex justify-content-between lh-sm">
                    <div>
                        <h6 class="my-0"><%=product.getName()%></h6>
                        <small class="text-muted">Мягкая игрушка</small>
                    </div>
                    <span class="text-muted"><%=product.getCost()%>руб.</span>
                </li>
                <%
                    }
                %>
                <%
                    Integer sum = (Integer)request.getAttribute("sumCost");
                %>
                <li class="list-group-item d-flex justify-content-between">
                    <span>Итого (РУБ)</span>
                    <strong><%=sum%>руб.</strong>
                </li>
            </ul>
        </div>
        <div class="col-md-7 col-lg-8">
            <h4 class="mb-3">Адрес для выставления счета</h4>
            <form class="needs-validation" novalidate="">
                <div class="row g-3">
                    <div class="col-sm-6">
                        <label for="firstName" class="form-label">Имя получателя:</label>
                        <input type="text" class="form-control" id="firstName" placeholder="" value="" required="">
                    </div>

                    <div class="col-sm-6">
                        <label for="lastName" class="form-label">Фамилия получателя:</label>
                        <input type="text" class="form-control" id="lastName" placeholder="" value="" required="">
                    </div>

                    <div class="col-12">
                        <label for="email" class="form-label">Email <span class="text-muted">(необязательно)</span></label>
                        <input type="email" class="form-control" id="email" placeholder="you@example.com">
                    </div>

                    <div class="col-12">
                        <label for="number_phone" class="form-label">Номер телефона:</label>
                        <input type="number" class="form-control" id="number_phone">
                    </div>

                    <div class="col-12">
                        <label for="address" class="form-label">Адрес получателя</label>
                        <input type="text" class="form-control" id="address" placeholder="Москва, Арбат" required="">
                    </div>

                    <div class="col-md-7">
                        <label for="country" class="form-label">Страна</label>
                        <select class="form-select" id="country" required="">
                            <option value="">Выберите...</option>
                            <option>Россия</option>
                            <option>Украина</option>
                        </select>
                    </div>
                    <div class="col-md-7">
                        <label for="zip" class="form-label">Почтовый индекс</label>
                        <input type="text" class="form-control" id="zip" placeholder="" required="">
                    </div>
                </div>

                <h4 class="mb-3">Оплата</h4>

                <div class="my-3">
                    <div class="form-check">
                        <input id="credit" name="paymentMethod" type="radio" class="form-check-input" checked="" required="">
                        <label class="form-check-label" for="credit">Кредитная карта</label>
                    </div>
                    <div class="form-check">
                        <input id="debit" name="paymentMethod" type="radio" class="form-check-input" required="">
                        <label class="form-check-label" for="debit">Дебетовая карточка</label>
                    </div>
                    <div class="form-check">
                        <input id="paypal" name="paymentMethod" type="radio" class="form-check-input" required="">
                        <label class="form-check-label" for="paypal">PayPal</label>
                    </div>
                </div>

                <div class="row gy-3">
                    <div class="col-md-6">
                        <label for="cc-name" class="form-label">Имя на карте</label>
                        <input type="text" class="form-control" id="cc-name" placeholder="" required="">
                        <small class="text-muted">Полное имя, как показано на карточке</small>
                    </div>

                    <div class="col-md-6">
                        <label for="cc-number" class="form-label">Номер кредитной карты</label>
                        <input type="text" class="form-control" id="cc-number" placeholder="" required="">
                    </div>

                    <div class="col-md-3">
                        <label for="cc-expiration" class="form-label">Срок действия</label>
                        <input type="text" class="form-control" id="cc-expiration" placeholder="" required="">
                    </div>

                    <div class="col-md-3">
                        <label for="cc-cvv" class="form-label">CVV</label>
                        <input type="text" class="form-control" id="cc-cvv" placeholder="" required="">
                    </div>
                </div>
                <hr class="my-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit">Оплатить</button>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>




