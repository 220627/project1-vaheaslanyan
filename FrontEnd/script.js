'use strict';

const url = "http://localhost:3000";

document.getElementById("loginButton").onclick = loginFunction;
let userData;


async function loginFunction() {

    console.log("clicked")

    //Getting user input for credentials
    let username = document.getElementById("emailInput").value;
    let password = document.getElementById("passwordInput").value;

    //Creating Json object with credentials
    let userCredentialsJson = JSON.stringify({
            username: username,
            password: password
        })

    // console.log(userCredentials.JSON)

    //Fetching request to server, second param is configurations for the request
    let response = await fetch(url + "/login", {
        method: "POST",
        body: userCredentialsJson,
        credentials: "include" //will capture cookie to enable sessions. Will need to include this to fetches after login as well
    });//include

    if (response.status >= 200 && response.status < 300) {

        let userData = await response.json();
        takeToDashboard(userData);
    } else {
        alert("Login failed");
    }

}

async function takeToDashboard(userData) {
    if (userData.user_role_id_fk == 1) {
        window.location.href = "manager_portal.html";
    } else {
        window.location.href = "employee_portal.html";
    }
}

