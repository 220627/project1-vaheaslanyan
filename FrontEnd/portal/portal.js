'use strict'

//User ID variable that is populated in Page Setup section
let userId;

//Saving elements to vars
let dasboardDiv = document.getElementById("dashboardDiv");
let newExpenseDiv = document.getElementById("newExpenseDiv");
let reimbsDiv = document.getElementById("reimbsDiv");

let mainHeader = document.getElementById("mainHeader");
let reimbsTable = document.getElementById("reimbsTable");

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

  switch (button) {
    case dashboardButton:
        dashboardButtonPressed();
      break;
    case newExpenseButton:
        newExpenseButtonPressed();
      break;
    case reimbsButton:
        reimbsButtonPressed();
      break;

  }
}

function dashboardButtonPressed() {
  newExpenseDiv.style.display = 'none';
  reimbsDiv.style.display = 'none';
  dasboardDiv.style.display = 'block';

  mainHeader.innerHTML = "Dashboard"
}

function newExpenseButtonPressed() {
  dasboardDiv.style.display = "none";
  reimbsDiv.style.display = 'none';
  newExpenseDiv.style.display = 'block';

  mainHeader.innerHTML = "New Expense"
}

function reimbsButtonPressed() {
  dasboardDiv.style.display = 'none';
  newExpenseDiv.style.display = 'none';
  reimbsDiv.style.display = 'block';
  loadReimbs()
}

async function loadReimbs() {

  // Removing any previous tbody
  if (reimbsTable.childElementCount > 1) {
    reimbsTable.removeChild(reimbsTable.lastChild);
  }
  
  // Creating a tbody that will be appended a child at the end of this function
  let reimbsTableBody = document.createElement("tbody");

  // Sending fetch request to back-end
  let response = await fetch(url + "/reimbs");

  if (response.status >= 200 && response.status < 300) {

    let data = await response.json();

    for(let reimb of data) {

      // Creating a row and cells for every data
      let row = document.createElement("tr");
      let amountCell = document.createElement("td");
      let typeCell = document.createElement("td");
      let descriptionCell = document.createElement("td");
      let submittedCell = document.createElement("td");
      let statusCell = document.createElement("td");

      // Populating cells
      amountCell.innerHTML = reimb.reimb_amount;
      typeCell.innerHTML = reimb.reimbType.reimb_type_name;
      descriptionCell.innerHTML = reimb.reimb_description;
      submittedCell.innerHTML = reimb.reimb_submitted;
      statusCell.innerHTML = reimb.reimb_is_approved;

      // Appendning cells to the row
      row.appendChild(amountCell);
      row.appendChild(typeCell);
      row.appendChild(descriptionCell);
      row.appendChild(submittedCell);
      row.appendChild(statusCell);

      // Appending the row to the table body
      reimbsTableBody.appendChild(row);
    }

    // Appending the table body to the table
    reimbsTable.appendChild(reimbsTableBody);
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