document.addEventListener("DOMContentLoaded", function () {
    var nameInput = document.getElementById("name");
    var lastNameInput = document.getElementById("lastName");
    var usernameInput = document.getElementById("username");

    nameInput.addEventListener("input", function () {
        if (nameInput.value !== "") {
            nameInput.style.backgroundColor = "rgb(254,232,149)";
        } else {
            nameInput.style.backgroundColor = "";
        }
    });

    lastNameInput.addEventListener("input", function () {
        if (lastNameInput.value !== "") {
            lastNameInput.style.backgroundColor = "rgb(254,232,149)";
        } else {
            lastNameInput.style.backgroundColor = "";
        }
    });

    usernameInput.addEventListener("input", function () {
        if (usernameInput.value !== "") {
            usernameInput.style.backgroundColor = "rgb(254,232,149)";
        } else {
            usernameInput.style.backgroundColor = "";
        }
    });

});
