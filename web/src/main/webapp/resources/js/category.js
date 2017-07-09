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