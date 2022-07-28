"use strict";

//User ID variable that is populated in Page Setup section in subsequent js files
let userId;

//Saving elements to vars
let navbarUserName = document.getElementById("navbarUserName");
let mainHeader = document.getElementById("mainHeader");

/* MARK: - Page Setup -------------------------------------------------------------------------------*/
async function getUser() {
    userId = getCookie("userId");
  let response = await fetch(url + `/users/${userId}`, {
    // credentials: "include"
  });

  setUserName(response);
}

async function setUserName(response) {
  if (response.status >= 200 && response.status < 300) {
    let data = await response.json();
    navbarUserName.innerHTML = `Hi, ${data.user_first_name}`;
    // mainHeader.innerText = `Welcome ${data.user_first_name}`;
  }
}

/* MARK: - Miscellaneous -----------------------------------------------------------------------------*/
//Function to get cookie value by name
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