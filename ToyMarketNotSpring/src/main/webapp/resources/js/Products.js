function addToBasket(productId) {
    $.ajax('/catalog', {
        method: 'POST',
        data: {
            action: "addToCart",
            id: productId
        },
        success: function( result ) {
            alert("Продукт добавлен в корзину")
        },
        error : function () {
            alert("Требуется авторизоваться")
        }
    })
}

function addToList(productId) {
    $.ajax('/catalog', {
        method: 'POST',
        data: {
            action: "addToWishList",
            id: productId
        },
        success: function( result ) {
            alert("Продукт добавлен в виш-лист")
        },
        error : function () {
            alert("Требуется авторизоваться")
        }
    })
}

function sorted(type){
    $.ajax('/catalog', {
        method: 'POST',
        data: {
            type
        },
        success: function (result) {
            alert("Продукт добавлен в виш-лист")
        },
        error: function () {
            alert("Требуется авторизоваться")
        }
    })

}