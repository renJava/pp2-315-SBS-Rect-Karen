document.addEventListener("DOMContentLoaded", function () {
    let inputs = [
        document.getElementById("name"),
        document.getElementById("lastName"),
        document.getElementById("username")
    ];

    inputs.forEach(function (input) {
        input.addEventListener("input", function () {
            if (input.value !== "") {
                input.style.backgroundColor = "rgb(254,232,149)";
            } else {
                input.style.backgroundColor = "";
            }
        });
    });

});

