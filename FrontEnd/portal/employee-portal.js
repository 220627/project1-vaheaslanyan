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
// let reimbsButton = document.getElementById("reimbsButton");
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
submitExpenseButton.onclick = submitExpenseButtonPressed;

/* MARK: - Handling Navigation -----------------------------------------------------------------------*/
function offcanvasButtonPressed(button, reimbsTableFilter) {
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
      reimbsButtonPressed(reimbsTableFilter);
      break;
  }
}

function dashboardButtonPressed() {
  newExpenseDiv.style.display = "none";
  requestsDiv.style.display = "none";
  requestCardDiv.style.display = "none"; //PJS*
  dasboardDiv.style.display = "block";

  mainHeader.innerHTML = "Dashboard"; //PJS*
  setupDashboard(); //PJS
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

  loadReimbsTable(filter); //PJS*
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

  // Getting image from input and converting it to base64 to be able to send it to back-end
  let imageFile = document.getElementById("receiptImageInput").files[0];
  let imageBase64;
  try {
    imageBase64 = await convertFileToBase64(imageFile);
  } catch(e) {
    console.error(e);
  }
  

  let newExpenseJson = JSON.stringify({
    reimb_amount: expenseAmount,
    reimb_description: expenseDescription,
    reimb_receipt_url: imageBase64, // a base64 is passed which is sent to GCStorage from ReimbDAO and then it is replaced with actual URL and saved in DB
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
    console.log("Expense successfully added.");
  } else {
    console.log("Failed to add Expense: Error has occured.");
  }
}

// Converting
const convertFileToBase64 = (file) =>
  new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = (error) => reject(error);
  });
