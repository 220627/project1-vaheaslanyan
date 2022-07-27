'use strict'

//User ID variable that is populated in Page Setup section
let userId;

//Saving elements to vars
let mainHeader = document.getElementById("mainHeader");
let dashboardButton = document.getElementById("dashboardButton");
let newExpenseButton = document.getElementById("newExpenseButton");
let reimbsButton = document.getElementById("reimbsButton");

//Setting up event listeners
document.addEventListener("DOMContentLoaded", setupLoadedPage);
dashboardButton.addEventListener("click", function(){offcanvasButtonPressed(dashboardButton)});
newExpenseButton.addEventListener("click", function(){offcanvasButtonPressed(newExpenseButton)});
reimbsButton.addEventListener("click", function(){offcanvasButtonPressed(reimbsButton)});

/* MARK: - Page Setup -------------------------------------------------------------------------------*/

function setupLoadedPage() {
  userId = getCookie("userId");
  getUser();
}

async function getUser() {
  let response = await fetch(url + `/users/${userId}`);
  setUserName(response);
}

async function setUserName(response) {
  if (response.status >= 200 && response.status < 300) {
    let data = await response.json();
    mainHeader.innerText = `Welcome ${data.user_first_name}`;
  }
}

/* MARK: - Handling Navigation -----------------------------------------------------------------------*/
function offcanvasButtonPressed(button) {

  dashboardButton.classList.remove("pressed-button");
  newExpenseButton.classList.remove("pressed-button");
  reimbsButton.classList.remove("pressed-button");

  button.classList.add("pressed-button");
  console.log("clicked")
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