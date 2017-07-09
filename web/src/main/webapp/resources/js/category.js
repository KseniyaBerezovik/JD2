function getCategory() {
    var categoryName = $("#category").val();
    var category = {
        name: categoryName
    };

    $.ajax("/admin/addProductRest", {
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(category)
    }).done(function () {
        window.location.href = '/addDetails';
        document.getElementById("categoryForm").style.visibility = "hidden";
        document.getElementById("characteristicsForm").style.visibility = "visible";
    })
}

function getDetail() {
    var details = document.getElementsByClassName("myDetail");

    var arr = {};
    for (var i = 0; i < details.length; i++) {
        var item = details[i];
        console.log(details[i].name);
        console.log(details[i].value);

        arr[item.name] = item.value;
        $.ajax("/admin/addDetails", {
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                name: details[i].name,
                value: details[i].value
            })
        })
    }
    window.location.href = "/addimg";

    console.log(JSON.stringify(arr));
}



// function getUser () {
//     var userLogin = $("#login").val();
//     var userPassword = $("#password").val();
//
//     var user = {
//         login:userLogin,
//         password:userPassword
//     }
//
//     $.ajax("/saveUser", {
//         method: "POST",
//         contentType: "application/json",
//         data: JSON.stringify(user)
//     }).done(function (message) {
//         $('#message').text(message.content);
//     })
// }
//
// function appendUser() {
//     var userLogin = $("#login").val();
//     var userPassword = $("#password").val();
//
//     var user = {
//         login:userLogin,
//         password:userPassword
//     }
//
//     $.ajax("/appendUser", {
//         method: "POST",
//         contentType: "application/json",
//         data: JSON.stringify(user)
//     }).done(function (arr) {
//         $('#users').empty();
//
//         arr.forEach(function (item, i, array) {
//             var element = document.createElement("p");
//             element.textContent = item.content;
//             document.getElementById("users").appendChild(element);
//         })
//     })
// }