<%@ page import="java.util.Optional" %>
<%@ page import="ru.itis.models.User" %>
<%@ page import="ru.itis.dto.ProductsForm" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=utf-8" %>

<html>
<head>
    <title>ToyShop-Cart</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
        <%@include file="css/catalog.css" %>
    </style>
    <script src="/js/jquery.js"></script>
    <script src="/js/Products.js"></script>
</head>
<body>
<nav>
    <div class="row">
        <div class="navbar fixed-top navbar-expand-lg bg-light container-fluid">
            <div class="col-10">
                <div class="navbar-header">
                    <a class="navbar-brand" style="color: darkblue">TOYSHOP</a>
                </div>
            </div>
            <div class="col-2">
                <a class="navbar-brand" href="/catalog">
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" class="bi bi-list" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
                    </svg>
                </a>
                <a class="navbar-brand" href="/in/profile">
                    <svg xmlns="http://www.w3.org/2000/svg" width="34" height="34" class="bi bi-person" viewBox="0 0 16 16">
                        <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                    </svg>
                </a>
                <a class="navbar-brand" href="/in/wishList">
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" class="bi bi-suit-heart" viewBox="0 0 16 16">
                        <path d="m8 6.236-.894-1.789c-.222-.443-.607-1.08-1.152-1.595C5.418 2.345 4.776 2 4 2 2.324 2 1 3.326 1 4.92c0 1.211.554 2.066 1.868 3.37.337.334.721.695 1.146 1.093C5.122 10.423 6.5 11.717 8 13.447c1.5-1.73 2.878-3.024 3.986-4.064.425-.398.81-.76 1.146-1.093C14.446 6.986 15 6.131 15 4.92 15 3.326 13.676 2 12 2c-.777 0-1.418.345-1.954.852-.545.515-.93 1.152-1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z"/>
                    </svg>
                </a>
            </div>
        </div>
    </div>
</nav>

<div class="catalog-list">
    <div class="row row-cols-1 row-cols-md-4 g-4">
        <%
            List<ProductsForm> products = (List<ProductsForm>) request.getAttribute("products");
            for (ProductsForm product : products) {
        %>
        <div class="col">
            <div class="card h-100" style="background-color: #fffa98">
                <div class="product-grid">
                    <div class="product-image">
                        <a href="/product?id=<%=product.getId()%>" class="image">
                            <img class="pic-1" style="border-radius: 100px" src="/toy/<%=product.getPicture()%>">
                        </a>
                    </div>
                    <div class="product-content">
                        <h3 class="title"><a href="/product?id=<%=product.getId()%>"><%=product.getName()%></a></h3>
                        <div class="price"><%=product.getCost()%>руб.</div>
                        <div class="d-grid gap-2 col-6 mx-auto">
                            <button type="submit" class="btn btn-outline-info" onclick="addToList(<%=product.getId()%>)">
                                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" class="bi bi-suit-heart" viewBox="0 0 16 16">
                                    <path d="m8 6.236-.894-1.789c-.222-.443-.607-1.08-1.152-1.595C5.418 2.345 4.776 2 4 2 2.324 2 1 3.326 1 4.92c0 1.211.554 2.066 1.868 3.37.337.334.721.695 1.146 1.093C5.122 10.423 6.5 11.717 8 13.447c1.5-1.73 2.878-3.024 3.986-4.064.425-.398.81-.76 1.146-1.093C14.446 6.986 15 6.131 15 4.92 15 3.326 13.676 2 12 2c-.777 0-1.418.345-1.954.852-.545.515-.93 1.152-1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z"/>
                                </svg>
                            </button>
                        </div>
                        <form action="/in/cart?id=<%=product.getId()%>" method="post">
                            <div class="d-grid gap-2 col-6 mx-auto">
                                <button type="submit" name="action" class="btn btn-outline-info" value="dropProduct">
                                    <i class="bi bi-trash"></i>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>

<nav>
    <div class="row">
        <div class="navbar fixed-bottom navbar-light bg-light">
            <div class="col-9"></div>
            <%
                Integer sum = (Integer)request.getAttribute("sumCost");
            %>
            <div class="col-3">
                <form action="/in/order">
                <button type="submit" class="btn btn-outline-info">Сумма заказа:<%=sum%>руб. Перейти к оформлению</button>
                </form>
            </div>
        </div>
    </div>
</nav>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
