function getDetail() {
    var details = document.getElementsByClassName("myDetail");
    for (var i = 0; i < details.length; i++) {
        $.ajax("/admin/add-product/add-details", {
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                name: details[i].name,
                value: details[i].value
            })
        })
    }
    window.location.href = "/admin/add-product/third";
}

function deleteFromCart(id) {
    var productId = document.getElementById("productId" + id).getAttribute("value");
    var amount = document.getElementById("amount" + id).value;
    console.log(productId);
    console.log(amount);

    $.ajax("/user/cart/delete", {
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            productId: productId,
            amount: amount
        })
    }).done (function redirect(amount) {
        $("#amountProductsInCart").text(amount.amount);
        window.location.href = "/user/cart/";
    })
}

function addToCart(id) {
    var productId = document.getElementById("productId" + id).getAttribute("value");
    var amount = document.getElementById("amount" + id).value;
    console.log(productId);
    console.log(amount);

    $.ajax("/user/cart/add", {
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            productId: productId,
            amount: amount
        })
    }).done (function redirect(amount) {
        $("#amountProductsInCart").text(amount.amount);
    })
}

// function getYear(year) {
//     var currentUrl = window.location.href.toString();
//     var newUrl;
//     if(currentUrl.indexOf('?') >= 0) {
//         newUrl = currentUrl + '&year=' + year
//     } else {
//         newUrl = currentUrl + "?year=" + year
//     }
//     console.log(newUrl);
//     $.ajax(newUrl,  {
//         method: "GET",
//     }).done(function success() {
//         console.log('success');
//         window.history.pushState({}, "", newUrl.toString());
//     })
// }

function getFilters() {
    var currentUrl = window.location.href.toString().split("?")[0];
    var newUrl = currentUrl;
    var filters = document.getElementsByClassName("filters");
    for (i = 0; i < filters.length; i++) {
        if(filters[i].type == 'checkbox' && filters[i].checked) {
            if (newUrl.indexOf('?') >= 0) {
                newUrl += '&';
            } else {
                newUrl += '?';
            }
            newUrl += filters[i].dataset.filter + '=' + filters[i].dataset.filterValue;
        } else if(filters[i].type == 'select-one' && filters[i].value != '') {
            if (newUrl.indexOf('?') >= 0) {
                newUrl += '&';
            } else {
                newUrl += '?';
            }
            newUrl += filters[i].dataset.filter + filters[i].dataset.fromTo + '=' + filters[i].value;
        }
    }
    console.log(newUrl);
    window.location.href = newUrl;
}

// if (filters[1].type == 'checkbox') {
//     if (currentUrl.indexOf('?') >= 0) {
//         newUrl += '&year=' + filters[i].value;
//     } else {
//         newUrl +=  "?year=" + filters[i].value;
//     }
// } else {
//     if (currentUrl.indexOf('?') >= 0) {
//         newUrl += '&year=' + filters[i].value;
//     } else {
//         newUrl +=  "?year=" + filters[i].value;
//     }
// }