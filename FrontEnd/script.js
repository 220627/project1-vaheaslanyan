const url = "http://localhost:3000";

document.getElementById("loginButton").onclick = loginFunction;


async function loginFunction() {

    //Getting user input for credentials
    let username = document.getElementById("emailInput").value;
    let password = document.getElementById("passwordInput").value;

    //Creating Json object with credentials
    let userCredentialsJson = JSON.stringify({
            username: username,
            password: password
        })

    // console.log(userCredentials)

    //Fetching request to server, second param is configurations for the request
    let response = await fetch(url + "/login", {
        method: "POST",
        body: userCredentialsJson,
        credentials: "same-origin" //will capture cookie to enable sessions. Will need to include this to fetches after login as well
    });//same-origin

    if (response.status >= 200 && response.status < 300) {

        let data = await response.json();

        alert("Logged In")
    } else {
        alert("Login failed")
    }

}