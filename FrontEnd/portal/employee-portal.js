"use strict";

//NOTE: PJS* mark indicates that variable or function is created in portal.js file

/* MARK: - Saving elements to vars ---------------------------------------------------------------*/
let dasboardDiv = document.getElementById("dashboardDiv");
let newExpenseDiv = document.getElementById("newExpenseDiv");
let requestsDiv = document.getElementById("requestsDiv");

let reimbsTable = document.getElementById("reimbsTable");

// Buttons
// let dashboardButton = document.getElementById("dashboardButton");
let newExpenseButton = document.getElementById("newExpenseButton");
let reimbsButton = document.getElementById("reimbsButton");
let submitExpenseButton = document.getElementById("submitExpenseButton");

let expenseAmountInput = document.getElementById("expenseAmountInput");
let expenseDescriptionInput = document.getElementById(
  "expenseDescriptionInput"
);
let expenseTypeSelector = document.getElementById("expenseTypeSelector");

/* MARK: - Adding Event Listeners ------------------------------------------------------------------*/
document.addEventListener("DOMContentLoaded", setupLoadedPage);

dashboardButton.addEventListener("click", function () {
  offcanvasButtonPressed(dashboardButton);
});
newExpenseButton.addEventListener("click", function () {
  offcanvasButtonPressed(newExpenseButton);
});
reimbsButton.addEventListener("click", function () {
  offcanvasButtonPressed(reimbsButton);
});
// submitExpenseButton.onclick = submitExpenseButtonPressed;

submitExpenseButton.onclick = convertFileToByte;

/* MARK: - Page Setup -------------------------------------------------------------------------------*/
function setupLoadedPage() {
  getUser(); //PJS*

  dashboardButtonPressed();
  //TEMPORARY CODE
  newExpenseButtonPressed();
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
  newExpenseDiv.style.display = "none";
  requestsDiv.style.display = "none";
  requestCardDiv.style.display = "none"; //PJS*
  dasboardDiv.style.display = "block";

  mainHeader.innerHTML = "Dashboard"; //PJS*
}

function newExpenseButtonPressed() {
  dasboardDiv.style.display = "none";
  requestsDiv.style.display = "none";
  requestCardDiv.style.display = "none"; //PJS*
  newExpenseDiv.style.display = "block";

  mainHeader.innerHTML = "New Expense"; //PJS*
}

function reimbsButtonPressed(filter) {
  dasboardDiv.style.display = "none";
  newExpenseDiv.style.display = "none";
  requestsDiv.style.display = "block";
  requestCardDiv.style.display = "none"; //PJS*

  mainHeader.innerHTML = "Reimbursements"; //PJS*

  loadReimbsTable(filter);
}

//This function is called via onClick attribute in HTML
function tableRowPressed(reimbId) {
  dasboardDiv.style.display = "none";
  requestsDiv.style.display = "none";
  newExpenseDiv.style.display = "none";
  // requestCardDiv visibility is toggled in portal.js to make sure the data is received before it is rendered

  loadReimbCard(reimbId); //PJS*
}

/* MARK: - New Expense -------------------------------------------------------------------------------*/
async function submitExpenseButtonPressed() {
  let expenseAmount = expenseAmountInput.value;
  let expenseDescription = expenseDescriptionInput.value;
  let expenseTypeIndex = expenseTypeSelector.value;

  let newExpenseJson = JSON.stringify({
    reimb_amount: expenseAmount,
    reimb_description: expenseDescription,
    reimb_receipt_url: "url_here",
    reimb_type_id_fk: expenseTypeIndex,
    reimb_author_id_fk: userId,
    // reimb_resolver_id_fk: 1,
  });

  let response = await fetch(url + "/reimbs", {
    method: "POST",
    body: newExpenseJson,
    // credentials: "include"
  });

  if (response.status >= 200 && response.status < 300) {
    console.log("success");
  } else {
    console.log("messup");
  }
}

async function convertFileToByte() {
  
  const toBase64 = file => new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = error => reject(error);
});
  
  let imageFile = document.getElementById("receiptImageInput").files[0]

    let imageByte = await toBase64(imageFile);

    console.log(imageByte);

    
  
}