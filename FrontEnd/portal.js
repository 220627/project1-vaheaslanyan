const url = "http://localhost:3000";

let mainHeader = document.getElementById("mainHeader");
let userId;

document.addEventListener("DOMContentLoaded", function () {
  userId = getCookie("userId");
  getUser();
});

function getCookie(name) {
  // Split cookie string and get all individual name=value pairs in an array
  var cookieArr = document.cookie.split(";");

  // Loop through the array elements
  for (var i = 0; i < cookieArr.length; i++) {
    var cookiePair = cookieArr[i].split("=");

    /* Removing whitespace at the beginning of the cookie name
        and compare it with the given string */
    if (name == cookiePair[0].trim()) {
      // Decode the cookie value and return
      return decodeURIComponent(cookiePair[1]);
    }
  }

  // Return null if not found
  return null;
}

async function getUser() {
  let response = await fetch(url + `/users/${userId}`);

  setUserName(response);
}

async function setUserName(response) {
  if (response.status >= 200 && response.status < 300) {
    let data = await response.json();
    mainHeader.innerText = data.user_first_name;
  }
}
